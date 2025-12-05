OrangeHRM POM Selenium Project
==============================
Generated files:
- src/main/java/pages/LoginPage.java         (Page Object with PageFactory)
- src/main/java/utils/ConfigReader.java     (reads config.properties)
- src/main/java/utils/TestDataReader.java   (reads JSON testdata)
- src/test/java/tests/LoginTest.java        (TestNG test - login)
- src/test/resources/env/config.properties  (URL and BROWSER)
- src/test/resources/testdata/login.json    (test data)
- pom.xml                                   (Maven project)
- testng.xml                                (TestNG suite)
- reports/OrangeHRMLoginReport.html         (Extent report will be generated here)
Notes:
- Initialize driver using WebDriverManager.chromedriver().setup() as required.
- To run:
  mvn clean test
- Java 11+ is required.
