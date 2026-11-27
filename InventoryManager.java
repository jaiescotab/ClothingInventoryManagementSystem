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

        product.setQuantity(
            product.getQuantity() + quantity
        );
        addTransaction(
            new StockTransaction(
                "Stock In",
                id,
                quantity,
                java.time.LocalDateTime.now().toString()
            )
        );
        return true;
    }
    return false;
}
   public boolean stockOut(String id, int quantity) {

    Product product =findProductById(id);

    if (product != null &&
        product.getQuantity() >= quantity) {

        product.setQuantity(
            product.getQuantity() - quantity
        );
        addTransaction(
            new StockTransaction(
                "Stock Out",
                id,
                quantity,
                java.time.LocalDateTime.now().toString()
            )
        );
        return true;
    }
    return false;
}
   public ArrayList<Product> getLowStockProducts() {
    ArrayList<Product> lowStockProducts = new ArrayList<>();

    for (Product product : products) {

        if (product.getQuantity() <= product.getReorderLevel()) {
            lowStockProducts.add(product);
        }
    }
    return lowStockProducts;
}
}