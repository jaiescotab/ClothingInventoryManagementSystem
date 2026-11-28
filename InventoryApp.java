
import java.util.Scanner;
import java.util.ArrayList;

public class InventoryApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        InventoryManager inventory = new InventoryManager();
        int choice;

        do {
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Stock In");
            System.out.println("7. Stock Out");
            System.out.println("8. View Low-Stock Products");
            System.out.println("9. Sort Products");
            System.out.println("10. Generate Summary Report");
            System.out.println("11. View Transaction History");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = getValidChoice(input, 0, 11);

            switch (choice) {

                case 1: {
                    System.out.println("\n===== ADD PRODUCT =====");
                    System.out.println("1. Tops");
                    System.out.println("2. Bottoms");
                    System.out.println("3. Outerwear");

                    int categoryChoice = getValidChoice(input, 1, 3);

                    String id;

                    while (true) {
                        id = getNonEmptyInput(input, "Enter Product ID: ");

                        if (inventory.findProductById(id) == null) {
                            break;
                        }

                        System.out.println("Product ID already exists. Please try another ID.");
                    }

                    String name = getNonEmptyInput(input, "Enter Product Name: ");
                    double unitPrice = getValidPrice(input);
                    int quantity = getValidNumber(input, "Enter Quantity: ");
                    int reorderLevel = getValidNumber(input, "Enter Reorder Level: ");

                    switch (categoryChoice) {

                        case 1: {
                            System.out.println("\n===== TOPS DETAILS =====");

                            String size = getNonEmptyInput(input, "Enter Size: ");
                            String color = getNonEmptyInput(input, "Enter Color: ");
                            String sleeveType = getNonEmptyInput(input, "Enter Sleeve Type: ");

                            Tops product = new Tops(id, name, unitPrice, quantity,
                                    reorderLevel, size, color, sleeveType);

                            inventory.addProduct(product);
                            break;
                        }

                        case 2: {
                            System.out.println("\n===== BOTTOMS DETAILS =====");

                            String waistSize = getNonEmptyInput(input, "Enter Waist Size: ");
                            String color = getNonEmptyInput(input, "Enter Color: ");
                            String length = getNonEmptyInput(input, "Enter Length: ");

                            Bottoms product = new Bottoms(id, name, unitPrice, quantity,
                                    reorderLevel, waistSize, color, length);

                            inventory.addProduct(product);
                            break;
                        }

                        case 3: {
                            System.out.println("\n===== OUTERWEAR DETAILS =====");

                            String size = getNonEmptyInput(input, "Enter Size: ");
                            String color = getNonEmptyInput(input, "Enter Color: ");
                            String garmentType = getNonEmptyInput(input, "Enter Garment Type: ");

                            Outerwear product = new Outerwear(id, name, unitPrice, quantity,
                                    reorderLevel, size, color, garmentType);

                            inventory.addProduct(product);
                            break;
                        }
                    }

                    System.out.println("\nProduct added successfully!");
                    System.out.println("Total Products: " + inventory.getProducts().size());
                    break;
                }

                case 2:
                    System.out.println("\n===== ALL PRODUCTS =====");

                    if (inventory.getProducts().isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        for (Product product : inventory.getProducts()) {
                            System.out.println("\n-------------------------");
                            System.out.println("Product ID: " + product.getId());
                            System.out.println("Product Name: " + product.getName());
                            System.out.println("Category: " + product.getCategory());
                            System.out.println("Unit Price: " + product.getUnitPrice());
                            System.out.println("Quantity: " + product.getQuantity());
                            System.out.println("Reorder Level: " + product.getReorderLevel());
                            System.out.println("Additional Details: " + product.getProductInfo());
                        }
                    }
                    break;

                case 3: {
                    System.out.println("\n===== SEARCH PRODUCT =====");
                    System.out.println("1. Search by Product ID");
                    System.out.println("2. Search by Product Name");

                    int searchChoice = getValidChoice(input, 1, 2);

                    if (searchChoice == 1) {
                        String id = getNonEmptyInput(input, "Enter Product ID: ");
                        Product product = inventory.findProductById(id);

                        if (product != null) {
                            System.out.println("\n===== PRODUCT FOUND =====");
                            System.out.println("Product ID: " + product.getId());
                            System.out.println("Product Name: " + product.getName());
                            System.out.println("Category: " + product.getCategory());
                            System.out.println("Unit Price: " + product.getUnitPrice());
                            System.out.println("Quantity: " + product.getQuantity());
                            System.out.println("Reorder Level: " + product.getReorderLevel());
                            System.out.println("Additional Details: " + product.getProductInfo());
                        } else {
                            System.out.println("No product found with that ID.");
                        }

                    } else {
                        String name = getNonEmptyInput(input, "Enter Product Name: ");
                        ArrayList<Product> foundProducts =
                                inventory.searchProductsByName(name);

                        if (foundProducts.isEmpty()) {
                            System.out.println("No products found with that name.");
                        } else {
                            System.out.println("\n===== SEARCH RESULTS =====");

                            for (Product product : foundProducts) {
                                System.out.println("\n-------------------------");
                                System.out.println("Product ID: " + product.getId());
                                System.out.println("Product Name: " + product.getName());
                                System.out.println("Category: " + product.getCategory());
                                System.out.println("Unit Price: " + product.getUnitPrice());
                                System.out.println("Quantity: " + product.getQuantity());
                                System.out.println("Reorder Level: " + product.getReorderLevel());
                                System.out.println("Additional Details: " + product.getProductInfo());
                            }
                        }
                    }
                    break;
                }

                case 4: {
                    System.out.println("\n===== UPDATE PRODUCT =====");

                    String id = getNonEmptyInput(input, "Enter Product ID to update: ");
                    Product existingProduct = inventory.findProductById(id);

                    if (existingProduct == null) {
                        System.out.println("Product not found.");
                    } else {
                        System.out.println("\n===== CURRENT PRODUCT DETAILS =====");
                        System.out.println("Product ID: " + existingProduct.getId());
                        System.out.println("Product Name: " + existingProduct.getName());
                        System.out.println("Category: " + existingProduct.getCategory());
                        System.out.println("Unit Price: " + existingProduct.getUnitPrice());
                        System.out.println("Quantity: " + existingProduct.getQuantity());
                        System.out.println("Reorder Level: " + existingProduct.getReorderLevel());
                        System.out.println("Additional Details: " + existingProduct.getProductInfo());

                        System.out.println("\n===== ENTER UPDATED DETAILS =====");

                        String name = getNonEmptyInput(input, "Enter New Product Name: ");
                        double unitPrice = getValidPrice(input);
                        int reorderLevel = getValidNumber(input, "Enter New Reorder Level: ");

                        Product updatedProduct = null;

                        if (existingProduct.getCategory().equals("Tops")) {
                            Tops existingTops = (Tops) existingProduct;

                            String size = getNonEmptyInput(input, "Enter New Size: ");
                            String color = getNonEmptyInput(input, "Enter New Color: ");
                            String sleeveType = getNonEmptyInput(input, "Enter New Sleeve Type: ");

                            updatedProduct = new Tops(
                                    id, name, unitPrice, existingProduct.getQuantity(),
                                    reorderLevel, size, color, sleeveType
                            );

                        } else if (existingProduct.getCategory().equals("Bottoms")) {
                            Bottoms existingBottoms = (Bottoms) existingProduct;

                            String waistSize = getNonEmptyInput(input, "Enter New Waist Size: ");
                            String color = getNonEmptyInput(input, "Enter New Color: ");
                            String length = getNonEmptyInput(input, "Enter New Length: ");

                            updatedProduct = new Bottoms(
                                    id, name, unitPrice, existingProduct.getQuantity(),
                                    reorderLevel, waistSize, color, length
                            );

                        } else if (existingProduct.getCategory().equals("Outerwear")) {
                            Outerwear existingOuterwear = (Outerwear) existingProduct;

                            String size = getNonEmptyInput(input, "Enter New Size: ");
                            String color = getNonEmptyInput(input, "Enter New Color: ");
                            String garmentType = getNonEmptyInput(input, "Enter New Garment Type: ");

                            updatedProduct = new Outerwear(
                                    id, name, unitPrice, existingProduct.getQuantity(),
                                    reorderLevel, size, color, garmentType
                            );
                        }

                        if (inventory.updateProduct(id, updatedProduct)) {
                            System.out.println("\nProduct updated successfully!");

                            System.out.println("\n===== UPDATED PRODUCT DETAILS =====");
                            System.out.println("Product ID: " + updatedProduct.getId());
                            System.out.println("Product Name: " + updatedProduct.getName());
                            System.out.println("Category: " + updatedProduct.getCategory());
                            System.out.println("Unit Price: " + updatedProduct.getUnitPrice());
                            System.out.println("Quantity: " + updatedProduct.getQuantity());
                            System.out.println("Reorder Level: " + updatedProduct.getReorderLevel());
                            System.out.println("Additional Details: " + updatedProduct.getProductInfo());
                        } else {
                            System.out.println("Product update failed.");
                        }
                    }
                    break;
                }

                case 0:
                    System.out.println("Exiting Inventory Management System...");
                    break;

                default:
                    System.out.println("This feature is not implemented yet.");
            }

        } while (choice != 0);

        input.close();
    }

    private static String getNonEmptyInput(Scanner input, String message) {
        String value;

        while (true) {
            System.out.print(message);
            value = input.nextLine();

            if (!value.isBlank()) {
                return value;
            }

            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static int getValidNumber(Scanner input, String message) {
        while (true) {
            try {
                System.out.print(message);
                int number = Integer.parseInt(input.nextLine());

                if (number >= 0) {
                    return number;
                }

                System.out.println("Number cannot be negative.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double getValidPrice(Scanner input) {
        while (true) {
            try {
                System.out.print("Enter Unit Price: ");
                double price = Double.parseDouble(input.nextLine());

                if (Double.isFinite(price) && price >= 0) {
                    return price;
                }

                System.out.println("Unit price must be a valid non-negative number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static int getValidChoice(Scanner input, int minimum, int maximum) {
        while (true) {
            try {
                int choice = Integer.parseInt(input.nextLine());

                if (choice >= minimum && choice <= maximum) {
                    return choice;
                }

                System.out.println("Invalid choice. Please enter a number from "
                        + minimum + " to " + maximum + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}