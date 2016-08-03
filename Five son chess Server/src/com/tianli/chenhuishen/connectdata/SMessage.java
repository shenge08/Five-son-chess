package com.tianli.chenhuishen.connectdata;

import java.io.Serializable;

public class SMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 261339869824180794L;
	public CommunicateParam type;
	public String useName;
	public String usePwd;
	public String Info;
	public boolean isSuccess;
	public boolean isTarget;
	
	public String getInfo() {
		return Info;
	}
	public void setInfo(String errorInfo) {
		this.Info = errorInfo;
	}
	public SMessage(CommunicateParam type,String useName,String usePwd){
		this.type=type;
		this.useName=useName;
		this.usePwd=usePwd;
		this.isSuccess=false;
		this.isTarget=false;
	}
}
