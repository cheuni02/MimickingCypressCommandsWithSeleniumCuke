package org.cheuni02.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cheuni02.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadTestStepDefs {
    protected Integer num;

    @Given("A precondition")
    public void aPrecondition() {
        num = 6;
    }

    @When("A change happens")
    public void aChangeHappens() {
        System.out.println("num before test: " + num);
        num = num + 7;
    }

    @Then("Expected Outcome occurs")
    public void expectedOutcomeOccurs() {
        System.out.println("num after test: " + num);
        assertEquals(num, 13);
    }

    @And("Running Main.main returns {string}")
    public void runningMainMainReturnsHelloAndWelcome(String expectedStr) {
        Main mainInstance = new Main();
        assertEquals(mainInstance.greet(), expectedStr);
    }
}
