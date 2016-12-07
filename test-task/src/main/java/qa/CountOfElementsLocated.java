package qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class CountOfElementsLocated implements ExpectedCondition<Boolean> {

    private final By locator;
    private final int count;

    public CountOfElementsLocated(By locator, int count) {
        this.locator = locator;
        this.count = count;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            return elements.size() == count;
        } catch (StaleElementReferenceException | NoSuchElementException | ElementNotVisibleException e) {
            return false;
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
}
