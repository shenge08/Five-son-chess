package com.tianli.chenhuishen.View;

import javax.swing.JButton;

import com.tianli.chenhuishen.enums.DeskState;

public class DeskButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2626278621515693221L;
	private int deskNum;
	private DeskState state;
	public DeskButton(int deskNum){
		this.deskNum=deskNum;
		state=DeskState.Empty;
	}
	public int getDeskNum() {
		return deskNum;
	}
	public void setDeskNum(int deskNum) {
		this.deskNum = deskNum;
	}
	public DeskState getState() {
		return state;
	}
	
	public void setState(DeskState state) {
		this.state = state;
	}
	public void setState(int stateNum) {
		if(stateNum==0){
			this.state=DeskState.Empty;
		}else if(stateNum==1){
			this.state=DeskState.One;
		}else if(stateNum==2){
			this.state=DeskState.Full;
		}else{
			
		}
	}

}
