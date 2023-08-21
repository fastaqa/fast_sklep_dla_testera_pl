package pl.akademiaqa.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderAddressSection;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderPersonalInformationSection;

import static pl.akademiaqa.utils.PageUtils.*;

@Getter
public class OrderDetailsPage extends BasePage {

    private OrderPersonalInformationSection personalInformation;
    private OrderAddressSection addressSection;

    public OrderDetailsPage(Page page) {
        super(page);
        waitForPageToLoad(page);
        this.personalInformation = new OrderPersonalInformationSection(page);
        this.addressSection = new OrderAddressSection(page);
    }

    public OrderConfirmationPage enterOrderDetails() {
        return personalInformation
                .enterPersonalInformation()
                .enterAddress()
                .selectShippingMethod()
                .placeOrder();
    }
}
