public class StockTransaction {

    private String transactionType;
    private String productId;
    private int quantity;
    private String timestamp;

    public StockTransaction(String transactionType, String productId, int quantity, String timestamp) {

        setTransactionType(transactionType);
        setProductId(productId);
        setQuantity(quantity);
        setTimestamp(timestamp);
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {

        if (transactionType != null && !transactionType.isBlank()) {
            this.transactionType = transactionType;
        } else {
            throw new IllegalArgumentException("Transaction type cannot be empty.");
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

        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity must be greater than zero."); }
        }
        public String getTimestamp() {
    return timestamp;
}
public void setTimestamp(String timestamp) {

    if (timestamp != null && !timestamp.isBlank()) {
        this.timestamp = timestamp;
    } else {
        throw new IllegalArgumentException("Timestamp cannot be empty.");
    }
    }
}