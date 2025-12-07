package Final_Project;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HyperIndexEngine<String, ExampleRecord> engine = new HyperIndexEngine<>();
        Scanner sc = new Scanner(System.in);
        ExampleRecordParser parser = new ExampleRecordParser();

        while (true) {
            System.out.println("\n=== HyperIndex Fusion Engine ===");
            System.out.println("1. Insert record");
            System.out.println("2. Delete record");
            System.out.println("3. Search record by ID");
            System.out.println("4. Range query by ID");
            System.out.println("5. Display Top-K by relatedInformation1 (or custom comparator)");
            System.out.println("6. Load from CSV");
            System.out.println("7. Save to CSV");
            System.out.println("8. Display all records (sorted by ID)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Related Information 1: ");
                    String info1 = sc.nextLine();
                    System.out.print("Enter Related Information 2: ");
                    String info2 = sc.nextLine();
                    engine.insert(id, new ExampleRecord(id, info1, info2));
                    System.out.println("Inserted successfully.");
                    break;
                case 2:
                    System.out.print("Enter ID to delete: ");
                    id = sc.nextLine();
                    engine.delete(id);
                    System.out.println("Deleted if existed.");
                    break;
                case 3:
                    System.out.print("Enter ID to search: ");
                    id = sc.nextLine();
                    ExampleRecord rec = engine.search(id);
                    System.out.println(rec != null ? rec : "Record not found.");
                    break;
                case 4:
                    System.out.print("Enter lower ID: ");
                    String lower = sc.nextLine();
                    System.out.print("Enter upper ID: ");
                    String upper = sc.nextLine();
                    List<ExampleRecord> range = engine.rangeQuery(lower, upper);
                    for (ExampleRecord r : range) System.out.println(r);
                    break;
                case 5:
                    System.out.print("Enter K: ");
                    int k = sc.nextInt(); sc.nextLine();
                    List<ExampleRecord> topK = engine.topK(k, Comparator.comparing(a -> a.relatedInformation1));
                    for (ExampleRecord r : topK) System.out.println(r);
                    break;
                case 6:
                    System.out.print("Enter CSV file path to load: ");
                    String loadPath = sc.nextLine();
                    engine.loadCSV(loadPath, parser);
                    System.out.println("Data loaded.");
                    break;
                case 7:
                    System.out.print("Enter CSV file path to save: ");
                    String savePath = sc.nextLine();
                    engine.saveCSV(savePath, parser);
                    System.out.println("Data saved.");
                    break;
                case 8:
                    List<ExampleRecord> all = engine.getSorted();
                    for (ExampleRecord r : all) System.out.println(r);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
