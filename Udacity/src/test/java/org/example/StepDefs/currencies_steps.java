package org.example.StepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;

    public class currencies_steps {
        HomePage HomePage ;

        @When("user select “euro” from currency dropdown list")
        public void userSelectEuroFromCurrencyDropdownList() {
            HomePage = new HomePage(Hooks.driver);
            HomePage.currency_symbol().click();
            HomePage.currencyEuro().click();
        }

        @Then("All the products change to euro symbol")
        public void AllTheProductschangeToEuroSymbol() {
            int count = Hooks.driver.findElements(By.xpath("//option[@value=\"https://demo.nopcommerce.com/changecurrency/6?returnUrl=%2F\"]")).size();
            for (int x = 0; x < count; x++) {
                String text = HomePage.productPrices().getText();
                Assert.assertTrue(text.contains("€"));
            }
        }

        @Then("user choose Euro successfully")
        public void userChooseEuroSuccessfully() {
            String ExpectedResult = "€";
            String ActualResult = Hooks.driver.findElement(By.cssSelector("select[onchange=\"setLocation(this.value)\"]")).getText();
            Assert.assertTrue(ActualResult.contains(ExpectedResult));

        }
    }