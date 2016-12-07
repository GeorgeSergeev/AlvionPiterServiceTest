package qa;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.scheduling.ThucydidesFluentWait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AbstractPage extends PageObject {

    private static final int DEFAULT_IMPLICITLY_WAIT = 15000;
    private final int implicitWait = Integer.parseInt(System.getProperty("webdriver.timeouts.implicitlywait", Integer.toString(DEFAULT_IMPLICITLY_WAIT)));

    public AbstractPage(WebDriver driver) {
        super(driver, DEFAULT_IMPLICITLY_WAIT);
    }

    protected int getImplicitWait() {
        return implicitWait;
    }

    /**
     * Ожидание элемента в DOM (видимость не проверяется)
     *
     * @param locator
     * @return
     */
    protected boolean isElementPresent(By locator) {
        List<WebElement> elements = getDriver().findElements(locator);
        return elements.size() > 0;
    }

    /**
     * Ожидание элемента в DOM (видимость не проверяется)
     *
     * @param locator
     * @param timeout
     * @return
     */
    protected boolean isElementPresent(By locator, int timeout) {
        boolean result = false;
        setImplicitTimeout(timeout, TimeUnit.MILLISECONDS);
        List<WebElementFacade> elements = findAll(locator);
        if (elements.size() > 0) {
            result = true;
        }
        resetImplicitTimeout();
        return result;
    }

    /**
     * Ожидание отсутствия элемента в DOM (видимость не проверяется)
     *
     * @param locator
     * @return
     */
    protected boolean isElementNotPresent(By locator) {
        List<WebElementFacade> elements = findAll(locator);
        return elements.size() <= 0;
    }

    /**
     * Ожидание отсутствия элемента в DOM (видимость не проверяется)
     *
     * @param locator
     * @param timeout
     * @return
     */
    protected boolean isElementNotPresent(By locator, int timeout) {
        boolean result = true;
        setImplicitTimeout(timeout, TimeUnit.MILLISECONDS);
        List<WebElementFacade> elements = findAll(locator);
        if (elements.size() > 0) {
            result = false;
        }
        resetImplicitTimeout();
        return result;
    }

    /**
     * Ожидание видимого элемента
     *
     * @param locator
     * @return
     */
    protected boolean waitForElementPresent(By locator) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition().withTimeout(implicitWait, TimeUnit.MILLISECONDS);
        try {
            wait.until(new VisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание видимого элемента
     *
     * @param locator
     * @param timeout
     * @return
     */
    protected boolean waitForElementPresent(By locator, int timeout) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(timeout, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        try {
            wait.until(new VisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание видимого элемента
     *
     * @param element
     * @return
     */
    protected boolean waitForElementPresent(WebElement element) {
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(implicitWait, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        WebElement wef = $(element).withTimeoutOf(100, TimeUnit.MILLISECONDS);
        try {
            wait.until(new VisibilityOfElement(wef));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        }
        return result;
    }

    /**
     * Ожидание видимого элемента
     *
     * @param element
     * @param timeout
     * @return
     */
    protected boolean waitForElementPresent(WebElement element, int timeout) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(timeout, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        WebElement wef = $(element).withTimeoutOf(100, TimeUnit.MILLISECONDS);
        try {
            wait.until(new VisibilityOfElement(wef));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание пока элемент станет невидимым
     *
     * @param locator
     * @return
     */
    protected boolean waitForElementNotPresent(By locator) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(implicitWait, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        try {
            wait.until(new InvisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание пока элемент станет невидимым
     *
     * @param locator
     * @param timeout
     * @return
     */
    protected boolean waitForElementNotPresent(By locator, int timeout) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(timeout, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        try {
            wait.until(new InvisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        }finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание пока элемент станет невидимым
     *
     * @param element
     * @return
     */
    protected boolean waitForElementNotPresent(WebElement element) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
            .withTimeout(implicitWait, TimeUnit.MILLISECONDS)
            .pollingEvery(500, TimeUnit.MILLISECONDS);
        WebElement wef = $(element).withTimeoutOf(100, TimeUnit.MILLISECONDS);
        try {
            wait.until(new InvisibilityOfElement(wef));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание пока элемент станет невидимым
     *
     * @param element
     * @param timeout
     * @return
     */
    protected boolean waitForElementNotPresent(WebElement element, int timeout) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(timeout, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        WebElement wef = $(element).withTimeoutOf(100, TimeUnit.MILLISECONDS);
        try {
            wait.until(new InvisibilityOfElement(wef));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание появления значения в атрибуте
     * @param element        элемент для которого проверяется значение атрибута
     * @param attributeName  имя атрибута
     * @param attributeValue значение атрибута
     * @return
     */
    protected boolean waitForElementAttributeContainsValue(WebElement element, String attributeName, String attributeValue) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        boolean result = true;
        FluentWait<WebElement> wait = new FluentWait<>(element).
                withTimeout(implicitWait, TimeUnit.MILLISECONDS).
                pollingEvery(500, TimeUnit.MILLISECONDS);
        try {
            wait.until((WebElement webElement) -> element.getAttribute(attributeName).contains(attributeValue));
        } catch (NoSuchElementException | TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание определенного кол-ва элементов
     * @param locator
     * @return
     */
    protected boolean waitForElementsCountSameAs(By locator, int count) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
            .withTimeout(implicitWait, TimeUnit.MILLISECONDS)
            .pollingEvery(500, TimeUnit.MILLISECONDS);
        try {
            wait.until(new CountOfElementsLocated(locator, count));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Экранируем спецсимволы для CSS селекторов
     *
     * @param input
     * @return
     */
    public static String forCSSSelector(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        int len = input.length();
        final StringBuilder result = new StringBuilder(len + len / 4);
        final StringCharacterIterator iterator = new StringCharacterIterator(input);
        char ch = iterator.current();
        while (ch != CharacterIterator.DONE) {
            if (ch == '\'') {
                result.append("\\\'");
            } else if (ch == '\\') {
                result.append("\\\\");
            } else if (ch == '"') {
                result.append("\\\"");
            } else {
                result.append(ch);
            }
            ch = iterator.next();
        }
        return result.toString();
    }

    /**
     * Экранируем одинарные и двойный кавычки в xpath разбиванием строки
     *
     * @param input
     * @return в результате получаем строку в concat() формате
     */
    public static String forXpathSelector(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        int len = input.length();
        final StringBuilder result = new StringBuilder(len + len / 4);
        final StringCharacterIterator iterator = new StringCharacterIterator(input);
        char ch = iterator.current();
        int count = 0;

        while (ch != CharacterIterator.DONE) {
            if (ch == '\'') {
                result.append("',\"'\",'");
                count++;
            } else if (ch == '"') {
                result.append("','\"','");
                count++;
            } else {
                result.append(ch);
            }
            ch = iterator.next();
        }
        if (count > 0) {
            return "concat('" + result.toString() + "','')";
        } else {
            return "\'" + input + "\'";
        }
    }

    /**
     * Получаем количество элементов по селектору
     *
     * @param locator
     * @return
     */
    protected int getCountOfElementsBy(By locator) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        int count = findAll(locator).size();
        resetImplicitTimeout();
        return count;
    }

    /**
     * Return current browser User-Agent
     * @return
     */
    public String getCurrentUserAgent() {
        String getUserAgent = "return navigator.userAgent;";
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        return (String) js.executeScript(getUserAgent);
        // TODO: dip: not working with custom driver
//        return (String) evaluateJavascript("return navigator.userAgent;");
    }
}
