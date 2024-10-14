package org.cheuni02.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoStepDefs {

    private final WebDriver driver;

    By todoList = By.cssSelector("ul.todo-list li");

    public List<WebElement> elements(By locator) {
        return driver.findElements(locator);
    }

    public ToDoStepDefs(Hooks hooks) {
        this.driver = hooks.getDriver();
    }

    @Given("I am on the todos page")
    public void iAmOnTheTodosPage() throws InterruptedException {
        driver.get("https://example.cypress.io/todo");
    }

    @Then("There are {int} items by default")
    public void thereAreItemsByDefault(int numItems) throws InterruptedException {
        Integer actualNumItems = elements(todoList).size();
        assertEquals(actualNumItems, numItems);
    }

    @And("One of them is {string}")
    public void oneOfThemIsPayElectricBill(String task) throws InterruptedException {
        List<String> taskStrings = new ArrayList<String>();
        for (WebElement ele : elements(todoList)) {
            taskStrings.add(ele.getText());
        }
        assertTrue(taskStrings.contains(task));
    }
}
