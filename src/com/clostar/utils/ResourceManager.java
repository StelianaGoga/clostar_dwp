package com.clostar.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class which manages ResouceBundle with a multilanguage dictionary messages
 */
public class ResourceManager {
    private static final Object lockObject = new Object();

    /**
     * Compose a message stored in the dictionary (for the current application language)
     * 
     * @param templateKey
     *            {@link String} with the key of the message from the dictionary
     * @param args
     *            {@link Object}[] with the massage parameters to be replaced
     * @return {@link String} with the composed message
     */
    public static String composeMessage(String templateKey, Object[] args) {
        MessageFormat mf = new MessageFormat(getString(templateKey));
        return mf.format(args);
    }

    /**
     * Gets a message from the dictionary (for the current application language)
     * 
     * @param key
     *            {@link String} with the key of the message from the dictionary
     * @return {@link String} with the message from dictionary
     */
    public static String getString(String key) {
        synchronized (lockObject) {
            ResourceBundle bundle = ResourceBundle.getBundle("dictionary", new Locale(DefaultConfigs.getApplicationLang()));
            return bundle.getString(key);
        }
    }
    
    /**
     * Compose a message stored in the dictionary (for the current application language)
     * 
     * @param templateKey
     *            {@link String} with the key of the message from the dictionary
     * @param args
     *            {@link Object}[] with the massage parameters to be replaced
     * @return {@link String} with the composed message
     */
    public static String composeMessage(String templateKey, Object[] args, Locale lang) {
        MessageFormat mf = new MessageFormat(getString(templateKey, lang));
        return mf.format(args);
    }

    /**
     * Gets a message from the dictionary (for the current application language)
     * 
     * @param key
     *            {@link String} with the key of the message from the dictionary
     * @return {@link String} with the message from dictionary
     */
    public static String getString(String key, Locale lang) {
        synchronized (lockObject) {
            ResourceBundle bundle = ResourceBundle.getBundle("dictionary", lang);
            return bundle.getString(key);
        }
    }
}
