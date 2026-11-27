public class Bottoms extends Product {

    private String waistSize;
    private String color;

    public Bottoms(String id, String name, double unitPrice, int quantity, int reorderLevel, String waistSize, String color) {
           super(id, name, unitPrice, quantity, reorderLevel);

        setWaistSize(waistSize);
        setColor(color);
    }
    public String getWaistSize() {
        return waistSize;
    }
    public void setWaistSize(String waistSize) {
        this.waistSize = waistSize;
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
        return "Waist Size: " + waistSize + ", Color: " + color;
    }
}