package com.tianli.chenhuishen.View;

import javax.swing.JFrame;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.tianli.chenhuishen.connectdata.SMessage;
import com.tianli.chenhuishen.listener.FrameControlAgent;
import com.tianli.chenhuishen.listener.ResponseListener;

public class GameFrame extends JFrame implements  ResponseListener{
	private FrameControlAgent fca;
	
	public GameFrame(){
		this.setSize(1000,700);
		this.setLayout(null);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initSubViews();
	}

	
	
	
	
	
	private void initSubViews() {
		
		
	}






	@Override
	public void handleResponseData(SMessage msg) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		GameFrame gameFrame=new GameFrame();
		gameFrame.setVisible(true);
	}
}
