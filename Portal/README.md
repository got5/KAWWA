# Work with the Portal

## Clone the Repository

## Launch the Portal

In order to execute locally the portal, we will use the Jetty gradle plugin. Everything is already defined in the build.gradle. You should open your command line tool, go to the Portal directory, and execute this command : 

```
./gradlew.bat jettyRun
```

If you are windows, you can also double-click on the run.bat file available in the Portal directory. 

## Build the Portal

In order to build the portal, we will use the build gradle goal. Everything is already defined in the build.gradle. You should open your command line tool, go to the Portal directory, and execute this command : 

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
