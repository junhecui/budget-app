package ui;

import java.util.Scanner;

import model.Expense;
import model.ListOfExpense;

public class BudgetApp {
    private ListOfExpense expenses = new ListOfExpense();
    private Scanner input;

    // EFFECTS: runs the budget app
    public BudgetApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

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

    private void processCommand(String command) {
        if (command.equals("add")) {
            doAdd();
        } else if (command.equals("remove")) {
            doRemove();
        } else if (command.equals("total")) {
            doTotal();
        } else if (command.equals("show")) {
            doShowNames();
        } else if (command.equals("cost")) {
            doFindCost();
        } else {
            System.out.println("Invalid command!");
        }
    }

    private void init() {
        input = new Scanner(System.in);
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\nadd -> Add expense");
        System.out.println("\nremove -> Remove expense");
        System.out.println("\ntotal -> Total expense");
        System.out.println("\nshow -> Shows the names of all expenses");
        System.out.println("\ncost -> Find cost of an expense from name");
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

    //
    private void doFindCost() {
        System.out.print("Enter the name of the expense you wish to find the cost of: ");
        String name = input.next();

        System.out.print("The cost of " + name + " is: $" + expenses.findCost(name));
    }
}
