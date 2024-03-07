package ui;

import java.util.Scanner;

import model.Expense;
import model.ListOfExpense;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BudgetApp {
    private static final String JSON_STORE = "./data/budget.json";
    private ListOfExpense expenses;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the budget app
    public BudgetApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        expenses = new ListOfExpense("Jun He");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.print("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if ("add".equals(command)) {
            doAdd();
        } else if ("remove".equals(command)) {
            doRemove();
        } else if ("total".equals(command)) {
            doTotal();
        } else if ("show".equals(command)) {
            doShowNames();
        } else if ("cost".equals(command)) {
            doFindCost();
        } else if ("save".equals(command)) {
            doSaveList();
        } else if ("load".equals(command)) {
            doLoadList();
        } else {
            System.out.println("Invalid command!");
        }
    }


    // EFFECTS: displays options user can choose from
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\nadd -> Add expense");
        System.out.println("\nremove -> Remove expense");
        System.out.println("\ntotal -> Total expense");
        System.out.println("\nshow -> Shows the names of all expenses");
        System.out.println("\ncost -> Find cost of an expense from name");
        System.out.println("\nsave -> Saves list of expenses to file");
        System.out.println("\nload -> Loads list of expenses to file");
    }

    // MODIFIES: this
    // EFFECTS: adds a new expense
    private void doAdd() {
        Expense newExpense;
        input.nextLine();
        System.out.print("\nEnter name of expense: ");
        String name = input.nextLine();
        System.out.print("\nEnter amount of expense: $");
        double amount = input.nextDouble();
        newExpense = new Expense(name, amount);

        expenses.addExpense(newExpense);
    }

    // MODIFIES: this
    // EFFECTS: removes an expense
    private void doRemove() {
        input.nextLine();
        System.out.print("\nEnter name of expense you wish to remove: ");
        String name = input.nextLine();

        expenses.removeExpense(name);
    }

    // EFFECTS: returns the total cost of all expenses
    private void doTotal() {
        System.out.print("\nYour total expenses are: $" + expenses.getTotal());
    }

    // EFFECTS: shows the names of all expenses
    private void doShowNames() {
        System.out.println("\nHere is a list of all of your expenses:");

        for (String name : expenses.returnNames()) {
            System.out.println(name);
        }
    }

    // EFFECTS: returns the cost of a specific expense
    private void doFindCost() {
        System.out.print("Enter the name of the expense you wish to find the cost of: ");
        String name = input.next();
        double cost = expenses.findCost(name);

        if (cost == -1) {
            System.out.println("Cannot find item!");
        } else {
            System.out.print("Your cost is" + expenses.findCost(name));
        }
    }

    // EFFECTS: saves the list of expenses to file
    private void doSaveList() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenses);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the list of expenses from file
    private void doLoadList() {
        try {
            expenses = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
