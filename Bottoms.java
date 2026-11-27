
public class Bottoms extends Product {

    private String waistSize;
    private String color;
    private String length;

    public Bottoms(String id, String name, double unitPrice, int quantity, int reorderLevel, String waistSize, String color, String length) {
        super(id, name, unitPrice, quantity, reorderLevel);

        setWaistSize(waistSize);
        setColor(color);
        setLength(length);
    }
    public String getWaistSize() {
        return waistSize;
    }
    public void setWaistSize(String waistSize) {
        if (waistSize != null && !waistSize.isBlank()) {
            this.waistSize = waistSize;
        } else {
            throw new IllegalArgumentException("Waist size cannot be null or blank.");
        }
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        if (color != null && !color.isBlank()) {
            this.color = color;
        } else {
            throw new IllegalArgumentException("Color cannot be null or blank.");
        }
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        if (length != null && !length.isBlank()) {
            this.length = length;
        } else {
            throw new IllegalArgumentException("Length cannot be null or blank.");
        }
    }
    @Override
    public String getCategory() {
        return "Bottoms";
    }
    @Override
    public String getProductInfo() {
        return "Waist Size: " + waistSize + ", Color: " + color + ", Length: " + length;
    }
}
