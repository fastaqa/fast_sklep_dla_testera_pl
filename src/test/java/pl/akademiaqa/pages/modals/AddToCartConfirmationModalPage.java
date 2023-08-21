package pl.akademiaqa.pages.modals;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.pages.BasePage;
import pl.akademiaqa.pages.ShoppingCartPage;

import static pl.akademiaqa.utils.PageUtils.*;

public class AddToCartConfirmationModalPage extends BasePage {

    private Locator conformationLabel;
    private Locator proceedToCheckoutButton;

    public AddToCartConfirmationModalPage(Page page) {
        super(page);
        waitForPageToLoad(page);
        this.conformationLabel = page.locator("#myModalLabel");
        this.proceedToCheckoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed to checkout"));
    }

    public String getConfirmationMessage() {
        return conformationLabel.innerText();
    }

    public ShoppingCartPage proceedToCheckoutOnModal() {
        proceedToCheckoutButton.click();

        return new ShoppingCartPage(page);
    }
}
