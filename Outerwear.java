
public class Outerwear extends Product {

    private String size;
    private String color;
    private String garmentType;

    public Outerwear(String id, String name, double unitPrice, int quantity, int reorderLevel, String size, String color, String garmentType) {
        super(id, name, unitPrice, quantity, reorderLevel);

        setSize(size);
        setColor(color);
        setGarmentType(garmentType);
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        if (size != null && !size.isBlank()) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Size cannot be null or blank.");
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
    public String getGarmentType() {
        return garmentType;
    }
    public void setGarmentType(String garmentType) {
        if (garmentType != null && !garmentType.isBlank()) {
            this.garmentType = garmentType;
        } else {
            throw new IllegalArgumentException("Garment type cannot be null or blank.");
        }
    }
    @Override
    public String getCategory() {
        return "Outerwear";
    }
    @Override
    public String getProductInfo() {
        return "Size: " + size + ", Color: " + color + ", Garment Type: " + garmentType;
    }
}
