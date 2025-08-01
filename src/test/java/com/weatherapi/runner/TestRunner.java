package com.weatherapi.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner для запуска Cucumber тестов с TestNG
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.weatherapi.steps", "com.weatherapi.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-html-reports",
        "json:target/cucumber-json-reports/Cucumber.json",
        "junit:target/cucumber-xml-reports/Cucumber.xml"
    },
    tags = "@positive or @negative"
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
} 