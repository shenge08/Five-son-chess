package com.tianli.chenhui.main;

import java.io.IOException;
import java.lang.Thread.State;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Response;

import com.tianli.chenhui.javaBean.Desk;
import com.tianli.chenhui.javaBean.DeskState;
import com.tianli.chenhui.javaBean.FiveChessData;
import com.tianli.chenhui.tcptools.HandleDataAgent;
import com.tianli.chenhui.tcptools.SocketEvent;
import com.tianli.chenhui.tcptools.TcpClientSocket;
import com.tianli.chenhuishen.connectdata.CommunicateParam;
import com.tianli.chenhuishen.connectdata.SMessage;

public class DataHandler implements HandleDataAgent {
	public static FiveChessData data;
	public  List<TcpClientSocket> clientSockets;
	public  Map<TcpClientSocket, String> inDeskClients;
	public DataHandler(List<TcpClientSocket> clientSockets) {
		data=new FiveChessData();
		this.clientSockets=clientSockets;
		inDeskClients=new HashMap<TcpClientSocket, String>();
	}
	
	@Override
	public void handleData(SocketEvent e) {
		SMessage message=(SMessage)e.getInfo();
		if(message.type==CommunicateParam.LOGIN){
			handleLoginRequest(e);
		}
		
		
		if(message.type==CommunicateParam.DESK_DETAL){
			handleGetDeskInfo(e);
			reponseAllClient(CommunicateParam.PERSON_COUNT,clientSockets.size()+"");
		}
		
		if(message.type==CommunicateParam.CHOOSE_DESK){
			TcpClientSocket currentClient=(TcpClientSocket)e.getSource();
			String haveChoose=this.inDeskClients.get(currentClient);
			if(haveChoose!=null){
				int haveChooseNum=Integer.parseInt(haveChoose);      //客户端已经选择的桌子
				Desk lastdesk=data.desks[haveChooseNum];
				String wantChoose=message.Info;                       //客户端要选择的桌子
				String stateFlag=wantChoose+"n";
				if(haveChoose.equals(wantChoose)){
					if(lastdesk.state==DeskState.Full){
						System.out.println("离不开原房间 并有2人");
						return;
						
					}
					lastdesk.blackClient=null;
					lastdesk.state=DeskState.Empty;
					stateFlag=stateFlag+"0";
					this.inDeskClients.remove(currentClient);
					System.out.println("离开原房间 并有0人");
					reponseAllClient(CommunicateParam.CHOOSE_DESK, stateFlag);
				}else{
					if(lastdesk.state==DeskState.Full){
						return;
					}
					lastdesk.blackClient=null;
					lastdesk.state=DeskState.Empty;
					int wantDeskNum=Integer.parseInt(wantChoose);
					Desk wantDesk=data.desks[wantDeskNum];
					if(wantDesk.state==DeskState.Full){
						System.out.println("目标房间已满    进不去");
						return;
					}else if(wantDesk.state==DeskState.Empty && (lastdesk.state!=DeskState.Full)){
						this.inDeskClients.remove(currentClient);
						wantDesk.blackClient=currentClient;
						wantDesk.state=DeskState.One;
						stateFlag=haveChoose+"n0n"+wantChoose+"n1";
						System.out.println("目标房间有0人   进入 并有1人");
						this.inDeskClients.put(currentClient, wantChoose);
						reponseAllClient(CommunicateParam.CHANGE_DESK, stateFlag);		
					}else if(wantDesk.state==DeskState.One && (lastdesk.state!=DeskState.Full)){
						this.inDeskClients.remove(currentClient);
						wantDesk.whiteClient=currentClient;
						wantDesk.state=DeskState.Full;
						stateFlag=haveChoose+"n0n"+wantChoose+"n2";
						System.out.println("目标房间有1人   进入 并有2人");
						this.inDeskClients.put(currentClient, wantChoose);
						reponseAllClient(CommunicateParam.CHANGE_DESK, stateFlag);	
					}
				}
			}else{
				handleFirstChoose(e, message);
			}
			
		}
		
	}

	private void handleFirstChoose(SocketEvent e, SMessage message) {
		String str_num=message.Info;
		String stateFlag=str_num+"n";
		TcpClientSocket currentClient=(TcpClientSocket)e.getSource();
		int clientChooseNum=Integer.parseInt(str_num);
		Desk desk=data.desks[clientChooseNum];
		DeskState lastState=desk.state;
		if(desk.blackClient==null && desk.whiteClient==null){
			desk.blackClient=currentClient;
			desk.state=DeskState.One;
			System.out.println("进空房间 并有1人");
			stateFlag=stateFlag+"1";
		}else if(desk.blackClient!=null && desk.whiteClient==null){
			desk.whiteClient=currentClient;
			desk.state=DeskState.Full;
			System.out.println("进一人房间 并有2人");
			stateFlag=stateFlag+"2";
			/*if(desk.blackClient!=currentClient){
				desk.whiteClient=currentClient;
				desk.state=DeskState.Full;
				stateFlag=stateFlag+"2";
			}else {
				desk.blackClient=null;
				desk.state=DeskState.Empty;
				stateFlag=stateFlag+"0";
			}*/
		
		}else if (desk.blackClient!=null && desk.whiteClient!=null) {
			//
		}
		if(lastState!=desk.state){
			this.inDeskClients.put(currentClient, str_num);
			reponseAllClient(CommunicateParam.CHOOSE_DESK, stateFlag);
		}
		if(lastState==DeskState.One&&desk.state==DeskState.Full){
			
		}
	}

	private void reponseAllClient(CommunicateParam param,String msg) {
		SMessage reponseMsg=new SMessage(param, null, null);
		reponseMsg.Info=msg;
		for (TcpClientSocket tcpClientSocket : clientSockets) {
			try {
				tcpClientSocket.send(reponseMsg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void handleGetDeskInfo(SocketEvent e) {
		SMessage reponseMsg=new SMessage(CommunicateParam.DESK_DETAL, null, null);
		StringBuilder result=new StringBuilder("");
		for (Desk desk : data.desks) {
			if (desk.state==DeskState.Empty) {
				result.append("0");
			}
			if (desk.state==DeskState.One) {
				result.append("1");
			}
			if (desk.state==DeskState.Full) {
				result.append("2");
			}
			
		}
		reponseMsg.Info=result.toString();
		System.out.println(reponseMsg.Info);
		reponseMsg.isSuccess=true;
		try {
			((TcpClientSocket)e.getSource()).send(reponseMsg);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void handleLoginRequest(SocketEvent e) {
		SMessage reponseMsg=new SMessage(CommunicateParam.LOGIN, null, null);
		reponseMsg.isSuccess=true;
		try {
			((TcpClientSocket)e.getSource()).send(reponseMsg);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void exit(SocketEvent e) {
		// TODO Auto-generated method stub
		
	}

}
