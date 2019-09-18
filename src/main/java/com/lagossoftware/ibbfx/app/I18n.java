/*
 * Copyright (C) 2016 IBB Management Project, Cem Ikta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lagossoftware.ibbfx.app;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Resource Bundle helper.
 *
 * <p>
 * Each module has its own resource bundle file for i18n strings. Always read
 * default locale from <code>Locale.getDefault()</code>.
 * <p>
 * <pre>
 *      I18n.MODULE_NAME.getString("stringKey");
 * </pre>
 *
 * @see ResourceBundle
 *
 * @author Cem Ikta
 */
public enum I18n {

    /**
     * Components module
     */
    CONTROL("control"),
    
    /**
     * Common module
     */
    COMMON("common"),
    
    /**
     * Adresse module
     */
    ADRESSE("adresse"),

    /**
     * Eingangsrechnung module
     */
    EINGANGSRECHNUNG("eingangsrechnung"),

    /**
     * Planung module
     */
    PLANUNG("planung"),

    /**
     * Fahrtkasse module
     */
    FAHRTKASSE("fahrtkasse"),

    /**
     * Grundlagen module
     */
    GRUNDLAGEN("grundlagen"),

    /**
     * Settings module
     */
    SETTINGS("settings");

    private final ResourceBundle resourceBundle;
    private static final String DEFAULT_LOCATION = "i18n.";
    private final static Logger logger = Logger.getLogger(I18n.class.getName());

    I18n(String bundleFile) {
        resourceBundle = ResourceBundle.getBundle(DEFAULT_LOCATION + bundleFile);
    }

    /**
     * Gets a string for the given key from resource bundle.
     *
     * @param key the key for the desired string
     * @return the string for the given key
     */
    public String getString(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException ex) {
            logger.log(Level.SEVERE, null, ex);
            return "err#";
        }
    }
    
    /**
     * Gets a string for the given key from resource bundle and formats 
     * with parameters.
     *
     * @param key the key for the desired string
     * @param params parameters for message formats
     * @return the string for the given key
     */
    public String getString(String key, Object... params) {
        try {
            return MessageFormat.format(resourceBundle.getString(key), params);            
        } catch (MissingResourceException ex) {
            logger.log(Level.SEVERE, null, ex);
            return "err#";
        }
    }

}
