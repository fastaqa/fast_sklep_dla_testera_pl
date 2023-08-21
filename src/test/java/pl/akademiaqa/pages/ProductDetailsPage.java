package pl.akademiaqa.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.pages.modals.AddToCartConfirmationModalPage;
import pl.akademiaqa.pages.sections.productDetailsPage.AddToCartSection;
import pl.akademiaqa.pages.sections.productDetailsPage.ProductCustomizationSection;

import static pl.akademiaqa.utils.PageUtils.*;

@Getter
public class ProductDetailsPage extends BasePage {

    private ProductCustomizationSection customizationSection;
    private AddToCartSection addToCartSection;

    public ProductDetailsPage(Page page) {
        super(page);
        waitForPageToLoad(page);
        this.customizationSection = new ProductCustomizationSection(page);
        this.addToCartSection = new AddToCartSection(page);
    }


    public ProductDetailsPage customizeProduct(String customMessage) {
        customizationSection.customizeProduct(customMessage);
        return this;
    }

    public AddToCartConfirmationModalPage addProductToCart() {
        return addToCartSection.addProductToCart();
    }
}
