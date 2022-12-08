package org.example.StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.HomePage;
import org.example.pages.categories_page;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class categories_steps {
    HomePage HomePage ;
    categories_page category ;
    String subCategoryName;

    @When("user select category and subcategory")
    public void select_category() throws InterruptedException {

        HomePage = new HomePage(Hooks.driver);
        category = new categories_page(Hooks.driver);
        Actions action = new Actions(Hooks.driver);
        action.moveToElement(HomePage.computers()).perform();
        action.moveToElement(HomePage.computers()).perform();

        subCategoryName = HomePage.desktops().getText().toLowerCase();

        Thread.sleep(1000);
    }

    @And("user click on subcategory")
    public void user_clicks_subcategory(){
        HomePage.desktops().click();
    }

    @Then("user find relative product page")
    public void product_found(){
        Assert.assertEquals(category.categoryList().getText().toLowerCase(),subCategoryName,"subcategory");
    }
}
