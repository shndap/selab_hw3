import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartEdgeCaseTest {

    // بررسی سبد خالی و اطمینان از صفر بودن مجموع و تعداد اقلام
    @Test
    public void testEmptyCartHasZeroTotalAndDiscount() {
        ShoppingCart cart = new ShoppingCart();

        assertEquals(0.0, cart.getTotal());
        assertEquals(0.0, cart.getTotalWithDiscount());
        assertEquals(0, cart.getItemCount());
    }

    // بررسی اینکه قیمت‌های نامعتبر هنگام افزودن کالا نادیده گرفته می‌شوند
    @Test
    public void testAddItemWithInvalidPriceShouldBeIgnored() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Invalid", -5);
        cart.addItem("Zero", 0);

        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.getTotal());
    }

    // بررسی اینکه به‌روزرسانی با قیمت نامعتبر هیچ تغییری ایجاد نمی‌کند
    @Test
    public void testUpdateItemPriceWithInvalidPriceShouldDoNothing() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Notebook", 30);
        cart.updateItemPrice("Notebook", -10);

        assertEquals(30.0, cart.getTotal());
        assertEquals(1, cart.getItemCount());
    }

    // بررسی مجموع دقیقاً برابر 100 و عدم اعمال تخفیف
    @Test
    public void testDiscountAtExactThresholdWithDecimalPrices() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("P1", 33.33);
        cart.addItem("P2", 33.33);
        cart.addItem("P3", 33.34);

        assertEquals(100.0, cart.getTotal(), 0.0001);
        assertEquals(100.0, cart.getTotalWithDiscount(), 0.0001);
    }

    // بررسی حذف کالای ناموجود
    @Test
    public void testRemoveMissingItemShouldReturnFalse() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Pen", 5);
        boolean removed = cart.removeItem("Notebook");

        assertFalse(removed);
        assertEquals(1, cart.getItemCount());
        assertEquals(5.0, cart.getTotal());
    }

    // بررسی رفتار نام‌های تکراری در سبد
    @Test
    public void testDuplicateItemNameShouldOverwritePriceWithoutChangingCount() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Pen", 10);
        cart.addItem("Pen", 15);

        assertEquals(1, cart.getItemCount());
        assertEquals(15.0, cart.getTotal());
    }

    // بررسی پایداری مجموع برای تعداد زیاد کالا
    @Test
    public void testManyItemsShouldProduceAccurateTotal() {
        ShoppingCart cart = new ShoppingCart();

        for (int i = 0; i < 1000; i++) {
            cart.addItem("Item-" + i, 1.0);
        }

        assertEquals(1000, cart.getItemCount());
        assertEquals(1000.0, cart.getTotal(), 0.0001);
    }

}
