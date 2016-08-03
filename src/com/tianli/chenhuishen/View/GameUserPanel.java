package com.tianli.chenhuishen.View;

import java.awt.Color;

import javax.swing.JPanel;

public class GameUserPanel extends JPanel {
	public String userType;
	public String userName;
	
	public GameUserPanel(){
		this.setBounds(600, 0, 150, 600);
		this.setLayout(null);
		this.setBackground(Color.yellow);
	}
}
