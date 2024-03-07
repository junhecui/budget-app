package persistence;

import model.Expense;
import model.ListOfExpense;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// Represents a reader that reads list of expense from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfExpense read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenses(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses expenses from JSON object and returns it
    private ListOfExpense parseExpenses(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListOfExpense expenses = new ListOfExpense(name);
        addExpenses(expenses, jsonObject);
        return expenses;
    }

    // MODIFIES: expenses
    // EFFECTS: parses expenses from JSON object and adds them to list
    private void addExpenses(ListOfExpense expenses, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(expenses, nextExpense);
        }
    }

    // MODIFIES: expenses
    // EFFECTS: parses expense from JSON object and adds it to list
    private void addExpense(ListOfExpense expenses, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double amount = jsonObject.getDouble("amount");
        Expense expense = new Expense(name, amount);
        expenses.addExpense(expense);
    }
}
