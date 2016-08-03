package com.tianli.chenhuishen.View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.tianli.chenhuishen.enums.DeskState;
import com.tianli.chenhuishen.enums.FrameChangeParam;
import com.tianli.chenhuishen.listener.CommuicateAgent;
import com.tianli.chenhuishen.listener.FrameControlAgent;
import com.tianli.chenhuishen.listener.ResponseListener;
import com.tianli.chenhuishen.connectdata.CommunicateParam;
import com.tianli.chenhuishen.connectdata.SMessage;



public class HallFrame extends JFrame implements ResponseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -285987305880938689L;
	
	private DeskPanel dp;
	private HallInfoPanel hip;
	public ImageIcon[] imageIcons;
	
	private FrameControlAgent fca;
	
	public void addAgent(CommuicateAgent cl,FrameControlAgent fca){
		this.fca=fca;
		this.dp.addCummnicateAgent(cl);
		this.hip.addCummnicateAgent(cl);
		this.dp.getDeskDetail();
	}
	
	
	
	
	public HallFrame(CommuicateAgent cl,FrameControlAgent fca){
		this.setSize(750,635);
		this.setLayout(null);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Game Hall");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initSubViews();
		this.addAgent(cl,fca);
		this.setVisible(true);
		}
	
	
	
	private void initSubViews() {
		imageIcons=new ImageIcon[4];
		imageIcons[0]=new ImageIcon("img/deskbg.png");
		imageIcons[1]=new ImageIcon("img/roomlist/table.jpg");
		imageIcons[2]=new ImageIcon("img/roomlist/left.jpg");
		imageIcons[3]=new ImageIcon("img/roomlist/full.jpg");
		this.dp=new DeskPanel(imageIcons);
		this.add(this.dp);
		
		this.hip=new HallInfoPanel();
		this.add(this.hip);
		
	}
	
	
	
	
	
	
	@Override
	public void handleResponseData(SMessage msg) {
		if(msg.type==CommunicateParam.DESK_DETAL){
			String result=msg.Info;
			handleDeskDetail(result);
		}
		if(msg.type==CommunicateParam.CHOOSE_DESK){
			String[] results=msg.Info.split("n");
			int deskNum=Integer.parseInt(results[0]);
			int stateNum=Integer.parseInt(results[1]);
			this.dp.desks[deskNum].setIcon(imageIcons[stateNum+1]);
			
		}
		if(msg.type==CommunicateParam.CHANGE_DESK){
			String[] results=msg.Info.split("n");
			int lastDeskNum=Integer.parseInt(results[0]);
			int lastStateNum=Integer.parseInt(results[1]);
			int DeskNum=Integer.parseInt(results[2]);
			int StateNum=Integer.parseInt(results[3]);
			this.dp.desks[lastDeskNum].setIcon(imageIcons[lastStateNum+1]);
			this.dp.desks[DeskNum].setIcon(imageIcons[StateNum+1]);
		}
	
		if(msg.type==CommunicateParam.PERSON_COUNT){
			String result=msg.Info;
			this.hip.lab_num.setText(result);
		}
		
	}




	private void handleDeskDetail(String result) {
		for(int i=0;i<result.length();i++){
			char k=result.charAt(i);
			int m=k-48;
			this.dp.desks[i].setIcon(imageIcons[m+1]);
			if(m==0){dp.desks[i].setState(DeskState.Empty);}
			if(m==1){dp.desks[i].setState(DeskState.One);}
			if(m==2){dp.desks[i].setState(DeskState.Full);}
		}
		
	}

	
	
}
