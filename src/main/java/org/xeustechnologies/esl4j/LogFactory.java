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

import java.util.Map;

/**
 * @author Kamran
 * 
 */
public interface LogFactory {

    /**
     * Returns the Logger instance
     * 
     * @param clazz
     * @return Logger
     */
    public Logger getLogger(Class clazz);

    /**
     * Set any factory specific properties.
     * 
     * The properties can also be set in the esl4j.properties file. Below is an
     * example on how to set the properties in the config file:
     * 
     * <pre>
     *   org.xeustechnologies.esl4j.LogFactory=myapp.MyLogFactory
     *   myapp.MyLogFactory.property1=value1
     *   myapp.MyLogFactory.property2=value2
     * </pre>
     * 
     * @param props
     */
    public void setProperties(Map<String, String> props);
}