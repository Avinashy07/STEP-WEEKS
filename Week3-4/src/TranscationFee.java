import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // simple string for demo, could use LocalTime

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ": " + fee + "@" + timestamp;
    }
}

public class TranscationFee {

    // Bubble Sort: ascending by fee
    public static void bubbleSort(List<Transaction> transactions) {
        int n = transactions.size();
        boolean swapped;
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    Collections.swap(transactions, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break; // early termination
        }

        System.out.println("BubbleSort result: " + transactions);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort: ascending by fee, then timestamp
    public static void insertionSort(List<Transaction> transactions) {
        int n = transactions.size();
        int shifts = 0;

        for (int i = 1; i < n; i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;

            while (j >= 0 && compare(transactions.get(j), key) > 0) {
                transactions.set(j + 1, transactions.get(j));
                j--;
                shifts++;
            }
            transactions.set(j + 1, key);
        }

        System.out.println("InsertionSort result: " + transactions);
        System.out.println("Shifts: " + shifts);
    }

    // Comparator: fee first, then timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp); // lexicographic for demo
    }

    // Flag high-fee outliers
    public static void flagOutliers(List<Transaction> transactions) {
        System.out.print("High-fee outliers: ");
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.fee > 50.0) {
                System.out.print(t + " ");
                found = true;
            }
        }
        if (!found) System.out.print("none");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Small batch (≤ 100) → Bubble Sort
        if (transactions.size() <= 100) {
            bubbleSort(new ArrayList<>(transactions));
        }

        // Medium batch (100–1000) → Insertion Sort
        if (transactions.size() > 100 && transactions.size() <= 1000) {
            insertionSort(new ArrayList<>(transactions));
        } else {
            // For demo, force insertion sort on small batch too
            insertionSort(new ArrayList<>(transactions));
        }

        // Flag outliers
        flagOutliers(transactions);
    }
}

