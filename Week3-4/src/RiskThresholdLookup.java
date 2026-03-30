import java.util.*;

public class RiskThresholdLookup {

    // Linear Search: threshold match in unsorted array
    public static int linearSearch(int[] risks, int target) {
        int comparisons = 0;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                System.out.println("Linear: threshold=" + target + " → found at index " + i + " (" + comparisons + " comps)");
                return i;
            }
        }
        System.out.println("Linear: threshold=" + target + " → not found (" + comparisons + " comps)");
        return -1;
    }

    // Binary Search: find floor and ceiling in sorted array
    public static void binaryFloorCeiling(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int comparisons = 0;
        Integer floor = null, ceiling = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (risks[mid] == target) {
                floor = risks[mid];
                ceiling = risks[mid];
                break;
            } else if (risks[mid] < target) {
                floor = risks[mid];
                low = mid + 1;
            } else {
                ceiling = risks[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary floor(" + target + "): " + (floor != null ? floor : "none") +
                ", ceiling: " + (ceiling != null ? ceiling : "none") +
                " (" + comparisons + " comps)");
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100}; // sorted risk bands

        // Linear Search (unsorted scenario)
        linearSearch(risks, 30);

        // Binary Search (sorted scenario)
        binaryFloorCeiling(risks, 30);
    }
}

