package pages.items;

public class Checkout {

    String pathToCardHead = "div.cart-page__head > h1";
    String pathToProductPrice = "div.cart-page__order-item-money";
    String pathToDescriptionPrice = "p.cart-page__order-item-subtext";
    String pathToFullPrice = "div.cart-page__order-total-money";

    String cartHeader;
    int cartPrice;
    int cartCount;
    int cartFullPrice;
    int cartDiscountPrice;

    public Checkout() {
    }
}
