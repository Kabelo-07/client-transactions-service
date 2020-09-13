# client-transactions-service
***************************
* Required to run project *
***************************
- download and install from : https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- From CMD(Windows) or Terminal (linux) run : java -version
 - You should see the following results:
*********************************************************************************
*    java version "11.0.8" 2020-07-14 LTS                                       *
*    Java(TM) SE Runtime Environment 18.9 (build 11.0.8+10-LTS)                 *
*    Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.8+10-LTS, mixed mode)   *
*********************************************************************************
Should you wish to build thr project: 
====================================

- download and install gradle from https://gradle.org/install/. (Instructions and steps to follow are on the website)
- Open terminal (ctrl + alt + t)
- Run : ./gradle clean build 
- Should you encounter issues please contact:
- cell : 078 097 0873
- email: lefamoeketsi65@gmail.com

***************************************
* How to start up application windows * 
***************************************

- Open cmd and locate the project
- Run java -jar build/libs/client-transactions.jar 
- 
****************************************
* How to start up application on linux *
****************************************

- Open terminal (ctrl + alt + t)
- cd to project folder
- Run : java -jar build/libs/client-transactions.jar 
- 
======================================================

- Once spring boot application is up and running.
- Go to to http://localhost:8383/swagger-ui.html#/ (server port configured to 8383 - incase port 8080 is already running)
