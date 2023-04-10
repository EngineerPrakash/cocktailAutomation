# cocktailAutomation
<p align="center">
   <i><strong> This Project is Sample demonstration for automating the 2 API's for cocktail DB with rest Assured and TestNg
</strong></i>
<p>

### Rest Assured API Automation Guide with TestNg

### Features
* TestNg.xml files has two separate suite for the getCocktail and getIngredients API.
* POM.xml has all the dependencies listed in order to run this project.
* All code is written in Java.
* Page Object design pattern implementation.
* TestNg Annotation has been used to provide the test data and create the test methods.
* Added the API test cases.xlsx file in the root folder.
* Added the sample HTML test report for the run in the local.

### About API test cases excel file
* Download the sheet to view the details in more clear way
* it contains 3 sheet.
```
First Sheet --Test cases which has minimum test cases for the api mentioned in the task
Second Sheet--Schema of the API
Third sheet--Non-Functional test which can be done on the api and through which tools
```

### How to Run 
* clone the repo with git clone
* Build the project 
* Run the TestNg.XMl file to run the suite
* results will be stored locally to view

### HTML report
* Sample HTML report has been pushed in the repo for reference.

### Future modification
* we can add the BDD cucumber plugin to write the cases in BDD approach and generate the test results.
* we can add allure plugin to get the more concise allure reports.