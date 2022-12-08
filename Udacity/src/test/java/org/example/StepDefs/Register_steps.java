package org.example.StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.pages.HomePage;
import org.example.pages.Register_page;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class Register_steps {

    HomePage homepage ;
    Register_page  register ;

    @Given("user navigate to register page")
    public void register_page() {
        register = new Register_page(Hooks.driver);
        homepage = new HomePage(Hooks.driver);
        homepage.Navigate_to_RegisterPage().click();
    }

    @And("user select gender type")
    public void Gender(){
        register.femaleGender().click();
    }

    @And("user enter valid data")
    public void user_enter_first_name(){
        register.firstName().sendKeys("Hadeer");

        register.lastName().sendKeys("Moaaz");

        Select select = new Select(register.birthDay());
        select.selectByIndex(30);

        select = new Select(register.birthMonth());
        select.selectByVisibleText("August");

        select = new Select(register.birthYear());
        select.selectByValue("1995");


        register.email().sendKeys("Hadeermohamed22@gmail.com");

        register.companyName().sendKeys("Vois");

        register.password().sendKeys("Vois@2222");
        register.confirmPassword().sendKeys("Vois@2222");
    }

    @Then("user click on register button")
    public void register_button()
    {
        register.registerB().click();
    }

    @Then("success message is displayed")
    public void register_successfully() {

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(register.successfulMsg().isDisplayed(), "Success Message");

        String actual = register.successfulMsg().getCssValue("color");
        String expected = "rgba(76, 177, 124, 1)";
        soft.assertEquals(actual, expected, "color is correct");

        soft.assertAll();
    }
}
