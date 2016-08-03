package com.tianli.chenhuishen.tools;


/**
 * Socket����ͨ�ż����¼������ݲ���
 * @author csh
 */
public class SocketEvent {
	private String ip="";//�ͻ���Socket��ӦIP��ַ
	private int port=0;//�ͻ���Socket��Ӧ�˿ں�
	private Object info="";//�ͻ���Socket���ݵ���Ϣ
	private Object source=null;//����ͨ���¼����¼�Դ
	
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
