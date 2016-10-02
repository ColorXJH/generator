/**
 *    Copyright 2006-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.eclipse.tests.harness;

import static org.mybatis.generator.eclipse.core.merge.EclipseDomUtils.getCompilationUnitFromSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Type;
import org.mybatis.generator.eclipse.core.merge.visitors.TypeStringifier;
import org.mybatis.generator.eclipse.tests.harness.summary.CompilationUnitSummary;

public class Utilities {
    public static CompilationUnitSummary getCompilationUnitSummaryFromResource(InputStream resource) throws Exception {
        String javaSource = getResourceAsString(resource);
        return getCompilationUnitSummaryFromSource(javaSource);
    }

    public static CompilationUnitSummary getCompilationUnitSummaryFromSource(String javaSource) throws Exception {
        CompilationUnit cu = getCompilationUnitFromSource(javaSource);
        CompilationUnitSummary summary = CompilationUnitSummary.from(cu);
        return summary;
    }

    public static String getResourceAsString(InputStream resource) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(resource));
        StringBuilder sb = new StringBuilder();
        
        int c;
        while ((c = br.read()) != -1) {
            sb.append((char) c);
        }
        
        br.close();
        
        return sb.toString();
    }

    public static CompilationUnit getCompilationUnitFromResource(InputStream resource) throws IOException {
        String javaSource = getResourceAsString(resource);
        return getCompilationUnitFromSource(javaSource);
    }

    public static <T> T newInstance(Class<? extends T> type) {
        try {
            T instance = type.newInstance();
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("error creating instance of class " + type.getName(), e);
        }
    }
    
    public static String getTypeString(Type type) {
        TypeStringifier ts = new TypeStringifier();
        type.accept(ts);
        return ts.toString();
    }
}
