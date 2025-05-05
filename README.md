# Trmix Test Automation

This project contains automated tests for the Trmix website using Selenium WebDriver with Cucumber framework in Java.

## Prerequisites

- Java JDK 11 or higher
- Maven
- Chrome or Firefox browser

## Project Structure

The project follows the Page Object Model (POM) design pattern and uses Cucumber for BDD testing.

```
src/test/java/
├── com.trmix
│   ├── pages/          # Page Object classes
│   ├── runners/        # Cucumber test runners
│   ├── stepdefinitions/# Step definition classes
│   └── utilities/      # Utility classes
src/test/resources/
└── features/           # Cucumber feature files
```

## Running the Tests

To run the tests, use the following command:

```bash
mvn clean test
```

## Reports

After test execution, you can find the reports in:
- HTML Report: `target/cucumber-reports/cucumber.html`
- JSON Report: `target/cucumber-reports/cucumber.json`
