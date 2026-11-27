public class Bottoms extends Product {

    private String size;
    private String color;

    public Bottoms(String id, String name, double unitPrice,
                   int quantity, int reorderLevel,
                   String size, String color) {

        super(id, name, unitPrice, quantity, reorderLevel);

        setSize(size);
        setColor(color);
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String getCategory() {
        return "Bottoms";
    }
    @Override
    public String getProductInfo() {
        return size + ", " + color;
    }
}