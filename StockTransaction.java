import java.time.LocalDateTime;

public class StockTransaction {

    private String transactionType;
    private String productId;
    private int quantity;
    private LocalDateTime timestamp;

    public StockTransaction(String transactionType, String productId, int quantity, LocalDateTime timestamp) {

        setTransactionType(transactionType);
        setProductId(productId);
        setQuantity(quantity);
        setTimestamp(timestamp);
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {

        if (transactionType != null &&
            (transactionType.equalsIgnoreCase("Add Product") ||
             transactionType.equalsIgnoreCase("Update Product") ||
             transactionType.equalsIgnoreCase("Delete Product") ||
             transactionType.equalsIgnoreCase("Stock In") ||
             transactionType.equalsIgnoreCase("Stock Out"))) {

            this.transactionType = transactionType;

        } else {
            throw new IllegalArgumentException("Invalid transaction type.");
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {

        if (productId != null && !productId.isBlank()) {
            this.productId = productId;
        } else {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {

        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {

        if (timestamp != null) {
            this.timestamp = timestamp;
        } else {
            throw new IllegalArgumentException("Timestamp cannot be null.");
        }
    }
}
