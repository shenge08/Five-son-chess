package com.tianli.chenhuishen.View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.tianli.chenhuishen.listener.CommuicateAgent;
import com.tianli.chenhuishen.listener.FrameControlAgent;
import com.tianli.chenhuishen.connectdata.CommunicateParam;
import com.tianli.chenhuishen.connectdata.SMessage;
import com.tianli.chenhuishen.enums.DeskState;

public class DeskPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1485253469212498236L;
	public DeskButton[] desks;
	public ImageIcon[] imageIcons;
/*	public boolean isChoose;
	public int isChooseNum;*/

	
	private CommuicateAgent ca;
	
	public void addCummnicateAgent(CommuicateAgent ca){
		this.ca=ca;
	}
	
	public void getDeskDetail(){
		SMessage msg=new SMessage(CommunicateParam.DESK_DETAL, null, null);
		ca.sendInformation(msg);
	}
	
	public DeskPanel(ImageIcon[] imageIcons){
		this.setBounds(0, 0, 600, 600);
		

		//this.setBackground(Color.black);
		this.desks=new DeskButton[16];
	/*	this.isChoose=false;
		this.isChooseNum=-1;*/
		this.setLayout(new GridLayout(4,4));
		for (int i=0;i<desks.length;i++) {
			desks[i]=new DeskButton(i);
			desks[i].setBounds(0,0,300,300);
			desks[i].setIcon(imageIcons[0]);
			desks[i].addActionListener(this);
			this.add(desks[i]);
		}
	}

	private void getDeskInfo() {
		SMessage msg=new SMessage(CommunicateParam.DESK_DETAL, null, null);
		ca.sendInformation(msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DeskButton btn_temp=(DeskButton)e.getSource();
		int num=btn_temp.getDeskNum();
		SMessage s=new SMessage(CommunicateParam.CHOOSE_DESK, null, null);
		s.Info=num+"";
		ca.sendInformation(s);
		/*DeskState state=btn_temp.getState();
		if(!isChoose && (state != DeskState.Full) ){
			SMessage s=new SMessage(CommunicateParam.CHOOSE_DESK, null, null);
			s.Info=num+"";
			ca.sendInformation(s);
		}else if(isChoose && isChooseNum==num && btn_temp.getState()==DeskState.One){
			SMessage s=new SMessage(CommunicateParam.ABNDON_DESK, null, null);
			s.Info=num+"";
			ca.sendInformation(s);
		}else{
			
		}*/
		
	}
	
}
