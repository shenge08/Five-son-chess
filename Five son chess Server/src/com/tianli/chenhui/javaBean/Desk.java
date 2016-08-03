package com.tianli.chenhui.javaBean;

import com.tianli.chenhui.tcptools.TcpClientSocket;

public class Desk {
	public DeskState state;
	public int deskNum;
	public TcpClientSocket blackClient;
	public TcpClientSocket whiteClient;
	public Desk(){
		//this.state=DeskState.Empty;
	}
}
