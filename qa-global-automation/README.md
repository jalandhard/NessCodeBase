# Ad Automation

## Installation
### Requirements
- Java
- Eclipse
- TestNG and MavenE2E (Maven Integration for eclipse) plugins for Eclipse
> Note: Plugins can be installed through Eclipse Marketplace, however newer versions of Eclipse do not have these plugins on the marketplace, so you may need to use an older version of Eclipse (ie. Mars, etc). 

### Steps
1. Clone project onto local computer (`git clone` or download the zip file)
2. In Eclipse, import Maven project (File -> Import -> Maven -> Existing Maven Projects -> Next -> Browse to cloned repository directory -> Finish)
3. Right click on pom.xml->Run As->Maven clean
4. Right click on pom.xml->Run As->Maven install.  It will install all Jars (TestNG, Appache POI, Extent report, log4j) from global repository


## Usage
1. Right click on testng.xml->Run As->TestNG Suite
2. Once execution completed refresh project and go to test-output -> ExtentReports.html

## Notes
1. Standard data like URL and CC details are stored in configuration.properties file
2. Test related data is stored in datafiles -> data.xlsx
