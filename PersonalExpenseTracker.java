import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PersonalExpenseTracker {

    static ArrayList<Expense> expenses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Add Expense
    public static void addExpense() {

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        expenses.add(new Expense(amount, category, date));

        System.out.println("Expense Added Successfully!");
    }

    // Display Expenses
    public static void displayExpenses() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n----- Expense List -----");

        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    // Monthly Report
    public static void monthlyReport() {

        System.out.print("Enter Month (1-12): ");
        int month = sc.nextInt();

        double total = 0;

        for (Expense e : expenses) {
            if (e.getDate().getMonthValue() == month) {
                total += e.getAmount();
            }
        }

        System.out.println("Total Expense for Month " +
                month + " = ₹" + total);
    }

    // Highest Expense Category
    public static void highestExpenseCategory() {

        HashMap<String, Double> categoryMap = new HashMap<>();

        for (Expense e : expenses) {

            categoryMap.put(
                    e.getCategory(),
                    categoryMap.getOrDefault(
                            e.getCategory(), 0.0)
                            + e.getAmount());
        }

        String highestCategory = "";
        double max = 0;

        for (Map.Entry<String, Double> entry :
                categoryMap.entrySet()) {

            if (entry.getValue() > max) {
                max = entry.getValue();
                highestCategory = entry.getKey();
            }
        }

        System.out.println("Highest Expense Category: "
                + highestCategory);

        System.out.println("Total Amount: ₹" + max);
    }

    // Save to File
    public static void saveToFile() {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter("expenses.txt"))) {

            for (Expense e : expenses) {

                writer.write(
                        e.getAmount() + "," +
                                e.getCategory() + "," +
                                e.getDate());

                writer.newLine();
            }

            System.out.println("Data Saved Successfully.");

        } catch (IOException e) {
            System.out.println("Error Saving File.");
        }
    }

    // Load from File
    public static void loadFromFile() {

        expenses.clear();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader("expenses.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                double amount =
                        Double.parseDouble(data[0]);

                String category = data[1];

                LocalDate date =
                        LocalDate.parse(data[2]);

                expenses.add(
                        new Expense(amount,
                                category,
                                date));
            }

            System.out.println("Data Loaded Successfully.");

        } catch (IOException e) {
            System.out.println("File Not Found.");
        }
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Monthly Report");
            System.out.println("4. Highest Expense Category");
            System.out.println("5. Save Data");
            System.out.println("6. Load Data");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    displayExpenses();
                    break;

                case 3:
                    monthlyReport();
                    break;

                case 4:
                    highestExpenseCategory();
                    break;

                case 5:
                    saveToFile();
                    break;

                case 6:
                    loadFromFile();
                    break;

                case 7:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);
    }
}