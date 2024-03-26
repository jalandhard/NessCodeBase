--**Instructions to setup the frame work and run the scripts in to local machine**--
Pre-Condition:
1. Java should be installed and set the environmental path variables
2. Elipse should be installed 
3. TestNG and MavenE2E(Maven Integration for eclipse) plugins should be installed in to eclipse(To install go to Help->Eclipse Marketplace..->search for plugin to installed and install

Steps to be setup the frame work into eclipse:
1. Download zip project folder
2. Unzip the project
3. Open eclipse tool
4. Click on File->Import->Maven->Existing Maven Projecs->Next->Browse the unziped folder location->OK->Next->Finish
5. Right click on pom.xml->Run As->Maven clean
6. Right click on pom.xml->Run As->Maven install.  It will install all Jars (TestNG,Appache POI, Extent report, log4j) from global repository


Steps to run the automation scripts:
1. Right click on testng.xml->Run As->TestNG Suite
2. Once execution completed refresh project and go to test-output folder->ExtentReports.html

Notes:
1. All the standard data's like URL,CC details are stored in configuration.properties file
2. The test related data stored in datafiles->data.xlsx
Based of req we can modify the data in respective files
