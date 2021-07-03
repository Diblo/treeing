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

import org.xeustechnologies.esl4j.LogFactory;
import org.xeustechnologies.esl4j.Logger;

/**
 * A Do-not-log LogFactory
 * 
 * @author Kamran
 * 
 */
public class NoLogFactory implements LogFactory {

    public Logger getLogger(Class clazz) {
        return new NoLogger();
    }

    public static final class NoLogger implements Logger {

        public void debug(String message) {
        }

        public void debug(String msg, Throwable error) {
        }

        public void debug(Throwable error) {
        }

        public void error(String message) {
        }

        public void error(String msg, Throwable error) {
        }

        public void error(Throwable error) {
        }

        public void info(String message) {
        }

        public void info(String msg, Throwable error) {
        }

        public void info(Throwable error) {
        }

        public boolean isDebugEnabled() {
            return false;
        }

        public boolean isErrorEnabled() {
            return false;
        }

        public boolean isInfoEnabled() {
            return false;
        }

        public boolean isTraceEnabled() {
            return false;
        }

        public boolean isVerboseEnabled() {
            return false;
        }

        public boolean isWarnEnabled() {
            return false;
        }

        public void trace(String message) {

        }

        public void trace(String msg, Throwable error) {

        }

        public void trace(Throwable error) {

        }

        public void warn(String message) {

        }

        public void warn(String msg, Throwable error) {

        }

        public void warn(Throwable error) {

        }

        public void verbose(String message) {
        }

        public void verbose(String msg, Throwable error) {
        }

        public void verbose(Throwable error) {
        }
    }

    public void setProperties(Map<String, String> props) {
    }
}
