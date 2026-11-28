import java.util.ArrayList;

public class Supplier {

    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private ArrayList<Product> products;

    public Supplier(String supplierId, String supplierName,
            String contactNumber) {

        setSupplierId(supplierId);
        setSupplierName(supplierName);
        setContactNumber(contactNumber);
        products = new ArrayList<>();
    }
    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        if (supplierId != null && !supplierId.isBlank()) {
            this.supplierId = supplierId;
        } else {
            throw new IllegalArgumentException("Supplier ID cannot be blank.");
        }
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        if (supplierName != null && !supplierName.isBlank()) {
            this.supplierName = supplierName;
        } else {
            throw new IllegalArgumentException("Supplier name cannot be blank.");
        }
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        if (contactNumber != null && !contactNumber.isBlank()) {
            this.contactNumber = contactNumber;
        } else {
            throw new IllegalArgumentException("Contact number cannot be blank.");
        }
    }
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        for (Product existingProduct : products) {
            if (existingProduct.getId().equals(product.getId())) {
                throw new IllegalArgumentException("Product is already assigned to this supplier.");
            }
        }
        products.add(product);
    }
    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }
    public ArrayList<Product> getRestockList() {
        ArrayList<Product> restockList = new ArrayList<>();

        for (Product product : products) {
            if (product.getQuantity() <= product.getReorderLevel()) {
                restockList.add(product);
            }
        }
        return restockList;
    }
}
