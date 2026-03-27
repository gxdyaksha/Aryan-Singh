package com.unit;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class VegetableShopAppTest {
    private VegetableShopApp.VegetableShop shop;
    private VegetableShopApp.Vegetable tomato;
    private VegetableShopApp.Vegetable potato;

    @BeforeEach
    void setUp() {
        shop = new VegetableShopApp.VegetableShop();
        tomato = new VegetableShopApp.Vegetable("Tomato", 30.0);
        potato = new VegetableShopApp.Vegetable("Potato", 20.0);

        shop.addVegetable(tomato);
        shop.addVegetable(potato);
    }

    @Test
    void testAddAndGetVegetable() {
        assertEquals(tomato, shop.getVegetable("Tomato"));
        assertEquals(potato, shop.getVegetable("Potato"));
        assertNull(shop.getVegetable("Onion")); 
    }

    @Test
    void testUpdatePrice() {
        shop.updatePrice("Tomato", 35.0);
        assertEquals(35.0, shop.getVegetable("Tomato").getPricePerKg());
    }

    @Test
    void testUpdatePriceNonExistentVegetable() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.updatePrice("Onion", 40.0);
        });
        assertEquals("Vegetable not found", exception.getMessage());
    }

    @Test
    void testTotalVegetables() {
        assertEquals(2, shop.getTotalVegetables());
    }

    @Test
    void testDisplayInventoryNotEmpty() {
    
        assertDoesNotThrow(() -> shop.displayInventory());
    }

    @Test
    void testVegetableToString() {
        String expected = "Tomato - Rs 30.0 per kg";
        assertEquals(expected, tomato.toString());
    }
}
