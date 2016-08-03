package com.tianli.chenhuishen.tools;

import java.io.IOException;
import java.net.Socket;

import com.tianli.chenhuishen.listener.CommuicateAgent;

import com.tianli.chenhuishen.connectdata.SMessage;


public class MyClientSocket extends TcpClientSocket implements CommuicateAgent{

	public MyClientSocket(Socket socket) throws IOException {
		super(socket);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void sendInformation(SMessage s) {
		// TODO Auto-generated method stub
		try {
			this.send(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	


	
}
