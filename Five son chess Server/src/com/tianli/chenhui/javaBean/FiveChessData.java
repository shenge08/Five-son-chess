package com.tianli.chenhui.javaBean;

import com.tianli.chenhui.javaBean.Desk;

public class FiveChessData {
	public Desk[] desks;
	public FiveChessData(){
		desks=new Desk[16];
		initData();
	}
	
	private void initData() {
		for(int i=0;i<desks.length;i++){
			desks[i]=new Desk();
			desks[i].state=DeskState.Empty;
			desks[i].deskNum=i;
		}
	}
}
