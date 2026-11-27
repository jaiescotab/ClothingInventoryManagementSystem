public class Tops extends Product {

    private String size;
    private String color;
    private String sleeveType;


    public Tops(String id, String name, double unitPrice, int quantity, int reorderLevel, String size, String color, String sleeveType) {
        super(id, name, unitPrice, quantity, reorderLevel);

        setSize(size);
        setColor(color);
        setSleeveType(sleeveType);
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
            throw new IllegalArgumentException("Color cannot be null or blank."); }
        }
            public String getSleeveType() {
        return sleeveType;
    }
    public void setSleeveType(String sleeveType) {
        if (sleeveType != null && !sleeveType.isBlank()) {
            this.sleeveType = sleeveType;
        } else {
            throw new IllegalArgumentException("Sleeve type cannot be null or blank.");
        }

    }
    @Override
    public String getCategory() {
        return "Tops";
    }
    @Override
    public String getProductInfo() {
        return "Size: " + size + ", Color: " + color + ", Sleeve Type: " + sleeveType;
    }
}