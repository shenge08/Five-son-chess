package com.tianli.chenhuishen.listener;

import com.tianli.chenhuishen.connectdata.SMessage;


public interface ResponseListener {
	public void handleResponseData(SMessage msg);
}
