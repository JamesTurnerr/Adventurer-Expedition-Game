# SENG201 Project
This is our SENG201 project. 
This is a Java game where you can go on expeditions with a team of adventurers to collect items and gold.

## Authors
- James Turner and Diego Becker

## Prerequisites
- JDK >= 21 [click here to get the latest stable OpenJDK release (as of writing this README)](https://jdk.java.net/21/)
- *(optional)* Gradle [Download](https://gradle.org/releases/) and [Install](https://gradle.org/install/)


## Cloning into IntelliJ
- From Eng-Git repository URL:

Click "Code" to find the repository URL and clone it. In IntelliJ, select
"Main Menu" -> "New" -> "Project from Version Control",
and paste in the URL.
At this point, in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load.
- From Eng-Git ZIP file:

Click "Code" and download the source code as a ZIP file.
Extract this file, and open it with IntelliJ. At this point, in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load.


**Note:** *If you run into dependency issues when running the app or the Gradle pop up doesn't appear then open the Gradle sidebar and click the Refresh icon.*

## Run Project 
1. Open a command line interface inside the project directory and run `./gradlew run` to run the app.
2. The app should then open a new window, this may not be displayed over IntelliJ but can be easily selected from the taskbar

## Build and Run Jar
1. Open a command line interface inside the project directory and run `./gradlew jar` to create a packaged Jar. The Jar file is located at build/libs/
2. Navigate to the build/libs/ directory (you can do this with `cd build/libs`)
3. Run the command `java -jar seng201_team0-1.0-SNAPSHOT.jar` to open the application.

## Declaration of AI Use
During the project, we used AI tools such as ChatGPT for tasks such as generating the expedition events and locations.