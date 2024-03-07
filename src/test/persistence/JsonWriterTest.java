package persistence;

import model.Expense;
import model.ListOfExpense;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfExpense wr = new ListOfExpense("My budget");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            ListOfExpense expenses = new ListOfExpense("My budget");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(expenses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            expenses = reader.read();
            assertEquals("My budget", expenses.getName());
            assertEquals(0, expenses.count());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralList() {
        try {
            ListOfExpense expenses = new ListOfExpense("Jun He");
            expenses.addExpense(new Expense("Big Mac", 5));
            expenses.addExpense(new Expense("Drink", 2));
            expenses.addExpense(new Expense("Fries", 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralList.json");

            writer.open();
            writer.write(expenses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralList.json");
            expenses = reader.read();
            assertEquals("Jun He", expenses.getName());
            List<Expense> thingies = expenses.getExpenses();
            assertEquals(3, expenses.count());
            checkExpense("Big Mac", 5, expenses.getExpense(0));
            checkExpense("Drink", 2, expenses.getExpense(1));
            checkExpense("Fries", 3, expenses.getExpense(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}