package org.example.StepDefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class Login_steps {

    HomePage  HomePage ;
    LoginPage login ;

    @When("user navigate to login page")
    public void user_navigate_to_login_page() {
        HomePage = new HomePage(Hooks.driver);
        login = new LoginPage(Hooks.driver);
        HomePage.Navigate_to_LoginPage().click();
    }

    @And("user enter \"(.*)\" and \"(.*)\"$")
    public void vaild_data(String email, String password) throws InterruptedException {
        login.LoginSteps(email, password);
        Thread.sleep(1000);
    }

    @Then("user click on login button")
    public void click_button() {
        login.passwordE().sendKeys(Keys.ENTER);
    }

    @And("user could login successfully and go to home page")
    public void login_successfully() {
        // First Assertion
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(Hooks.driver.getCurrentUrl(),"https://demo.nopcommerce.com/","URL login");
        // Second Assertion
        Hooks.driver.findElement(By.cssSelector("a[class=\"ico-account\"]")).isDisplayed();
        soft.assertTrue(login.myAccount().isDisplayed(), "My Account");
        // Assert All
        soft.assertAll();
    }

    @And("user could not login successfully and go to home page")
    public void unable_to_login() {
        // First Assertion

        Hooks.driver.manage().timeouts().implicitlyWait(15, TimeUnit.MINUTES);
        SoftAssert soft = new SoftAssert();
        login.wrongMsg().isDisplayed();
        String actual = login.wrongMsg().getText();
        soft.assertEquals(actual,"Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect","Wrong Message");
        // Second Assertion
        actual = login.wrongMsg().getCssValue("color");
        String expected = "rgba(228, 67, 75, 1)";
        soft.assertEquals(actual, expected, "color");
        // Assert All
        soft.assertAll();
    }
}
