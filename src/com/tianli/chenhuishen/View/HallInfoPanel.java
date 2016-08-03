package com.tianli.chenhuishen.View;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tianli.chenhuishen.listener.CommuicateAgent;

public class HallInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7798845800997778419L;
	private JLabel lab_personNum;
	public JLabel lab_num;
	
	private CommuicateAgent ca;
	
	public void addCummnicateAgent(CommuicateAgent ca){
		this.ca=ca;
	}
	
	public HallInfoPanel(){
		initSubviews(0);
		this.setBounds(600, 0, 150, 600);
		this.setLayout(null);
		this.setBackground(Color.yellow);
	}

	private void initSubviews(int i) {
		this.lab_personNum=new JLabel("在线人数");
		this.lab_personNum.setBounds(37,175,80,30);
		this.add(this.lab_personNum);
		this.lab_num=new JLabel("0");
		this.lab_num.setBounds(60,210,80,30);
		this.add(this.lab_num);
		
	}

}
