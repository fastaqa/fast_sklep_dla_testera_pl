package pl.akademiaqa.fasades;

import pl.akademiaqa.pages.HomePage;
import pl.akademiaqa.pages.modals.AddToCartConfirmationModalPage;

public class AddProductToCartFacade {

    private HomePage homePage;

    public AddProductToCartFacade(HomePage homePage) {
        this.homePage = homePage;
    }

    public AddToCartConfirmationModalPage addProductWithCustomizationToCart(String productName) {
        return homePage
                .searchForProduct(productName)
                .viewProductDetails(productName)
                .customizeProduct(productName)
                .addProductToCart();
    }

    public AddToCartConfirmationModalPage addProductToCart(String productName) {
        return homePage
                .searchForProduct(productName)
                .viewProductDetails(productName)
                .addProductToCart();
    }
}
