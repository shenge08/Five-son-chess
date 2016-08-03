package com.tianli.chenhui.main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.tianli.chenhui.tcptools.*;


public class CMain {
	public static void main(String[] args){
		 List<TcpClientSocket> clientSockets=new LinkedList<TcpClientSocket>();
		 DataHandler dataHandler=new DataHandler(clientSockets);
		 TcpClientSocket client=null;
			while (true) {
				try {
					 client=TcpServerSocket.getClientSocket(10004,dataHandler);
					 clientSockets.add(client);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
	} 
}
