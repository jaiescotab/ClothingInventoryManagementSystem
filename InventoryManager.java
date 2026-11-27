import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<Product> products;
    private ArrayList<StockTransaction> transactions;

    public InventoryManager() {

        products = new ArrayList<>();
        transactions = new ArrayList<>();
    }
    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }
    public ArrayList<StockTransaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
   public Product findProductById(String id) {

    if (id == null || id.isBlank()) {
        throw new IllegalArgumentException("Product ID cannot be empty.");
    }

    for (Product product : products) {

        if (product.getId().equals(id)) {
            return product;
        }
    }
    return null;
    }
        public ArrayList<Product> searchProductsByName(String name) {

    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Product name cannot be empty.");
    }

    ArrayList<Product> searchResults = new ArrayList<>();

    for (Product product : products) {

        if (product.getName().toLowerCase().contains(name.toLowerCase())) {
            searchResults.add(product);
        }
    }
    return searchResults;
    }
    public void addProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        } else if (findProductById(product.getId()) != null) {
            throw new IllegalArgumentException("Product ID already exists.");
        } else {
            products.add(product);
        }
    } public boolean updateProduct(String id, Product updatedProduct) {

    if (updatedProduct == null) {
        throw new IllegalArgumentException("Updated product cannot be null.");
    }

    Product existingProduct = findProductById(id);

    if (existingProduct != null) {

        if (!existingProduct.getId().equalsIgnoreCase(updatedProduct.getId())) {
            throw new IllegalArgumentException("Product ID cannot be changed.");
        }

        if (!existingProduct.getCategory().equals(updatedProduct.getCategory())) {
            throw new IllegalArgumentException("Product category cannot be changed.");
        }
        updatedProduct.setQuantity(existingProduct.getQuantity());

        int index = products.indexOf(existingProduct);
        products.set(index, updatedProduct);

        return true;
    }
    return false;
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

        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        } else {
            transactions.add(transaction);
        }
    }
    public boolean stockIn(String id, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

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
                    java.time.LocalDateTime.now()
                )
            );
            return true;
        }
        return false;
    }
    public boolean stockOut(String id, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Product product = findProductById(id);

        if (product != null && product.getQuantity() >= quantity) {

            product.setQuantity(
                product.getQuantity() - quantity
            );
            addTransaction(
                new StockTransaction(
                    "Stock Out",
                    id,
                    quantity,
                    java.time.LocalDateTime.now()
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
    public void sortByName(boolean ascending) {
    for (int i = 0; i < products.size() - 1; i++) {
        for (int j = i + 1; j < products.size(); j++) {
            int comparison = products.get(i).getName()
                    .compareToIgnoreCase(products.get(j).getName());

            if ((ascending && comparison > 0) || (!ascending && comparison < 0)) {
                Product temp = products.get(i);
                products.set(i, products.get(j));
                products.set(j, temp);
            }
        }
    }
}
public void sortByPrice(boolean ascending) {
    for (int i = 0; i < products.size() - 1; i++) {
        for (int j = i + 1; j < products.size(); j++) {
            double firstPrice = products.get(i).getUnitPrice();
            double secondPrice = products.get(j).getUnitPrice();

            if ((ascending && firstPrice > secondPrice) || (!ascending && firstPrice < secondPrice)) {
                Product temp = products.get(i);
                products.set(i, products.get(j));
                products.set(j, temp);
            }
        }
    }
}public void sortByQuantity(boolean ascending) {
    for (int i = 0; i < products.size() - 1; i++) {
        for (int j = i + 1; j < products.size(); j++) {

            int firstQuantity = products.get(i).getQuantity();
            int secondQuantity = products.get(j).getQuantity();
            if ((ascending && firstQuantity > secondQuantity) || (!ascending && firstQuantity < secondQuantity)) {
                Product temp = products.get(i);
                products.set(i, products.get(j));
                products.set(j, temp);
            }
        }
    }
        }
        public String generateSummaryReport() {

    int totalProducts = products.size();
    int totalUnits = 0;
    double totalInventoryValue = 0;

    int topsCount = 0;
    int bottomsCount = 0;
    int outerwearCount = 0;

    double topsValue = 0;
    double bottomsValue = 0;
    double outerwearValue = 0;

    for (Product product : products) {

        totalUnits += product.getQuantity();

        double productValue = product.getUnitPrice() * product.getQuantity();

        totalInventoryValue += productValue;

        if (product.getCategory().equals("Tops")) {
            topsCount++;
            topsValue += productValue;
        } else if (product.getCategory().equals("Bottoms")) {
            bottomsCount++;
            bottomsValue += productValue;
        } else if (product.getCategory().equals("Outerwear")) {
            outerwearCount++;
            outerwearValue += productValue;
        }
    }

    String report = "";

    report += "\n===== INVENTORY SUMMARY REPORT =====\n";
    report += "Total Products: " + totalProducts + "\n";
    report += "Total Units: " + totalUnits + "\n";
    report += "Total Inventory Value: " + totalInventoryValue + "\n";

    report += "\n===== CATEGORY BREAKDOWN =====\n";
    report += "Tops: " + topsCount + " products, Value: " + topsValue + "\n";
    report += "Bottoms: " + bottomsCount + " products, Value: " + bottomsValue + "\n";
    report += "Outerwear: " + outerwearCount + " products, Value: " + outerwearValue + "\n";

    report += "\n===== LOW-STOCK PRODUCTS =====\n";

    ArrayList<Product> lowStockProducts = getLowStockProducts();

    if (lowStockProducts.isEmpty()) {
        report += "No low-stock products.\n";
    } else {
        for (Product product : lowStockProducts) {
            report += "ID: " + product.getId()
                    + ", Name: " + product.getName()
                    + ", Quantity: " + product.getQuantity()
                    + ", Reorder Level: " + product.getReorderLevel() + "\n";
        }
    }

    return report;
}
    }
