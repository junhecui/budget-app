package persistence;

import model.Expense;
import model.ListOfExpense;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfExpense expenses = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyList.json");
        try {
            ListOfExpense expenses = reader.read();
            assertEquals("My Budget", expenses.getName());
            assertEquals(0, expenses.count());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralList.json");
        try {
            ListOfExpense expenses = reader.read();
            assertEquals("Jun He", expenses.getName());
            List<Expense> listOfExpense = expenses.getExpenses();
            assertEquals(3, expenses.count());
            checkExpense("Big Mac", 5, expenses.getExpense(0));
            checkExpense("Drink", 2, expenses.getExpense(1));
            checkExpense("Fries", 3, expenses.getExpense(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}