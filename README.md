# Example

Example of Allure Report usage with TestNG, Java and Maven

The generated report is available here: [https://allure-examples.github.io/testng-java-maven](https://allure-examples.github.io/testng-java-maven/)

### Serve allure
- for latest reports: `mvn allure:serve`
- to execute tests: `mvn test -Dparallel=methods -Dsuitename=src/test/java/com/example/testng/StepTest.xml`
- do clean reports, just mvn clean: `mvn clean`

### Findings: initializing this repo
following the below steps allows me to pull from the original repo (upstream) and (origin) is now my default
- i cloned this repo from https://github.com/allure-examples/testng-java-maven.git/
- git remote rename origin upstream
- create repo on github without a readme or .gitignore
- git remote add origin https://github.com/MarkDYabut/allure-testng-playwright.git
- git push origin main
- git push -u origin