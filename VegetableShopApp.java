package com.unit;



import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VegetableShopApp {

    
    static class Vegetable {
        private String name;
        private double pricePerKg;

        public Vegetable(String name, double pricePerKg) {
            this.name = name;
            this.pricePerKg = pricePerKg;
        }

        public String getName() {
            return name;
        }

        public double getPricePerKg() {
            return pricePerKg;
        }

        public void setPricePerKg(double pricePerKg) {
            this.pricePerKg = pricePerKg;
        }

        @Override
        public String toString() {
            return name + " - Rs " + pricePerKg + " per kg";
        }
    }

    static class VegetableShop {
        private Map<String, Vegetable> inventory = new HashMap<>();

        public void addVegetable(Vegetable veg) {
            inventory.put(veg.getName(), veg);
        }

        public Vegetable getVegetable(String name) {
            return inventory.get(name);
        }

        public void updatePrice(String name, double newPrice) {
            Vegetable veg = inventory.get(name);
            if (veg == null) throw new IllegalArgumentException("Vegetable not found");
            veg.setPricePerKg(newPrice);
        }

        public void displayInventory() {
            if (inventory.isEmpty()) {
                System.out.println("Inventory is empty.");
                return;
            }
            System.out.println("Current Inventory:");
            for (Vegetable veg : inventory.values()) {
                System.out.println(veg);
            }
        }

        public int getTotalVegetables() {
            return inventory.size();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VegetableShop shop = new VegetableShop();

        System.out.print("How many vegetables to add:");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline


        for (int i = 0; i < n; i++) {
            System.out.print("Enter vegetable name: ");
            String name = sc.nextLine();
            System.out.print("Enter price per kg: ");
            double price = sc.nextDouble();
            sc.nextLine(); // consume newline
            shop.addVegetable(new Vegetable(name, price));
        }

        shop.displayInventory();

        System.out.print("Do you want to update a vegetable price(yes/no): ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter vegetable name to update: ");
            String name = sc.nextLine();
            System.out.print("Enter new price: ");
            double newPrice = sc.nextDouble();
            sc.nextLine();
            try {
                shop.updatePrice(name, newPrice);
                System.out.println("Price updated successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        shop.displayInventory();
        System.out.println("Total vegetables in shop: " + shop.getTotalVegetables());

        sc.close();
    }
}
