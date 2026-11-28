import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class InventoryManager {

    private ArrayList<Product> products;
    private ArrayList<StockTransaction> transactions;
    private ArrayList<Supplier> suppliers;

    public InventoryManager() {
        products = new ArrayList<>();
        transactions = new ArrayList<>();
        suppliers = new ArrayList<>();

        if (!loadData()) {
            loadSampleData();
        }
    }
    private boolean loadData() {
    File file = new File("inventory.txt");

    if (!file.exists()) {
        return false;
    }

    try (java.util.Scanner fileInput = new java.util.Scanner(file)) {
        if (!fileInput.hasNextLine()
                || !fileInput.nextLine().equals("INVENTORY_DATA")) {
            return false;
        }

        while (fileInput.hasNextLine()) {
            String[] data = fileInput.nextLine().split("\\|", -1);

            try {
                if (data[0].equals("P") && data.length == 10) {
                    String id = data[2];
                    String name = data[3];
                    double price = Double.parseDouble(data[4]);
                    int quantity = Integer.parseInt(data[5]);
                    int reorderLevel = Integer.parseInt(data[6]);

                    if (data[1].equals("Tops")) {
                        products.add(new Tops(id, name, price, quantity,
                                reorderLevel, data[7], data[8], data[9]));
                    } else if (data[1].equals("Bottoms")) {
                        products.add(new Bottoms(id, name, price, quantity,
                                reorderLevel, data[7], data[8], data[9]));
                    } else if (data[1].equals("Outerwear")) {
                        products.add(new Outerwear(id, name, price, quantity,
                                reorderLevel, data[7], data[8], data[9]));
                    }

                } else if (data[0].equals("S") && data.length == 4) {
                    suppliers.add(new Supplier(data[1], data[2], data[3]));

                } else if (data[0].equals("SP") && data.length == 3) {
                    Supplier supplier = findSupplierById(data[1]);
                    Product product = findProductById(data[2]);

                    if (supplier != null && product != null) {
                        supplier.addProduct(product);
                    }

                } else if (data[0].equals("T") && data.length == 5) {
                    transactions.add(new StockTransaction(
                            data[1],
                            data[2],
                            Integer.parseInt(data[3]),
                            LocalDateTime.parse(data[4])
                    ));
                }

            } catch (Exception e) {
                System.out.println("A saved record could not be loaded.");
            }
        }

        return true;

    } catch (FileNotFoundException e) {
        System.out.println("Unable to read saved inventory.");
        return false
    }
    }
    public void loadSampleData() {

    // TOPS
    products.add(new Tops("T001", "Basic T-Shirt", 299.00, 20, 5, "M", "White", "Short Sleeve"));
    products.add(new Tops("T002", "Polo Shirt", 450.00, 15, 5, "L", "Black", "Short Sleeve"));
    products.add(new Tops("T003", "Crop Top", 350.00, 12, 4, "XS", "Pink", "Sleeveless"));
    products.add(new Tops("T004", "Long Sleeve Blouse", 550.00, 8, 3, "S", "Blue", "Long Sleeve"));

    // BOTTOMS
    products.add(new Bottoms("B001", "Straight Jeans", 899.00, 10, 3, "27", "Blue", "Full Length"));
    products.add(new Bottoms("B002", "Cargo Pants", 750.00, 14, 4, "28", "Black", "Full Length"));
    products.add(new Bottoms("B003", "Denim Shorts", 499.00, 18, 5, "26", "Light Blue", "Short"));
    products.add(new Bottoms("B004", "Wide Leg Trousers", 799.00, 7, 3, "30", "Beige", "Full Length"));

    // OUTERWEAR
    products.add(new Outerwear("O001", "Denim Jacket", 1200.00, 6, 2, "M", "Blue", "Jacket"));
    products.add(new Outerwear("O002", "Hoodie", 950.00, 10, 3, "L", "Gray", "Hoodie"));
    products.add(new Outerwear("O003", "Cardigan", 850.00, 9, 3, "S", "Cream", "Cardigan"));
    products.add(new Outerwear("O004", "Sweater", 1100.00, 5, 2, "L", "Black", "Sweater"));
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

        addTransaction(
            new StockTransaction(
                "Add Product",
                product.getId(),
                product.getQuantity(),
                java.time.LocalDateTime.now()
            )
        );
    }
    }
    public boolean updateProduct(String id, Product updatedProduct) {
    if (updatedProduct == null) {
        throw new IllegalArgumentException("Updated product cannot be null.");
    }

    Product existingProduct = findProductById(id);
    if (existingProduct != null) {
        if (!existingProduct.getId().equalsIgnoreCase(updatedProduct.getId())) {
            throw new IllegalArgumentException("Product ID cannot be changed.");
        }
        updatedProduct.setQuantity(existingProduct.getQuantity());

        int index = products.indexOf(existingProduct);
        products.set(index, updatedProduct);

        addTransaction(
            new StockTransaction(
                "Update Product",
                updatedProduct.getId(),
                updatedProduct.getQuantity(),
                java.time.LocalDateTime.now()
            )
        );
        return true;
    }
    return false;
}
    public boolean deleteProduct(String id) {

    Product product = findProductById(id);
    if (product != null) {
        products.remove(product);

        addTransaction(
            new StockTransaction(
                "Delete Product",
                product.getId(),
                product.getQuantity(),
                java.time.LocalDateTime.now()
            )
        );
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
    private int calculateTotalUnits(int index) {
    // Base case
    if (index >= products.size()) {
        return 0;
    }
    // Recursive case
    return products.get(index).getQuantity()
            + calculateTotalUnits(index + 1);
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
    int totalUnits = calculateTotalUnits(0);
    double totalInventoryValue = 0;

    int topsCount = 0;
    int bottomsCount = 0;
    int outerwearCount = 0;

    double topsValue = 0;
    double bottomsValue = 0;
    double outerwearValue = 0;

    for (Product product : products) {

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
public void saveData() {
    try (PrintWriter writer = new PrintWriter("inventory.txt")) {
        writer.println("INVENTORY_DATA");

        // Save products
        for (Product product : products) {
            writer.println("P|" + product.getCategory()
                    + "|" + product.getId()
                    + "|" + product.getName()
                    + "|" + product.getUnitPrice()
                    + "|" + product.getQuantity()
                    + "|" + product.getReorderLevel()
                    + "|" + product.getStorageData());
        }

        // Save suppliers
        for (Supplier supplier : suppliers) {
            writer.println("S|" + supplier.getSupplierId()
                    + "|" + supplier.getSupplierName()
                    + "|" + supplier.getContactNumber());

            // Save each supplier's assigned products
            for (Product product : supplier.getProducts()) {
                writer.println("SP|" + supplier.getSupplierId()
                        + "|" + product.getId());
            }
        }

        // Save transactions
        for (StockTransaction transaction : transactions) {
            writer.println("T|" + transaction.getTransactionType()
                    + "|" + transaction.getProductId()
                    + "|" + transaction.getQuantity()
                    + "|" + transaction.getTimestamp());
        }

        System.out.println("Inventory, suppliers, and transaction history saved successfully!");

    } catch (FileNotFoundException e) {
        System.out.println("Unable to save inventory data.");
    }
}
public void addSupplier(Supplier supplier) {
    if (supplier == null) {
        throw new IllegalArgumentException("Supplier cannot be null.");
    }
    for (Supplier existingSupplier : suppliers) {
        if (existingSupplier.getSupplierId().equals(supplier.getSupplierId())) {
            throw new IllegalArgumentException("Supplier ID already exists.");
        }
    }
    suppliers.add(supplier);
}
public Supplier findSupplierById(String supplierId) {
    for (Supplier supplier : suppliers) {
        if (supplier.getSupplierId().equalsIgnoreCase(supplierId)) {
            return supplier;
        }
    }
    return null;
}
public void assignProductToSupplier(String supplierId, String productId) {
    Supplier supplier = findSupplierById(supplierId);
    Product product = findProductById(productId);

    if (supplier == null) {
        throw new IllegalArgumentException("Supplier not found.");
    }
    if (product == null) {
        throw new IllegalArgumentException("Product not found.");
    }
    supplier.addProduct(product);
}
public ArrayList<Product> getProductsBySupplier(String supplierId) {
    Supplier supplier = findSupplierById(supplierId);

    if (supplier == null) {
        throw new IllegalArgumentException("Supplier not found.");
    }
    return supplier.getProducts();
}
public ArrayList<Product> getRestockListBySupplier(String supplierId) {
    Supplier supplier = findSupplierById(supplierId);

    if (supplier == null) {
        throw new IllegalArgumentException("Supplier not found.");
    }
    return supplier.getRestockList();
}
public ArrayList<Supplier> getSuppliers() {
    return new ArrayList<>(suppliers);
}
public ArrayList<Product> filterProducts(String category, double minPrice, double maxPrice, String stockStatus) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'filterProducts'");
}
}