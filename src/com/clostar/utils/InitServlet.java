package com.clostar.utils;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.clostar.utils.Constants;
import com.opensymphony.xwork2.ActionContext;

public class InitServlet extends GenericServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        // init app path
        DefaultConfigs.setApplicationPath(getServletContext().getRealPath("/"));

        try {
            // init lang
        	DefaultConfigs.setApplicationLang(Config.getLang());
        	ActionContext.getContext().setLocale(DefaultConfigs.getApplicationLocale());
        	ActionContext.getContext().getSession().put(Constants.USER_ID, null);
        	
        } catch (Exception ie) {
            throw new ServletException(ie);
        }
    }

    /**
     * A call to this method, throws exception, because the role of this servlet
     * is only for initialization
     */
    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        throw new ServletException("Sorry, this servlet has only init role !!");
    }
    
}
