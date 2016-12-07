package qa.utils;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Класс для управления окнами браузера
 * @author Ruslan Guseinov <r.guseinov@corp.mail.ru>
 */
public class WindowsHelper extends ScenarioSteps {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    /**
     * Открыть новое окно
     */
    @Step(callNestedMethods = false)
    public void openNewWindowAndSwitchOnIt() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String open = String.format("window.open('about:blank','_blank','width=%d,height=%d');", WIDTH, HEIGHT);
        js.executeScript(open);
        switchOnNewWindow();
        String resize = String.format("window.resizeTo(%d,%d);", WIDTH, HEIGHT);
        js.executeScript(resize);
    }

    /**
     * Открыть указанную страницу в текущем окне
     * @param url
     */
    @Step(callNestedMethods = false)
    public void openPage(String url) {
        getDriver().get(url);
    }

    /**
     * Открыть указанную страницу в текущем окне
     * @param parameter
     */
    @Step(callNestedMethods = false)
    public void openCurrentPageWithParameter(String parameter) {
        getDriver().get(getDriver().getCurrentUrl() + parameter);
    }

    /**
     * Переключает на основное окно. Шаг для использования в тестах.
     */
    @Step(callNestedMethods = false)
    @Screenshots(onlyOnFailures = true)
    public void switchOnMainWindow() {
        String mainWindow = (String) Serenity.getCurrentSession().get("mainWindow");
        getDriver().switchTo().window(mainWindow);
    }

    /**
     * Переключает на основное окно. Статический метод, для использования на страницах и элементах.
     * @param driver
     */
    public static void switchOnMainWindow(WebDriver driver) {
        String mainWindow = (String) Serenity.getCurrentSession().get("mainWindow");
        driver.switchTo().window(mainWindow);
    }

    /**
     * Переключиться на новое окно
     */
    @Step(callNestedMethods = false)
    public void switchOnNewWindow() {
        // save main window before first switch
        setMainWindow();

        // wait for open new window
        waitABit(1000);

        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        int current = windows.indexOf(getMainWindow());
        int last = windows.size() - 1;

        assertNotEquals("Новое окно для переключения не найдено", current, last);
        getDriver().switchTo().window(windows.get(last));
    }

    /**
     * Set main window. Set only one time for test!
     */
    private void setMainWindow() {
        if (getMainWindow() == null) {
            Serenity.getCurrentSession().put("mainWindow", getDriver().getWindowHandle());
        }
    }

    /**
     * Get main window
     * @return
     */
    private String getMainWindow() {
        return (String) Serenity.getCurrentSession().get("mainWindow");
    }

    /**
     * Обновление окна
     */
    @Step(callNestedMethods = false)
    public void refreshWindow() {
        waitABit(5000);
        getDriver().navigate().refresh();
    }

    /**
     * вернуться назад (по истории в браузере)
     */
    @Step(callNestedMethods = false)
    public void goBack() { getDriver().navigate().back(); }

    /**
     * пойти вперед (по истории в браузере)
     */
    @Step(callNestedMethods = false)
    public void goForward() { getDriver().navigate().forward(); }

    /**
     * Проверяет, что количество открытых окон
     * совпадает с ожидаемым количеством
     * @param expectedCount expectedCount
     */
    @Step(callNestedMethods = false)
    public void windowsCountShouldBeEqual(int expectedCount) {
        int actualCount = 0;
        long timeout = System.currentTimeMillis() + 15000;
        while (timeout > System.currentTimeMillis()) {
            actualCount = getDriver().getWindowHandles().size();
            if (expectedCount == actualCount) {
                break;
            }
            waitABit(500);
        }
        assertEquals("Количество открытых окон браузера отличается от ожидаемого.",
                expectedCount, actualCount);
    }
}
