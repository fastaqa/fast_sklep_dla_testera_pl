package pl.akademiaqa.tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.fasades.AddProductToCartFacade;
import pl.akademiaqa.fasades.OrderProductFacade;
import pl.akademiaqa.pages.*;
import pl.akademiaqa.pages.modals.AddToCartConfirmationModalPage;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderAddressSection;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderPaymentSection;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderShippingSections;

import static org.assertj.core.api.Assertions.*;

class FullPurchaseTest extends BaseTest {

    private final String productName = "Customizable Mug";

    private HomePage homePage;
    private AddProductToCartFacade addProductToCartFacade;
    private OrderProductFacade orderProductFacade;

    @BeforeEach
    void beforeEach() {
        homePage = new HomePage(page);
        addProductToCartFacade = new AddProductToCartFacade(homePage);
        orderProductFacade = new OrderProductFacade();
    }

//    @Test
//    void should_purchase_selected_product_v1_test() {
//        SearchResultsPage searchResultsPage = homePage.getTopMenuAndSearchSection().searchForProduct(productName);
//        ProductDetailsPage productDetailsPage = searchResultsPage.getSearchResultsSection().viewProductDetails(productName);
//        productDetailsPage.getCustomizationSection().customizeProduct(productName);
//        AddToCartConfirmationModalPage confirmationModal = productDetailsPage.getAddToCartSection().addProductToCart();
//        assertThat(confirmationModal.getConfirmationMessage()).contains("Product successfully added to your shopping cart");
//
//        ShoppingCartPage shoppingCartPage = confirmationModal.proceedToCheckoutOnModal();
//        OrderDetailsPage orderDetailsPage = shoppingCartPage.getSummarySection().proceedToCheckout();
//
//        OrderAddressSection addressSection = orderDetailsPage.getPersonalInformation().enterPersonalInformation();
//        OrderShippingSections shippingSections = addressSection.enterAddress();
//        OrderPaymentSection paymentSection = shippingSections.selectShippingMethod();
//        OrderConfirmationPage confirmationPage = paymentSection.placeOrder();
//        assertThat(confirmationPage.getConfirmationDetailsSection()
//                .getConfirmationTitle()).containsIgnoringCase("Your order is confirmed");
//    }
//
//    @Test
//    void should_purchase_selected_product_v2_test() {
//        AddToCartConfirmationModalPage confirmationModal =
//                homePage
//                        .searchForProduct(productName)
//                        .viewProductDetails(productName)
//                        .customizeProduct(productName)
//                        .addProductToCart();
//
//        assertThat(confirmationModal.getConfirmationMessage()).contains("Product successfully added to your shopping cart");
//
//        OrderConfirmationPage confirmationPage =
//                confirmationModal
//                        .proceedToCheckoutOnModal()
//                        .proceedToCheckoutOnShoppingCartPage()
//                        .enterOrderDetails();
//
//        assertThat(confirmationPage.getConfirmationDetailsSection()
//                .getConfirmationTitle()).containsIgnoringCase("Your order is confirmed");
//    }

    @Test
    void should_purchase_selected_product_v3_test() {
        AddToCartConfirmationModalPage confirmationModal = addProductToCartFacade.addProductWithCustomizationToCart(productName);
        assertThat(confirmationModal.getConfirmationMessage()).contains("Product successfully added to your shopping cart");

        OrderConfirmationPage confirmationPage = orderProductFacade.orderProduct(confirmationModal);
        assertThat(confirmationPage.getConfirmationDetailsSection()
                .getConfirmationTitle()).containsIgnoringCase("Your order is confirmed");
    }
}
