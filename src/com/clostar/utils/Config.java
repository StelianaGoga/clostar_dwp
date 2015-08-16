package com.clostar.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class handles the configurations provided with a config.xml file, for an
 * web application, and provides access to the configured values.
 * 
 */
public class Config {
	private final static Logger logger = LoggerFactory.getLogger(Config.class);

    private static boolean parsed;

    private static Document configDocument;

    private static Element root;
    
    private static Map<String, String> properties = null;

    /**
     * Parse the config.xml file obtaining {@link Element} and {@link Document}
     * objects exposed by the class through getter methods 
     * 
     * @throws InitException
     */
    @SuppressWarnings("unchecked")
	protected static synchronized void parse() throws Exception {
        if (!parsed) {
            java.net.URL configURL = Config.class.getResource("/config.xml");

            try {
                configDocument = XMLUtil.loadDocument(configURL);
                
            } catch (DocumentException de) {
                logger.error(" parse error ", de);
                throw new Exception(de);
            }

            root = configDocument.getRootElement();
            
            if (root != null) {
            	Map<String, String> localProperties = new HashMap<String, String>();
            	for (Iterator<Element> i = root.elementIterator(); i.hasNext(); ) {
                	Element element = i.next();
                	localProperties.put(element.getName(), element.getTextTrim());
                }
            	properties = Collections.unmodifiableMap(localProperties);
            }
            parsed = true;
        }
    }

    /**
     * Prints the configDocument object (class member) to an outputstream
     * "/WEB-INF/classes/config.xml", with "US-ASCII" as encoding scheme for the
     * XML file
     * 
     * @throws InitException
     */
    protected static void serialize() throws Exception {
        try {
            OutputStream out = new FileOutputStream(DefaultConfigs.getApplicationPath() + File.separator + "WEB-INF" + File.separator
                    + "classes" + File.separator + "config.xml");
            XMLUtil.serializetoXML(configDocument, out, "US-ASCII");
        } catch (Exception e) {
            logger.error("serialize error", e);
            throw e;
        }
    }

    /**
     * Getter for the root object of the xml config file
     * 
     * @return {@link Element} object
     */
    public static Element getRoot() {
        return root;
    }

    /**
     * Getter for the {@link Document} object of the xml config file
     * 
     * @return {@link Document} object
     */
    public static Document getConfigDocument() {
        return configDocument;
    }

    /**
     * Getter for the value of "lang" property
     * 
     * @return the value configured
     * @throws InitException
     */
    public static String getLang() throws Exception {
        parse();
        Element lang = root.element("lang");
        return lang.getText();
    }

    /**
     * Getter for the value of "menu-enabled" property
     * 
     * @return the value configured
     * @throws InitException
     */
    public static String getMenuEnabled() throws Exception {
        parse();
        Element menuEnabled = root.element("menu-enabled");
        return menuEnabled.getText();
    }

    /**
     * Getter for the value of "login-enabled" property
     * 
     * @return the value configured
     * @throws InitException
     */
    public static String getLoginEnabled() throws Exception {
        parse();
        Element loginEnabled = root.element("login-enabled");
        return loginEnabled.getText();
    }

    /**
     * Getter for the value of "version" property
     * 
     * @return the value configured
     * @throws InitException
     */
    public static String getVersion() throws Exception {
        parse();
        Element version = root.element("version");
        return version.getText();
    }
    
    public static synchronized Map<String, String> getProperties() {
    	return Config.properties;
    }
}
