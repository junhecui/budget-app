package ui;

import model.Expense;
import model.ListOfExpense;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Represents the GUI of a Budgeting App
public class BudgetAppGUI extends JFrame implements ActionListener, ListSelectionListener {
    private JLabel nameLabel;
    private JLabel amountLabel;
    private JTextField nameField;
    private JTextField amountField;
    private JButton addButton;
    private JButton removeButton;
    private JList<String> expenseList;
    private ArrayList<Expense> expenses;
    private DefaultListModel<String> expenseModel;
    private GridBagConstraints gb;
    private ListOfExpense listOfExpense;

    private JButton saveButton;
    private JButton loadButton;
    private static final String BUDGET = "./data/budget.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private ImageIcon happy;
    private ImageIcon frown;
    private JLabel happyLabel;
    private JLabel frownlabel;
    private JFrame happyFrame;
    private JFrame frownFrame;

    // EFFECTS: constructs the gui
    public BudgetAppGUI() {
        super("Budget App");
        init();
        setLayout(new GridBagLayout());
        gb = new GridBagConstraints();
        layoutGUI();
        setButtons(addButton, removeButton, saveButton, loadButton); // todo
        setGUI();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // EFFECTS: initializes the fields of the application
    public void init() {
        nameLabel = new JLabel("Expense Name:");
        amountLabel = new JLabel("Amount:");
        nameField = new JTextField(20);
        amountField = new JTextField(10);
        addButton = new JButton("Add Expense");
        removeButton = new JButton("Remove Expense");
        saveButton = new JButton("Save Expenses");
        loadButton = new JButton("Load Expenses");
        expenseModel = new DefaultListModel<>();
        listOfExpense = new ListOfExpense("Expenses");
        expenseList = new JList<>(expenseModel);
        expenseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        expenseList.addListSelectionListener(this);
        expenses = new ArrayList<>();
        jsonWriter = new JsonWriter(BUDGET);
        jsonReader = new JsonReader(BUDGET);
        happy = new ImageIcon("./data/happy.png");
        frown = new ImageIcon("./data/sad.png");
        happyLabel = new JLabel(happy);
        frownlabel = new JLabel(frown);
        happyFrame = new JFrame("YAY!");
        frownFrame = new JFrame("Uh Oh...");
    }

    // MODIFIES: this
    // EFFECTS: create the layout of the GUI
    public void layoutGUI() {
        firstRow();
        secondRow();
        thirdRow();
        fourthRow();
        fifthRow();

    }

    // EFFECTS: creates name label and text field
    public void firstRow() {
        gb.gridx = 0;
        gb.gridy = 0;
        gb.anchor = GridBagConstraints.LINE_END;
        gb.insets = new Insets(10, 10, 0, 0);
        add(nameLabel, gb);

        gb.gridx = 1;
        gb.gridy = 0;
        gb.anchor = GridBagConstraints.LINE_START;
        gb.insets = new Insets(10, 0, 0, 10);
        add(nameField, gb);
    }

    // EFFECTS: creates balance label and text field
    public void secondRow() {
        gb.gridx = 0;
        gb.gridy = 1;
        gb.anchor = GridBagConstraints.LINE_END;
        gb.insets = new Insets(10, 10, 0, 0);
        add(amountLabel, gb);

        gb.gridx = 1;
        gb.gridy = 1;
        gb.anchor = GridBagConstraints.LINE_START;
        gb.insets = new Insets(10, 0, 0, 10);
        add(amountField, gb);
    }

    // EFFECTS: creates add and remove buttons
    public void thirdRow() {
        gb.gridx = 0;
        gb.gridy = 2;
        gb.anchor = GridBagConstraints.LINE_END;
        gb.insets = new Insets(10, 10, 0, 0);
        add(addButton, gb);

        gb.gridx = 1;
        gb.gridy = 2;
        gb.anchor = GridBagConstraints.LINE_START;
        gb.insets = new Insets(10, 0, 0, 10);
        add(removeButton, gb);
    }

    // EFFECTS: creates save and load buttons
    public void fourthRow() {
        gb.gridx = 0;
        gb.gridy = 3;
        gb.anchor = GridBagConstraints.LINE_END;
        gb.insets = new Insets(10, 10, 0, 0);
        add(saveButton, gb);

        gb.gridx = 1;
        gb.gridy = 3;
        gb.anchor = GridBagConstraints.LINE_START;
        gb.insets = new Insets(10, 0, 0, 10);
        add(loadButton, gb);
    }

    // EFFECTS: creates box for expense list
    public void fifthRow() {
        gb.gridx = 0;
        gb.gridy = 4;
        gb.gridwidth = 2;
        gb.fill = GridBagConstraints.BOTH;
        gb.weightx = 1.0;
        gb.weighty = 1.0;
        gb.insets = new Insets(10, 10, 10, 10);
        add(new JScrollPane(expenseList), gb);
    }

    // EFFECTS: set action listeners for buttons
    public void setButtons(JButton addButton, JButton removeButton, JButton saveButton, JButton loadButton) {
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    // EFFECTS: sets the properties of the GUI
    public void setGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addFunction();
            displayFrown();
        }
        if (e.getSource() == removeButton) {
            removeFunction();
            displaySmile();
        }
        if (e.getSource() == saveButton) {
            saveFunction();
        }
        if (e.getSource() == loadButton) {
            loadFunction();
        }
    }

    // EFFECTS: displays a smile image in a separate window
    public void displaySmile() {
        happyFrame.getContentPane().add(happyLabel);
        happyFrame.pack();
        happyFrame.setVisible(true);
    }

    // EFFECTS: displays a frown image in a separate window
    public void displayFrown() {
        frownFrame.getContentPane().add(frownlabel);
        frownFrame.pack();
        frownFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds account to list and displays it
    public void addFunction() {
        String name = nameField.getText();
        double amount = Double.parseDouble(amountField.getText());
        Expense expense = new Expense(name, amount);
        listOfExpense.addExpense(expense);
        expenses.add(expense);
        expenseModel.addElement(expense.toString());
        nameField.setText("");
        amountField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: removes account and takes it off GUI
    public void removeFunction() {
        int index = expenseList.getSelectedIndex();
        Expense e = expenses.get(index);
        listOfExpense.removeExpense(e.getName());
        expenses.remove(index);
        expenseModel.removeElementAt(index);
    }

    // EFFECTS: makes list of type ListOfExpense
    public ListOfExpense loe() {
        ListOfExpense loe = new ListOfExpense("Accounts");
        for (Expense e : expenses) {
            loe.addExpense(e);
        }
        return loe;
    }

    // MODIFIES: BUDGET
    // EFFECTS: saves accounts to JSON file
    public void saveFunction() {
        try {
            jsonWriter.open();
            jsonWriter.write(loe());
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // EFFECT: loads accounts from JSON file
    public void loadFunction() {
        try {
            ListOfExpense loe = jsonReader.read();
            int size = loe.count();
            for (int i = 0; i < size; i++) {
                Expense ca = loe.getExpense(i);
                expenses.add(ca);
                expenseModel.addElement(ca.toString());
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (expenseList.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }
}