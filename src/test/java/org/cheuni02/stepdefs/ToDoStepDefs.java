package org.cheuni02.stepdefs;

import io.cucumber.java.en.*;
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

import static org.junit.jupiter.api.Assertions.*;

public class ToDoStepDefs {

    private final WebDriver driver;

    By todoListCss = By.cssSelector("ul.todo-list li");
    By inputTodoCss = By.cssSelector("input.new-todo");
    By checkDoneCss = By.cssSelector("input[type=\"checkbox\"]");
    By clearCompletedXpath = By.xpath("//*[contains(text(),\"Clear Completed\")]");

    public List<WebElement> elements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement element(By locator) {
        return driver.findElement(locator);
    }

    public WebElement labelWithTextViaXpath(String task) {
        String xpathStr = "//*[contains(text(),'" + task + "')]/preceding-sibling::input";
        System.out.println("xpathStr: " + xpathStr);
        return driver.findElement(By.xpath(xpathStr));
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
        Integer actualNumItems = elements(todoListCss).size();
        assertEquals(actualNumItems, numItems);
    }

    @And("One of them is {string}")
    public void oneOfThemIsPayElectricBill(String task) throws InterruptedException {
        List<String> taskStrings = new ArrayList<>();
        for (WebElement ele : elements(todoListCss)) {
            taskStrings.add(ele.getText());
        }
        assertTrue(taskStrings.contains(task));
    }

    @When("I type a new Item {string}")
    public void iTypeANewItemFeedTheCat(String task) throws InterruptedException {
        new Actions(driver)
                .sendKeys(element(inputTodoCss), task)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    @And("Hit Enter key to add")
    public void hitEnterKeyToAdd() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("There are now {int} items")
    public void thereAreNowItems(int numItems) throws InterruptedException {
        assertEquals(elements(todoListCss).size(), numItems);
    }

    @And("Last item is {string}")
    public void lastItemIsFeedTheCat(String task) {
        assertEquals(elements(todoListCss).getLast().getText(), task);
    }

    @When("I add a new item {string}")
    public void iAddANewItemGoToDentist(String task) throws InterruptedException {
        new Actions(driver)
                .sendKeys(element(inputTodoCss), task)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    @And("Check it off as completed")
    public void checkItOffAsCompleted() throws InterruptedException {
        elements(todoListCss)
                .getLast()
                .findElement(checkDoneCss)
                .click();
    }

    @Then("Item will appear with a strikethrough")
    public void itemWillAppearWithAStrikethrough() {
        String lastItem = elements(todoListCss)
                .getLast()
                .getAttribute("class");

        assert lastItem != null;
        assertTrue(lastItem.contains("completed"));
    }

    @Given("Item {string} is checked as completed")
    public void itemIsCheckedAsCompleted(String task) throws InterruptedException {
        labelWithTextViaXpath(task).click();
    }

    @When("I filter for uncompleted tasks")
    public void iFilterForUncompletedTasks() throws InterruptedException {
        driver.findElement(By.linkText("Active")).click();
    }

    @Then("Only {string} shows")
    public void onlyThisTaskShows(String task) throws InterruptedException {
        List<WebElement> todoList = elements(todoListCss);
        assertEquals(todoList.size(), 1);
        assertEquals(todoList.getFirst().getText(), task);
    }

    @When("{string} is clicked")
    public void clearCompletedIsClicked(String linkText) throws InterruptedException {
        element(By.linkText(linkText)).click();
    }

    @Then("{string} is removed")
    public void payElectricBillIsRemoved(String task) throws InterruptedException {
        List<String> taskStrings = new ArrayList<>();
        for (WebElement ele : elements(todoListCss)) {
            taskStrings.add(ele.getText());
        }
        assertFalse(taskStrings.contains(task));
    }

    @But("{string} still shows")
    public void walkTheDogStillShows(String task) throws InterruptedException {
        List<String> taskStrings = new ArrayList<>();
        for (WebElement ele : elements(todoListCss)) {
            taskStrings.add(ele.getText());
        }
        assertTrue(taskStrings.contains(task));
    }

    @When("test input")
    public void testInput() {
        System.out.println("test input");
    }

    @Then("test outcome")
    public void testOutcome() {
        System.out.println("test outcome");
    }
}
