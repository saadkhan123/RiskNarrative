package com.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.joda.time.Seconds;
import org.junit.Assert;
import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;
import java.util.List;

public class testSteps {

    private WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I open website")
    public void i_open_website() {

        System.setProperty("webdriver.chrome.driver","C:/Users/Saad Ahmed/drivers.chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://risk.lexisnexis.co.uk/");
        driver.manage().window().maximize();
    }

    @And("I accept consent if present")
    public void i_accept_consent_if_present() {
        try {
            WebElement acceptCookiesButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"))
            );
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println("Consent didn't appear.");
        }
    }

    @Then("I should see the following industries under Choose Your Industry:")
    public void i_should_see_industries_under_choose_your_industry(List<String> industries) {
        List<WebElement> industryElements = driver.findElements(By.xpath("//div[@id='block-industry-solutions-block']/div/div/a"));
        for (String industry : industries) {
            boolean found = false;
            for (WebElement element : industryElements) {
                if (element.getText().equalsIgnoreCase(industry)) {
                    found = true;
                    break;
                }
            }
            assert found : "Industry '" + industry + "' not found";
        }
    }

    @When("I click on {string}")
    public void i_click_on(String industry) {
        WebElement industryElement = driver.findElement(By.linkText(industry));
        industryElement.click();
    }

    @Then("I should see the following options:")
    public void i_should_see_the_following_options(List<String> options) {
        List<WebElement> optionElements = driver.findElements(By.cssSelector(".teaser__link"));
        for (String option : options) {
            boolean found = false;
            for (WebElement element : optionElements) {
                if (element.getText().equalsIgnoreCase(option)) {
                    found = true;
                    break;
                }
            }
            assert found : "Option '" + option + "' not found";
        }
    }

    @io.cucumber.java.After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}