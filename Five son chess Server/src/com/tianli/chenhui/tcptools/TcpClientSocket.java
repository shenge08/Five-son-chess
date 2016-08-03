package com.tianli.chenhui.tcptools;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;



/**
 * TCP����ͨ����
 * @author chenhuishen
 */
public class TcpClientSocket implements Runnable{
	private  Socket 				socket;
	private  OutputStream 			out;
	private  ObjectOutputStream 	oos;
	private  InputStream  			in;
	private  ObjectInputStream		ois;
	private  Thread 				thread;
	private  HandleDataAgent        handle;
	
	public HandleDataAgent getHandleAgent(){
		return handle;
	}
	
	public TcpClientSocket(Socket socket) throws IOException {
		if(socket!=null){
			this.socket=socket;
			this.out=this.socket.getOutputStream();//����ֽ���
			this.oos=new ObjectOutputStream(out);
			this.in=this.socket.getInputStream();//�����ֽ���
			this.ois=new ObjectInputStream(in);
			this.thread=new Thread(this);
			this.thread.start();
		}
	}

	public void addAgent(HandleDataAgent handle){
		this.handle=handle;
	}
	/**
	 * �ͻ���ʹ��
	 * @param ip ip��ַ
	 * @param port �˿�
	 * @return ClientSocket
	 * @throws IOException
	 */
	public static TcpClientSocket getClientSocket(String ip,int port) throws IOException {
		return new TcpClientSocket(new Socket(ip,port));
		
	}

    public void send(Object o) throws IOException{
    	this.oos.writeObject(o);
    	this.oos.flush();
    }
	
	
	
	
	@Override
	public void run() {
		try{
			while(true){
				Object msg=this.ois.readObject();
				if(this.handle!=null){
					SocketEvent e=new SocketEvent(this.socket.getInetAddress().getHostAddress(),this.socket.getPort(),msg,this);
					handle.handleData(e);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try{
			this.oos.close();
			this.ois.close();
			this.socket.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
