/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.karaf.diagnostic.common;

import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import org.apache.karaf.diagnostic.core.common.TextDumpProvider;

/**
 * Provider which dumps thread info to file named threads.txt.
 * 
 * @author ldywicki
 */
public class ThreadDumpProvider extends TextDumpProvider {

    /**
     * Creates new dump entry which contains information about threads.
     */
    public ThreadDumpProvider() {
        super("threads.txt");
    }

    @Override
    protected void writeDump(OutputStreamWriter outputStream) throws Exception {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        outputStream.write("Number of threads: " + threadMXBean.getDaemonThreadCount() + "\n");
        ThreadInfo[] threadDump = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : threadDump) {
            outputStream.write(threadInfo.toString() + "\n\n");
        }
    }

}
