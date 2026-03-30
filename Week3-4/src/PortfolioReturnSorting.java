import java.util.*;

class Asset {
    String name;
    double returnRate;   // in percentage
    double volatility;   // risk measure

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "% (vol=" + volatility + ")";
    }
}

public class PortfolioReturnSorting {

    // Merge Sort (ascending returnRate, stable)
    public static void mergeSort(Asset[] assets, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(assets, left, mid);
            mergeSort(assets, mid + 1, right);
            merge(assets, left, mid, right);
        }
    }

    private static void merge(Asset[] assets, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = assets[left + i];
        for (int j = 0; j < n2; j++) R[j] = assets[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                assets[k++] = L[i++];
            } else {
                assets[k++] = R[j++];
            }
        }
        while (i < n1) assets[k++] = L[i++];
        while (j < n2) assets[k++] = R[j++];
    }

    // Quick Sort (descending returnRate, volatility ascending)
    public static void quickSort(Asset[] assets, int low, int high) {
        if (low < high) {
            int pi = partition(assets, low, high);
            quickSort(assets, low, pi - 1);
            quickSort(assets, pi + 1, high);
        }
    }

    private static int partition(Asset[] assets, int low, int high) {
        // Pivot selection: median-of-3 strategy
        int mid = (low + high) / 2;
        double pivotValue = medianOf3(assets[low].returnRate, assets[mid].returnRate, assets[high].returnRate);
        double pivot = pivotValue;

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compare(assets[j], pivot) > 0) { // keep higher returnRate first
                i++;
                Asset temp = assets[i];
                assets[i] = assets[j];
                assets[j] = temp;
            }
        }
        Asset temp = assets[i + 1];
        assets[i + 1] = assets[high];
        assets[high] = temp;
        return i + 1;
    }

    // Comparator: returnRate DESC, volatility ASC
    private static int compare(Asset a, double pivotReturn) {
        return Double.compare(a.returnRate, pivotReturn);
    }

    private static double medianOf3(double a, double b, double c) {
        if ((a > b) != (a > c)) return a;
        else if ((b > a) != (b > c)) return b;
        else return c;
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.25),
                new Asset("TSLA", 8.0, 0.40),
                new Asset("GOOG", 15.0, 0.20)
        };

        // Merge Sort (ascending returnRate)
        Asset[] mergeSorted = Arrays.copyOf(assets, assets.length);
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("MergeSort result: " + Arrays.toString(mergeSorted));

        // Quick Sort (descending returnRate + volatility ASC)
        Asset[] quickSorted = Arrays.copyOf(assets, assets.length);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort result: " + Arrays.toString(quickSorted));
    }
}

