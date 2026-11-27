import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<Product> products;
    private ArrayList<StockTransaction> transactions;

    public InventoryManager() {

        products = new ArrayList<>();
        transactions = new ArrayList<>();
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public ArrayList<StockTransaction> getTransactions() {
        return transactions;
    }
    public Product findProductById(String id) {

        for (Product product : products) {

            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public boolean deleteProduct(String id) {

        Product product = findProductById(id);

        if (product != null) {
            products.remove(product);
                return true;
        }
        return false;
    }
    public void addTransaction(StockTransaction transaction) {
        transactions.add(transaction);
    }
    public boolean stockIn(String id, int quantity) {

    Product product = findProductById(id);

    if (product != null) {

        product.stockIn(quantity);

        addTransaction(
            new StockTransaction(
                "Stock In",
                id,
                quantity
            )
        );
        return true;
    }
    return false;
}
    public boolean stockOut(String id, int quantity) {

    Product product = findProductById(id);

    if (product != null && product.stockOut(quantity)) {

        addTransaction(
            new StockTransaction(
                "Stock Out",
                id,
                quantity
            )
        );
        return true;
    }
    return false;
}
}