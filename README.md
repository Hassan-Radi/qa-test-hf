#Web Tests

**Changes**
 * Whole test suite is designed using page object design pattern. All the test logic is encapsulated in an anstraction layer.
 * Test date is maintained in single file for easy changing to their values.
 * Test cases can work on both Chrome and FireFox browsers.
 * Human readable report is generated after each test execution using Maven surefire plugin.
 * Implemented logging using Log4j library which would show the logs in the system console by deault (You can also write the logs to a file if you want).
 * Controlling the browser to execute on using a command line argument `browser`. You can select either `chrome` or `firefox` to run the test cases on both Chrome and Firefox browsers respectively. 
 * Controlling the URL/environment to execute on using a command line argument `url`.

**How to run the tests?**
You can run using the following command line: `mvn clean compile test surefire-report:report -Durl=http://automationpractice.com/index.php -Dtest=WebTest -Dbrowser=chrome`

**How to access the generated report**
The generated report can be found under the follwing direcrtory `/target/site/surefire-report.html`. Just double click on it to open it in the default browser.

**Added libraries**
 * Log4j: Handles the logging mechanism in the whole project and manages the logs from different sourcesa and also allows to redirect the logs to the console and or file.

#API Tests

**What do you already have?**
-----
 * web application with url http://automationpractice.com/index.php;
 * 3 [test cases](TESTCASES.md);
 * 3 automated tests.
 
We give the initial version of tests in order to save your time on extracting locators. 

**What do you need to do?**
----
You need to improve given automated tests as much as you can by designing your own solution to develop such kinds of tests for similar applications.
Feel free to replace any tool we used in initial version of tests(maven, junit) or add other ones, if you need.

Your solution can include:
* logging;
* taking screenshot on failed tests;
* generation human readable report;
* generating random values for insignificant test data, for example, for new user;
* WebDriver factory;
* encapsulation layers like test data, logic of tests, actions on web pages and so on;
* configurator:
  * run tests in parallel mode;
  * ability to run tests for different browsers/OS by configuring;
  * ability to run tests for different environments(urls) by configuring/by command-line.
* reading test data from file, for example, the name of dress, size and color in the checkout test.

If you would like to impress us cover as much point as you can!

**Evaluation Criteria**
-------------------
1. The improvements are done in efficient and effective manner.
2. The improved tests pass stably and follow described cases.
3. The solution is well and logically organised.
4. Tests execution does not take more time than initial version.
5. The code is documented and is easy to-follow.
6. The application is supplied with all the information required for us to run and validate it as well as a description and purpose of used additional libraries.
