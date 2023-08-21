package pl.akademiaqa.pages.sections.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.pages.BasePage;
import pl.akademiaqa.utils.StringUtils;

import java.util.Arrays;

import static pl.akademiaqa.utils.StringUtils.*;

public class FilterBySection extends BasePage {

    private Locator leftSlider;

    private Locator priceLabel;

    public FilterBySection(Page page) {
        super(page);
        this.leftSlider = page.locator(".ui-slider-handle").first();
        this.priceLabel = page.locator("#search_filters li p");
    }

    public void filterProductsByPriceWithMouse(double fromPrice) {
        while (fromPrice != getFromPrice()) {
            double x = leftSlider.boundingBox().x;
            double y = leftSlider.boundingBox().y;

            double middleX = x + leftSlider.boundingBox().width / 2;
            double middleY = y + leftSlider.boundingBox().height / 2;

            leftSlider.scrollIntoViewIfNeeded();
            page.mouse().move(middleX, middleY);
            page.mouse().down();
            page.mouse().move(x + 7.00, y);
            page.mouse().up();
            page.waitForCondition(() -> page.locator(".overlay__inner").isHidden());
        }
    }

    public void filterProductsByPriceWithKeyboard(double fromPrice) {
        while (fromPrice != getFromPrice()) {
            leftSlider.press("ArrowRight");
            page.waitForCondition(() -> page.locator(".overlay__inner").isHidden());
        }
    }

    private double getFromPrice() {
        return Arrays.asList(page.locator("#search_filters li p").innerText().split(" "))
                .stream()
                .map(p -> p.replaceAll(toUTF8("zł"), ""))
                .map(Double::parseDouble)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(" Invalid price format"));
    }
}
