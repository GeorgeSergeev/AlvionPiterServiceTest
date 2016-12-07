package qa.utils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

/**
 * Change browser resolution
 * @author Alexander Bogatov <a.bogatov@corp.mail.ru>
 */
public class ResolutionHelper extends ScenarioSteps {

    private final int WIDTH = 1024;
    private final int HEIGHT = 768;

    /**
     * Set default browser resolution
     */
    public void setResolution() {

        // get width and height
        int width;
        int height;

        if (getDriverType().equals("opera")) {
            JavascriptExecutor js = ((JavascriptExecutor) getDriver());
            width = (int) (long) js.executeScript("return window.innerWidth;");
            height = (int) (long) js.executeScript("return window.innerHeight;");
        } else {
            width = getDriver().manage().window().getSize().width;
            height = getDriver().manage().window().getSize().height;
        }

        if (WIDTH != width || HEIGHT != height) {
            System.out.println(String.format("Current browser dimension: %dx%d", width, height));
            String browser = getDriverType();
            switch (browser) {
                case "firefox":
                    setFirefoxDimension();
                    break;
                case "chrome":
                    setChromeDimension();
                    break;
                case "internet explorer":
                    setIEDimension(WIDTH, HEIGHT);
                    break;
                case "opera":
                    break;
                default:
                    throw new Error(String.format("Unknow browser type: %s", browser));
            }
        }
    }

    @Step(callNestedMethods = false)
    public void setResolution(int width, int height) {
        String browser = getDriverType();
        switch (browser) {
            case "firefox":
                setFirefoxDimension(width, height);
                System.out.println(String.format("Current browser dimension: %dx%d", width, height));
                break;
            case "chrome":
                setChromeDimension(width, height);
                System.out.println(String.format("Current browser dimension: %dx%d", width, height));
                break;
            case "internet explorer":
                setIEDimension(width, height);
                break;
            case "opera":
                break;
            default:
                throw new Error(String.format("Unknow browser type: %s", browser));
        }
    }

    public String getDriverType() {
        WebDriver webDriver = ((WebDriverFacade) getDriver()).getProxiedDriver();
        return ((RemoteWebDriver) webDriver).getCapabilities().getBrowserName();
    }

    private void setFirefoxDimension() {
        setFirefoxDimension(WIDTH, HEIGHT);
    }

    private void setFirefoxDimension(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        getDriver().manage().window().setSize(dimension);
    }

    private void setChromeDimension() {
        try {
            setChromeDimension(WIDTH, HEIGHT);
        } catch (Exception e) {
            JavascriptExecutor js = ((JavascriptExecutor) getDriver());
            String open = String.format("window.open('about:blank','_blank','width=%d,height=%d');", WIDTH, HEIGHT);
            js.executeScript(open);

            // swith on new window
            List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
            int current = windows.indexOf(getDriver().getWindowHandle());
            int last = windows.size() - 1;

            assertNotEquals("Новое окно для переключения не найдено", current, last);
            getDriver().switchTo().window(windows.get(last));

            String resize = String.format("window.resizeTo(%d,%d);", WIDTH, HEIGHT);
            js.executeScript(resize);
            throw new Error(e);
        }
    }

    private void setChromeDimension(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        getDriver().manage().window().setSize(dimension);
    }

    private void setIEDimension(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        getDriver().manage().window().setSize(dimension);
    }

}
