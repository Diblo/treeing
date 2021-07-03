/**
 * Copyright 2010 Xeus Technologies 
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
 * 
 */

package org.xeustechnologies.esl4j;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.xeustechnologies.esl4j.impl.NoLogFactory;

/**
 * @author Kamran
 * 
 */
public class LogManager {
    public static final String CONFIG_BUNDLE = "esl4j";
    private static LogFactory logFactory = null;
    private static final Object lock = new Object();
    private static ResourceBundle bundle;
    private static ClassLoader factoryClassloader;

    /**
     * Returns the configurered LogFactory instance
     * 
     * @return LogFactory
     */
    public static LogFactory getFactory() {
        synchronized (lock) {
            if( logFactory == null )
                createFactory();
        }

        return logFactory;
    }

    private static void createFactory() {
        String className = System.getProperty( LogFactory.class.getName() );
        if( className != null ) {
            LogFactory factory = createFactory( className );
            if( factory != null ) {
                logFactory = factory;
                return;
            }
        }

        bundle = ResourceBundle.getBundle( CONFIG_BUNDLE );
        className = bundle.getString( LogFactory.class.getName() );
        LogFactory factory = createFactory( className );
        if( factory != null ) {
            logFactory = factory;
            return;
        }

        logFactory = new NoLogFactory();
    }

    private static LogFactory createFactory(String className) {
        try {
            if( factoryClassloader == null ) {
                factoryClassloader = Thread.currentThread().getContextClassLoader();
                if( factoryClassloader == null )
                    factoryClassloader = LogManager.class.getClassLoader();
            }

            Class factoryClass = factoryClassloader.loadClass( className );
            Object factory = factoryClass.newInstance();

            if( !( factory instanceof LogFactory ) ) {
                System.err.println( "Class '" + className + "' is not an implementation of "
                        + LogFactory.class.getName() );
                return null;
            }

            LogFactory lf = (LogFactory) factory;
            lf.setProperties( loadProperties( className ) );

            return lf;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    private static Map<String, String> loadProperties(String className) {
        Enumeration<String> e = bundle.getKeys();
        Map<String, String> props = new HashMap<String, String>();

        while(e.hasMoreElements()) {
            String key = e.nextElement();
            if( key.startsWith( className ) ) {
                String val = bundle.getString( key );

                props.put( key.substring( className.length() + 1 ), val );
            }
        }

        return props;
    }

    /**
     * Sets the ClassLoader of the LogFactory implementation
     * 
     * @param ClassLoader
     *            fcl
     */
    public static void setFactoryClassLoader(ClassLoader fcl) {
        factoryClassloader = fcl;
    }

    /**
     * Sets the default LogFactory implementation
     * 
     * @param LogFactory
     *            newFactory
     */
    public static void setFactory(LogFactory newFactory) {
        logFactory = newFactory;
    }

    /**
     * Returns the Logger instance
     * 
     * @param Class
     *            sourceClass
     * @return Logger
     */
    public static Logger getLogger(Class sourceClass) {
        return getFactory().getLogger( sourceClass );
    }
}
