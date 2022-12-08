package org.example.StepDefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.HomePage;
import org.example.pages.Search_page;
import org.testng.asserts.SoftAssert;

public class Search_steps {
    HomePage HomePage ;
    Search_page search;

    @Given("user clicks on search field")
    public void search_field() {
        HomePage  = new HomePage(Hooks.driver);
        search = new Search_page(Hooks.driver);
        HomePage.search().click();
    }

    @When("user search for product name like \"(.*)\"$")
    public void search_for_product(String search) {
        HomePage.search().sendKeys(search);
    }

    @And("user click on search Button")
    public void search_Button() {

        HomePage.search_Button().click();
    }

    @Then("user could search successfully and go to search page")
    public void search_successfully() {
        // First Assertion
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(Hooks.driver.getCurrentUrl().contains("https://demo.nopcommerce.com/search?q=Nokia"), "search URL");
        // Second Assertion
        int size = search.productsList();
        soft.assertTrue(size > 0,"Search Result");
        // Assert All
        soft.assertAll();
    }

    @Then("user could search successfully with SKU code")
    public void successfully_with_SKU_code() {
        // First Assertion
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(Hooks.driver.getCurrentUrl().contains("https://demo.nopcommerce.com/search?q=AP_MBP_13"), "URL after search");
        // Second Assertion
        int size = search.productsList();
        soft.assertTrue(size > 0,"Search Result");
        // Assert All
        soft.assertAll();
    }

    @Then("user could not search for the product")
    public void unable_to_search() {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(search.noProducts().isDisplayed(), "No products");
    }
}