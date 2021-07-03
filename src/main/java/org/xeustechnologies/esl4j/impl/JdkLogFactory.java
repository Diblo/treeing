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

package org.xeustechnologies.esl4j.impl;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.xeustechnologies.esl4j.LogFactory;
import org.xeustechnologies.esl4j.Logger;

/**
 * The JDK logging LogFactory
 * 
 * @author Kamran
 * 
 */
public class JdkLogFactory implements LogFactory {

    public Logger getLogger(Class clazz) {
        return new JdkLogger( java.util.logging.Logger.getLogger( clazz.getName() ) );
    }

    public static final class JdkLogger implements Logger {

        private final java.util.logging.Logger logger;

        public JdkLogger(java.util.logging.Logger logger) {
            this.logger = logger;
        }

        public void log(String message, Level level) {
            if( logger.isLoggable( level ) ) {
                LogRecord log = create( level, message );
                logger.log( log );
            }
        }

        public void log(String message, Throwable error, Level level) {
            if( logger.isLoggable( level ) ) {
                LogRecord log = create( level, message );
                log.setThrown( error );
                logger.log( log );
            }
        }

        public void log(Throwable error, Level level) {
            if( logger.isLoggable( level ) ) {
                LogRecord log = create( level, "" );
                log.setThrown( error );
                logger.log( log );
            }
        }

        private LogRecord create(Level level, String message) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();

            String logSourceClassName = null;
            String logSourceMethodName = null;

            int logSourceFrame = 3;
            if( logSourceFrame < stackTrace.length ) {
                StackTraceElement logSource = stackTrace[logSourceFrame];
                if( logSource != null ) {
                    logSourceClassName = logSource.getClassName();
                    logSourceMethodName = logSource.getMethodName();
                }
            }

            LogRecord log = new LogRecord( level, message );
            log.setSourceClassName( logSourceClassName == null ? "Unknown" : logSourceClassName );
            log.setSourceMethodName( logSourceMethodName == null ? "Unknown" : logSourceMethodName );

            return log;
        }

        public void debug(String message) {
            log( message, Level.FINER );
        }

        public void debug(String message, Throwable error) {
            log( message, error, Level.FINER );
        }

        public void debug(Throwable error) {
            log( error, Level.FINER );
        }

        public void error(String message) {
            log( message, Level.SEVERE );
        }

        public void error(String message, Throwable error) {
            log( message, error, Level.SEVERE );
        }

        public void error(Throwable error) {
            log( error, Level.SEVERE );
        }

        public void info(String message) {
            log( message, Level.INFO );
        }

        public void info(String message, Throwable error) {
            log( message, error, Level.INFO );
        }

        public void info(Throwable error) {
            log( error, Level.INFO );
        }

        public boolean isDebugEnabled() {
            return logger.isLoggable( Level.FINER );
        }

        public boolean isErrorEnabled() {
            return logger.isLoggable( Level.SEVERE );
        }

        public boolean isInfoEnabled() {
            return logger.isLoggable( Level.INFO );
        }

        public boolean isTraceEnabled() {
            return logger.isLoggable( Level.FINEST );
        }

        public boolean isVerboseEnabled() {
            return logger.isLoggable( Level.FINE );
        }

        public boolean isWarnEnabled() {
            return logger.isLoggable( Level.WARNING );
        }

        public void trace(String message) {
            log( message, Level.FINEST );
        }

        public void trace(String message, Throwable error) {
            log( message, error, Level.FINEST );
        }

        public void trace(Throwable error) {
            log( error, Level.FINEST );
        }

        public void warn(String message) {
            log( message, Level.WARNING );
        }

        public void warn(String message, Throwable error) {
            log( message, error, Level.WARNING );
        }

        public void warn(Throwable error) {
            log( error, Level.WARNING );
        }

        public void verbose(String message) {
            log( message, Level.FINE );
        }

        public void verbose(String message, Throwable error) {
            log( message, error, Level.FINE );
        }

        public void verbose(Throwable error) {
            log( error, Level.FINE );
        }
    }

    public void setProperties(Map<String, String> props) {
    }
}
