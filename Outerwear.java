public class Outerwear extends Product {

    private String material;
    private String color;

    public Outerwear(String id, String name, double unitPrice, int quantity, int reorderLevel, String material, String color) {
        super(id, name, unitPrice, quantity, reorderLevel);

        setMaterial(material);
        setColor(color);
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String getCategory() {
        return "Outerwear";
    }
    @Override
    public String getProductInfo() {
        return "Material: " + material + ", Color: " + color;
    }
}