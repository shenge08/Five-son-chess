package com.tianli.chenhuishen.tools;


/**
 * Socket网络通信监听事件，传递参数
 * @author csh
 */
public class SocketEvent {
	private String ip="";//客户端Socket对应IP地址
	private int port=0;//客户端Socket对应端口号
	private Object info="";//客户端Socket传递的消息
	private Object source=null;//网络通信事件的事件源
	
	public SocketEvent() {
		
	}
	
	public SocketEvent(String ip,int port, Object info,Object source) {
		this.ip=ip;
		this.port=port;
		this.info=info;
		this.source=source;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}
}
