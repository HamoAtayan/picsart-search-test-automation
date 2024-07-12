# Picsart Search  Test Automation Project

This project demonstrates how to set up a test automation framework using Java, Maven, TestNG, Selenium, and Allure for reporting.

## Prerequisites

- Java JDK 17 or higher
- Maven
- Chrome and Firefox browsers to be installed (for Selenium)
- Allure Command Line (for generating reports)

## Project Structure

## Setting Up

1. Clone the repository
2. Build the project
3. Install Allure Command Line by following the instructions at [Allure Framework](https://docs.qameta.io/allure/#_installing_a_commandline).
4. After running the tests 'allure serve' to generate reports

## Running Tests

Navigate to the project root directory and execute the following commands:

To clean and execute tests:
```sh
mvn clean test
```
To run all the tests remotely
```sh
mvn clean test -Ddriver=remote
```

To run all the tests in firefox
```sh
mvn clean test -Ddriver=firefox
```

To get allure result 
```sh
cd target 
```
then 
```sh
allure serve
```

## Running a single test

```sh
mvn clean test -Dtest=com.picsart.tests.ui_tests.SearchTest
```

