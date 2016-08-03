package com.tianli.chenhuishen.tools;


import java.io.IOException;
import java.net.ServerSocket;





public class TcpServerSocket {
	private static ServerSocket serverSocket;
	
	public static ServerSocket getInstance(int port) throws IOException{
		if(serverSocket==null){
			synchronized ( TcpServerSocket.class) {
				if ( TcpServerSocket.serverSocket==null) {
					 TcpServerSocket.serverSocket=new ServerSocket(port);//建立服务端Socket
				}
			}
		}
		return serverSocket;
	}
	
	public static TcpClientSocket getClientSocket(int port ,HandleDataAgent handle) throws IOException {
		TcpClientSocket clientSocket=new TcpClientSocket(TcpServerSocket.getInstance(port).accept());
		if(handle!=null){
			clientSocket.addAgent(handle);
		}
		return clientSocket;
	}
}
