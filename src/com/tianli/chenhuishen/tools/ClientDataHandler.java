package com.tianli.chenhuishen.tools;

import java.util.LinkedList;
import java.util.List;

import javax.xml.ws.Response;

import com.tianli.chenhuishen.connectdata.SMessage;
import com.tianli.chenhuishen.listener.ResponseListener;


public class ClientDataHandler implements HandleDataAgent{
    private List<ResponseListener> listeners;
    
    
    public ClientDataHandler() {
    	this.listeners=new LinkedList<ResponseListener>();
	}
	
  private void ListenerHandleData(SMessage msg){
	
		 for (int i=0;i<listeners.size(); i++) {
			   if(listeners.get(i)!=null)
				   listeners.get(i).handleResponseData(msg);
		}
	   
   }
	

   public void addListener(ResponseListener rl){
	   listeners.add(rl);
   }
    
    
    @Override
	public void handleData(SocketEvent e) {
		SMessage msg=(SMessage)e.getInfo();
		this.ListenerHandleData(msg);
	}

	@Override
	public void exit(SocketEvent e) {
		// TODO Auto-generated method stub
		
	}

}
