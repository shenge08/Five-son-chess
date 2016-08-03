package com.tianli.chenhuishen.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import com.tianli.chenhuishen.enums.FrameChangeParam;
import com.tianli.chenhuishen.listener.CommuicateAgent;
import com.tianli.chenhuishen.listener.FrameControlAgent;
import com.tianli.chenhuishen.listener.ResponseListener;
import com.tianli.chenhuishen.connectdata.CommunicateParam;
import com.tianli.chenhuishen.connectdata.SMessage;


public class LoginFrame extends JFrame implements ActionListener ,ResponseListener{
	
	
		/**
		 * 
		 */
		private static final long serialVersionUID = -3533278001754025857L;
		
		private JButton btn_exit;	  							//�˳���ť
		private JButton btn_login;    							//���밴ť
		private JButton btn_regist;    							//ע�ᰴť
		
		private JTextField txt_name;  							//���������
		private JPasswordField txt_pwd;							//���������
		
		private JLabel lab_pwd;									//�����ǩ
		private JLabel lab_name;								//������ǩ
		
		private CommuicateAgent cl;
		private FrameControlAgent fca;
		
		
		/***
		 * ���캯��
		 * ��ʼ�����ں��ӿؼ�
		 */
		public LoginFrame(){
			this.setSize(300,300);
			this.setLayout(null);
			this.setResizable(true);
			this.setLocationRelativeTo(null);
			this.setTitle("my second ui java");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.InitSubView();
		 }
	
		
		public void addCommunicateAndFrameControlAgent(CommuicateAgent cl,FrameControlAgent fca){
			this.cl=cl;
			this.fca=fca;
		}
		


	     /***
		 * ˽�� ��ʼ���ӿؼ�����
		 * 
		 */
		
		
		
		private void InitSubView(){
			this.btn_login=new JButton("login");
			this.btn_login.setBounds(15,200,75,30);
			this.btn_login.addActionListener(this);
			
			this.btn_exit=new JButton("exit");
			this.btn_exit.setBounds(105,200,75,30);
			this.btn_exit.addActionListener(this);
			
			this.btn_regist=new JButton("regist");
			this.btn_regist.setBounds(195,200,75,30);
			this.btn_regist.addActionListener(this);
			
			this.txt_name=new JTextField("chen");
			this.txt_name.setBounds(100, 105, 150, 20);
			
			this.txt_pwd=new JPasswordField("1234");
			this.txt_pwd.setBounds(100, 140, 150, 20);
			
			this.lab_name=new JLabel("�˺ţ�");
			this.lab_name.setBounds(50,105,40,20);
			
			this.lab_pwd=new JLabel("���룺");
			this.lab_pwd.setBounds(50,140,40,20);
			
			this.add(this.btn_login);
			this.add(this.btn_exit);
			this.add(this.btn_regist);
			this.add(this.lab_pwd);
			this.add(this.lab_name);
			this.add(this.txt_pwd);
			this.add(this.txt_name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this.btn_login){
				SMessage loginMessage=new SMessage(CommunicateParam.LOGIN,this.txt_name.getText(),
						this.txt_pwd.getText()); 
				cl.sendInformation(loginMessage);
				//HallFrame hf=new HallFrame();
				//fca.changeFrame(FrameChangeParam.ToHallFrame);
				
				/*if(re.isSuccess){
					HallFrame hf=new HallFrame();
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(this.getContentPane(),
							"�˺��������򲻴���!", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}*/
			}else if(e.getSource()==this.btn_exit){
				System.exit(0);
			}else if(e.getSource()==this.btn_regist){
				/*	ResultEvent re=cl.sendInformation(CommunicateParam.REGIST);
					if(re.isSuccess){
						JOptionPane.showMessageDialog(this.getContentPane(),
								"ע��ɹ�!", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(this.getContentPane(),
								"ע��ʧ��!", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					}*/
			}
			
		}


		@Override
		public void handleResponseData(SMessage msg) {
			// TODO Auto-generated method stub
			if(msg.type==CommunicateParam.LOGIN){
				if(msg.isSuccess){
					fca.changeFrame(FrameChangeParam.ToHallFrame);
					this.dispose();
				}
			}
			
		}

		

	}


