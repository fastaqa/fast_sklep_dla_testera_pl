package pl.akademiaqa.pages.sections.orderDetailsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.pages.BasePage;

public class OrderAddressSection extends BasePage {

    private final String addressSection = "#checkout-addresses-step ";

    private Locator addressInput;
    private Locator zipCode;
    private Locator city;
    private Locator continueButton;


    public OrderAddressSection(Page page) {
        super(page);
        this.addressInput = page.locator(addressSection + "#field-address1");
        this.zipCode = page.locator(addressSection + "#field-postcode");
        this.city = page.locator(addressSection + "#field-city");
        this.continueButton = page.locator(addressSection + "button[name=confirm-addresses]");
    }

    public OrderShippingSections enterAddress() {
        addressInput.fill("Ul. Sezamkowa 8");
        zipCode.fill("90-210");
        city.fill("Kraków");
        continueButton.click();

        return new OrderShippingSections(page);
    }
}
