package com.clostar.utils;

import java.util.Locale;
import java.util.Map;

/*TODO - change comments*/
/**
 * Class which manages the default configurations on the application level. 
 * It also gives acess to the code values defined in database for the current application. 
 * This class can be extended to reach some particular needs of an application.
 * 
 */
public class DefaultConfigs {
    /**
     * relative path of the images folder for an web application
     */
    public static final String IMG_PATH = "img";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String DATE_TIME_WITH_SECS_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_TIME_WITH_SECS_ORACLE_FORMAT = "dd/MM/yyyy  HH24:mi:ss";
    public static final String TIME_FORMAT = "HH:mm";
    /** the period for the refresh of the code values from the database (ms) */
    public static final int CODES_REFRESH_PERIOD = 3600000;
    /** the number of result diplayed on a page */
    public static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * the application system path
     */
    private static String appPath;
    /**
     * the language of the app
     */
    private static String appLang;
    /**
     * the locale of the application
     */
    private static Locale appLocale;
    /**
     * tell if the application has an associated menu
     */
    private static boolean menuEnabled;
    private static boolean loginEnabled;
    private static Map<String, String> properties = null;

    /**
     * the map of map codes
     */
    //private static Map<Integer, Collection<DictionaryEntry>> allCodesListMap = new HashMap<Integer, Collection<CacheElement>>();

    /**
     * the map of map codes
     */
    //private static Map<Integer, Collection<CacheElement>> activeCodesListMap = new HashMap<Integer, Collection<CacheElement>>();

    /**
     * Accessor to application path
     * 
     * @return the application path
     */
    public static String getApplicationPath() {
        return appPath;
    }

    /**
     * Tool to set and modify the application path
     * 
     * @param path
     *            - the application path
     */
    static void setApplicationPath(String path) {
        appPath = path;
    }

    /**
     * @return - this application language
     */
    public static String getApplicationLang() {
        return appLang;
    }

    /**
     * Setter for the application language
     * 
     * @param lang
     *            - the language
     */
    public static void setApplicationLang(String lang) {
        appLang = lang;
        appLocale = new Locale(lang);
    }

    /**
     * @return - the {@link Locale} instance for the locale of this application
     */
    public static Locale getApplicationLocale() {
        return appLocale;
    }

    /**
     * Tells if the application has an interface menu asociated with, and if
     * this is enabled/displayed
     * 
     * @return application menu state 
     */
    public static boolean isMenuEnabled() {
        return menuEnabled;
    }

    /**
     * Setter for the application menu state 
     * @param menuEnabled {@link Boolean} with the state of the applicaiton menu, to be set.
     */
    static void setMenuEnabled(boolean menuEnabled) {
        DefaultConfigs.menuEnabled = menuEnabled;
    }

    /**
     * Tells if the web applicaiton login is enabled 
     * @return {@link Boolean}
     */
    public static boolean isLoginEnabled() {
        return loginEnabled;
    }

    /**
     * Setter for the login state for the web application
     * @param loginEnabled
     */
    static void setLoginEnabled(boolean loginEnabled) {
        DefaultConfigs.loginEnabled = loginEnabled;
    }
    
    static synchronized void setProperties(Map<String, String> properties) {
    	DefaultConfigs.properties = properties;
    }

    /**
     * Gets the active codes
     * 
     * @param codesType
     *            - the type of codes values wanted
     * @return - a {@link Collection} of the wanted code types
     */
//    public static Collection<CacheElement> getActiveCodes(Integer codesType) {
//        return activeCodesListMap.get(codesType);
//    }

    /**
     * Gets all the codes
     * 
     * @param codesType
     *            - the type of codes wanted
     * @return - a {@link Collection} of the wanted code types
     */
//    public static Collection<CacheElement> getAllCodes(Integer codesType) {
//        return allCodesListMap.get(codesType);
//    }

    /**
     * Retrieves first the list of code values coresponding to the specified
     * codeType. then it searches it to retrieve the codeName coresponding to
     * the specified codeId regarding if the code is active or not
     * 
     * @param codeType
     *            - the codeType to retrieve the list from the code types map
     * @param codeId
     *            - the codeId to search the codeName by
     * @return - the <code>CodeValue</code> corespondin to the specified
     *         codeType and codeId
     */
//    public static CacheElement getCodeName(Integer codeType, Integer codeId) {
//        CacheElement wantedCode = null;
//
//        Collection<CacheElement> codes = getAllCodes(codeType);
//
//        for (CacheElement code : codes) {
//            if (code.getValueId().equals(codeId)) {
//                wantedCode = code;
//                break;
//            }
//        }
//
//        return wantedCode;
//    }

    /**
     * Iterates over the provided list of code values and loads the active codes
     * and all codes maps
     * 
     * @param DBCodes
     *            - the {@link Collection} containing the codes from DB
     * @param key
     *            - the key under the list of codes will be put on the list maps
     */
//    public static void loadCodesInListMap(Collection<CacheElement> DBCodes, Integer key) {
//        Collection<CacheElement> activeCodesList = new ArrayList<CacheElement>();
//        Collection<CacheElement> allCodes = new ArrayList<CacheElement>();
//
//        for (CacheElement currentCode : DBCodes) {
////	        	logger.trace("Adding currentCode "+currentCode+" in the codes lists; active indicator "+currentCode.getValueActiveInd());
//            allCodes.add(currentCode);
//
//            if (currentCode.getValueActiveInd() == Constants.ActiveIndicators.ACTIVE) {
//                activeCodesList.add(currentCode);
//            }
//        }
//
//        allCodesListMap.put(key, allCodes);
//        activeCodesListMap.put(key, activeCodesList);
//    }

    /**
     * Getter for the version of the application, as it is configured
     * @return {@link String} with the version
     */
    public static String getVersion() {
        try {
            return Config.getVersion();
        } catch (Exception ie) {
            return null;
        }
    }
    
    public static synchronized String getProperty(String propertyName) {
    	if (DefaultConfigs.properties.containsKey(propertyName)) {
    		return DefaultConfigs.properties.get(propertyName);
    	}
    	return null;
    }
}