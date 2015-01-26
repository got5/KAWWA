# Work with the Portal

## Clone the Repository

In order to work locally with the Kawwa2 portal, you should clone clone the Git Repository. You can use Git command-line client, or the "Github for Windows" UI tool. Here is the URL of the repository : https://github.com/got5/KAWWA.git. 

Everything related to the portal are in the Portal directory.

## Launch the Portal

First, you should install the Java Developper Kit 7 (JDK), and set the Windows/Linux Environmental Variable JAVA_HOME to the path containing the JDK binaries.

If you any proxy issues with Gradle, you should set these Gradle properties inside the .gradle\gradle.properties file : 
* systemProp.http.proxyHost=www.somehost.org
* systemProp.http.proxyPort=8080
* systemProp.http.proxyUser=userid
* systemProp.http.proxyPassword=password

In order to execute locally the portal, we will use the Jetty gradle plugin. Everything is already defined in the build.gradle. You should open your command line tool, go to the Portal directory, and execute this command : 

```
./gradlew.bat jettyRun
```

If you are windows, you can also double-click on the run.bat file available in the Portal directory. 

## Build the Portal

In order to build the portal, we will use the build gradle goal. Everything is already defined in the build.gradle. You should open your command line tool, go to the Portal directory, and execute this command : 

The Portal will be available here : http://localhost:8080/Portal/

In order to get the components, you should update the awl.filesystemindexer.root property inside the Portal\src\main\webapp\WEB-INF\web.xml file.
```
./gradlew.bat build
```

The gradle build file will generate the war archive that has to be deployed into the application server. 
This script will execute other actions : 

* generate all sublime text snippets. Thansk so all the snippets define in the component directory, the script kawwa2-sublimetext/script.js
will generate all Sublime Text snippets for JS and HTML snippets. You can also execute this command in order to do the same thing : 
```
kawwa2-sublimetext> node script.js
```
