package org.cheuni02.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoStepDefs {

    private final WebDriver driver;

    By todoList = By.cssSelector("ul.todo-list li");
    By inputTodo = By.cssSelector("input.new-todo");

    public List<WebElement> elements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement element(By locator) {
        return driver.findElement(locator);
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
        List<String> taskStrings = new ArrayList<>();
        for (WebElement ele : elements(todoList)) {
            taskStrings.add(ele.getText());
        }
        assertTrue(taskStrings.contains(task));
    }

    @When("I type a new Item {string}")
    public void iTypeANewItemFeedTheCat(String task) throws InterruptedException {
        new Actions(driver)
                .sendKeys(element(inputTodo), task)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    @And("Hit Enter key to add")
    public void hitEnterKeyToAdd() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("There are now {int} items")
    public void thereAreNowItems(int numItems) throws InterruptedException {
        assertEquals(elements(todoList).size(), numItems);
    }

    @And("Last item is {string}")
    public void lastItemIsFeedTheCat(String task) {
        assertEquals(elements(todoList).getLast().getText(), task);
    }
}
