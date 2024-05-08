package com.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features= "src/test/resources/Feature",
        glue = {"com.stepDefinition"},
        monochrome = true,
        plugin = {"pretty",
                "junit:target/JUnitReports/report.json",
                "html:target/cucumber-reports/cucumber-pretty.html"},
        tags = "@smoke"
)
public class TestRunner {

}


