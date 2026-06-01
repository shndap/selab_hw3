import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<String, Double> items = new HashMap<>();

    private boolean isValidPrice(double price) {
        return price > 0.0;
    }

    public void addItem(String name, double price) {
        if (name == null || !isValidPrice(price)) {
            return;
        }
        items.put(name, price);
    }

    public boolean removeItem(String name) {
        if (items.containsKey(name)) {
            items.remove(name);
            return true;
        }
        return false;
    }

    public double getTotal() {
        double total = 0.0;
        for (double price : items.values()) {
            total += price;
        }
        return total;
    }
    public double getTotalWithDiscount() {
        double total = getTotal();
        if (total > 100.0) {
            return total * 0.9;
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public void updateItemPrice(String name, double newPrice) {
        if (name == null || !isValidPrice(newPrice)) {
            return;
        }
        if (items.containsKey(name)) {
            items.put(name, newPrice);
        }
    }

}
