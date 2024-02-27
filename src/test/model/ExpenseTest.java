package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {
    private Expense car;
    private Expense burger;

    @BeforeEach
    public void setUp() {
        car = new Expense("Tesla Model Y", 100000);
        burger = new Expense("McChicken", 5.25);
    }

    @Test
    public void getNameTest() {
        assertEquals(car.getName(),"Tesla Model Y");
        assertEquals(burger.getName(), "McChicken");
        assertNotEquals(burger.getName(), "Mcchicken");
        assertNotEquals(car.getName(), "McChicken");
    }

    @Test
    public void getAmountTest() {
        assertEquals(car.getAmount(), 100000);
        assertEquals(car.getAmount(), 100000.00);
        assertNotEquals(car.getAmount(), 5.25);
        assertEquals(burger.getAmount(), 5.25);
        assertNotEquals(burger.getAmount(), 5.2500001);
    }
}
