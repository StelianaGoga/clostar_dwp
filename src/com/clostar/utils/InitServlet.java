package com.clostar.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

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

//        InputStream fileInputStream = InitServlet.class.getResourceAsStream("/log4j.properties");
//        if (fileInputStream != null) {
//            PropertyConfigurator.configure(fileInputStream);
//        } else {
//            logger.error("the log4j.properties file was not found");
//        }
        
//        Enumeration<?> al = Logger.getRootLogger().getAllAppenders();
//        while(al.hasMoreElements()){
//            Object a = al.nextElement();
//            if(a instanceof Log4jJDBCAppender){
//                Log4jJDBCAppender log4jJDBCAppender = (Log4jJDBCAppender)a;
//                Log4jJDBCAppender.setApplicationNameAndIP(log4jJDBCAppender.getName() + " [" + getAddressIp() + "]");
//            }
//        }

        // init app path
        DefaultConfigs.setApplicationPath(getServletContext().getRealPath("/"));

        try {
            // init lang
        	DefaultConfigs.setApplicationLang(Config.getLang());
        	ActionContext.getContext().setLocale(DefaultConfigs.getApplicationLocale());
        	ActionContext.getContext().getSession().put(Constants.USER_ID, null);
        	
            // login
        	//DefaultConfigs.setLoginEnabled(Boolean.valueOf(Config.getLoginEnabled()));
            
            // properties
        	//DefaultConfigs.setProperties(Config.getProperties());
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
    
    private String getAddressIp(){
        InetAddress addr = null;
        InetAddress addr172 = null;
        try {
            // Replace eth0 with your interface name
            Enumeration<NetworkInterface> nie = NetworkInterface.getNetworkInterfaces();
            while (nie.hasMoreElements()) {
                NetworkInterface ni = nie.nextElement();
                Enumeration<InetAddress> iae = ni.getInetAddresses();
                
                while (iae.hasMoreElements()) {
                    InetAddress ia = iae.nextElement();
                    byte bs[] = ia.getAddress();
                    if (bs.length == 4 && bs[0] != 127) {
                        addr = ia;
                    }
                    if (bs.length == 4 && (bs[0] == 172 || bs[0] == -84)) {
                        addr172 = ia;
                    }
                }

            }
        } catch (Throwable t) { t.printStackTrace(); }
        
        String serverIp = "";
        if(addr172 != null){
            serverIp = addr172.getHostAddress();
        }
        if(addr172==null && addr != null){
            serverIp = addr.getHostAddress();
        }
        return serverIp;
    }
}
