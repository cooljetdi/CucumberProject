package systemtest.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class FinancialTimesStepDef {
    @Given("I visit the Financial Times web site")
    public void iVisitTheFinancialTimesWebSite() {
        System.out.println("I open Financial Times website.");
    }

    @And("I click on the search button")
    public void iClickOnTheSearchButton() {
        System.out.println("I click on the search button.");
    }

    @When("I search the keyword {string} in the Financial Times search box")
    public void iSearchTheKeywordInTheFinancialTimesSearchBox(String arg0) {
        System.out.println("I type in the search keyword: " + arg0);
    }

    @And("I should see the result")
    public void iShouldSeeTheResult() {
        System.out.println("I see the result.");
    }
}
