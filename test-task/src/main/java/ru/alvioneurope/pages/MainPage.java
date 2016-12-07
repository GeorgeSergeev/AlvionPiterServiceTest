package ru.alvioneurope.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import qa.AbstractPage;


public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Проверяет что скролл вверху страницы
     * @return
     */
    public boolean isScrollOnTop() {
        return ((Number) evaluateJavascript("return $(window).scrollTop();")).intValue() == 0;
    }

    /**
     * Возвращает положение элемента по высоте
     * @param field элемент страницы
     * @return положение по высоте
     */
    public int getElementYLocation(WebElement field) {
        return ((Locatable) field).getCoordinates().inViewPort().getY();
    }

    /**
     * Получение координаты Y для скролла
     * @return
     */
    public int getScrollY(){
        return ((Number) evaluateJavascript("return window.scrollY;")).intValue();
    }
}
