public class InventoryApp {

    public static void main(String[] args) {

        InventoryManager inventory = new InventoryManager();

        System.out.println(inventory.generateSummaryReport());
    }
}