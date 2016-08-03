package com.tianli.chenhuishen.Main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.tianli.chenhuishen.View.HallFrame;
import com.tianli.chenhuishen.View.LoginFrame;
import com.tianli.chenhuishen.enums.FrameChangeParam;
import com.tianli.chenhuishen.listener.FrameControlAgent;
import com.tianli.chenhuishen.tools.ClientDataHandler;
import com.tianli.chenhuishen.tools.MyClientSocket;

public class CMain implements FrameControlAgent{
	private static MyClientSocket mSocket;
	private static ClientDataHandler dataHandler;
	private static CMain main;
	public static void main(String args[]) {
		 main=new CMain();
		
		LoginFrame lf=new LoginFrame();
		try {
			mSocket=new MyClientSocket(new Socket("127.0.0.1", 10004));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataHandler=new ClientDataHandler();
		if(mSocket!=null)
		mSocket.addAgent(dataHandler);
		lf.addCommunicateAndFrameControlAgent(mSocket,main);
		dataHandler.addListener(lf);
		lf.setVisible(true);
	}

	@Override
	public void changeFrame(FrameChangeParam param) {
		if(param==FrameChangeParam.ToHallFrame){
			HallFrame hallFrame=new HallFrame(mSocket, main);
			//hallFrame.addAgent(mSocket, main);
			dataHandler.addListener(hallFrame);
		}
		
	}

	
	


	
}
