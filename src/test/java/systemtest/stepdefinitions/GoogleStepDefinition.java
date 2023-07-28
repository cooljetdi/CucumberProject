package systemtest.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class GoogleStepDefinition {
    @Given("I visit Google home page")
    public void iVisitGoogleHomePage() {
        System.out.println("MY GOOGLE HOME PAGE");
    }

    @Then("I type in search keyword")
    public void i_type_in_search_keyword() {
        System.out.println("SEARCH GOOGLE");
    }

    @Then("I HAHA")
    public void iHAHA() {
        System.out.println("I HAHAHAHA");
    }
}
