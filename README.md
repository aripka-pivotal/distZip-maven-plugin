# distZip-maven-plugin
This is maven mojo plugin that will generate a start script for distzip style distro that can be used in conjunction with the Maven Assembly plugin (https://maven.apache.org/components/plugins/maven-assembly-plugin/)  to create more efficient artifacts for pushing to cloud foundry.  This is an alternative to the uber jar approach such as that leveraged by the Maven Shade plugin (https://maven.apache.org/plugins/maven-shade-plugin/) where in all included jars are exploded into the uber jar.

At present this plugin uses a template file approach to build the app-start script for unix only.

In order to be used to create a Cloud Foundry compatiable distribution you must also utilize the maven assembly plugin.  See the USAGE INSTRUCTIONS section for a link to a sample project that demonstrates this.


**BUILDING THIS PLUGIN FOR USE**

This plugin is not distributed in a compiled format.  In order to use this plugin you will need to build it and install it into your own maven repository.  

* check out the source code from this git repo
* from the plugin project root directory execute the following:
 

    ` >mvn clean install `

**USAGE INSTRUCTIONS**

This plugin by default runs during the _"process sources"_ Maven lifecycle phase.  Including this plugin in the project definition file will cause it to be executed.

An example project demonstrating the use of this plugin can be found here https://github.com/aripka-pivotal/jetty-app


**TO DOs**

* encapsulate usage of Gradle Application plugin 
* encapsulate usage of Maven Assembly Plugin

