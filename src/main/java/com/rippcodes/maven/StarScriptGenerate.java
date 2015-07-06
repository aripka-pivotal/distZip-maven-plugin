package com.rippcodes.maven;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

@Mojo( name = "generate-start-script", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class StarScriptGenerate
    extends AbstractMojo
{
    /**
     * Location of the file.
     */
    @Parameter( defaultValue = "${project.build.directory}/start-scripts", property = "outputDir", required = true )
    private File outputDirectory;

    @Parameter(property = "mainClassName", required = true )
    private String mainClassName;

    public void execute()
        throws MojoExecutionException
    {
        File f = outputDirectory;

        if ( !f.exists() )
        {
            f.mkdirs();
        }

        Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
        
        for(PosixFilePermission p:PosixFilePermission.values()){
        	perms.add(p);
        }

        File script = new File( f, "app-start" );
        
        FileWriter w = null;

    	InputStream scriptFile = null;
    	
    	InputStreamReader isr = null;
    	
    	BufferedReader br = null;
        
        try
        {
        	scriptFile = this.getClass().getClassLoader().getResourceAsStream("app-start-a");
        	
        	isr = new InputStreamReader(scriptFile);
        	
        	br = new BufferedReader(isr);
        	
        	w = new FileWriter( script );
        	
        	while(br.ready()){
        		w.write(br.readLine());
        		w.write("\n");
        	}
        	
        	if(br != null){
        		br.close();
        	}
        	
        	if(isr != null){
        		isr.close();
        	}
        	
        	if(scriptFile != null){
        		scriptFile.close();
        	}
        	
            w.write("MAIN_CLASS="+mainClassName+"\n");
            
            scriptFile = this.getClass().getClassLoader().getResourceAsStream("app-start-b");
        	
        	isr = new InputStreamReader(scriptFile);
        	
        	br = new BufferedReader(isr);
        	
        	while(br.ready()){
        		w.write(br.readLine());
        		w.write("\n");
        	}
        	
        	if(br != null){
        		br.close();
        	}
        	
        	if(isr != null){
        		isr.close();
        	}
        	
        	if(scriptFile != null){
        		scriptFile.close();
        	}
        	
        	  Files.setPosixFilePermissions(script.toPath(), perms);
        	
            
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error creating file " + script, e );
        }
        finally
        {
            if ( w != null )
            {
                try
                {
                    w.close();
                }
                catch ( IOException e )
                {
                    // ignore
                }
            }
        }
    }
    
}
