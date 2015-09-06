package com.clostar.interceptors;

import java.util.Date;

import com.clostar.utils.Constants;
import com.clostar.business.SessionManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class SignedInTimeout extends AbstractInterceptor {


	@Override
	public String intercept(ActionInvocation invocation)throws Exception{
		
		SessionManager sm = new SessionManager(ActionContext.getContext());
		
		if (sm.getValueKey(Constants.USER_ID) != null) {
			Long startTime = (Long) sm.getValueKey(Constants.TIME);
			Long nowTime = new Date().getTime();
			// 15 minutes
			if (startTime != null && nowTime - startTime > 1000 * 60 * 15) {
				sm.removeSession();
			}
			else if (startTime != null){
				sm.putKey(Constants.TIME, nowTime);
			}
		}
		
		String result = invocation.invoke();		
		return result;
	}
	
}