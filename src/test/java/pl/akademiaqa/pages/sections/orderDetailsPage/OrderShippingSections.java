package pl.akademiaqa.pages.sections.orderDetailsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.pages.BasePage;

public class OrderShippingSections extends BasePage {

    private final String shippingSection = "#checkout-delivery-step ";
    private Locator deliveryMyCarrier;
    private Locator continueButton;

    public OrderShippingSections(Page page) {
        super(page);
        this.deliveryMyCarrier = page.locator(shippingSection + "#delivery_option_2");
        this.continueButton = page.locator(shippingSection + "button[name=confirmDeliveryOption]");
    }

    public OrderPaymentSection selectShippingMethod() {
        deliveryMyCarrier.check();
        continueButton.click();

        return new OrderPaymentSection(page);
    }
}
