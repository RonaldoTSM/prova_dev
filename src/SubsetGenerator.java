import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class SubsetGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter elements separated by space (max 4 elements): ");
        String input = scanner.nextLine();
        scanner.close();

        Set<Integer> inputSet = new HashSet<>();
        String[] parts = input.trim().split("\\s+");
        for (int i = 0; i < parts.length && i < 4; i++) {
            inputSet.add(Integer.parseInt(parts[i]));
        }

        Set<Set<Integer>> allSubsets = getSubSets(inputSet);

        ArrayList<Set<Integer>> sorted = new ArrayList<>(allSubsets);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = i + 1; j < sorted.size(); j++) {
                if (sorted.get(j).size() > sorted.get(i).size()) {
                    Set<Integer> temp = sorted.get(i);
                    sorted.set(i, sorted.get(j));
                    sorted.set(j, temp);
                }
            }
        }

        System.out.print("[");
        for (int i = 0; i < sorted.size(); i++) {
            Set<Integer> subset = sorted.get(i);
            System.out.print("[");
            int count = 0;
            for (Integer num : subset) {
                System.out.print(num);
                count++;
                if (count < subset.size()) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            if (i < sorted.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * The function below will:
     * - Take a Set of integers as input (representing the original set)
     * - Generate and return a Set containing all possible subsets of the input set
     * - Each subset is represented as a Set with no duplicate elements
     * - The collection of subsets is also a Set, with each element being a unique subset
     * - The order of elements in subsets and the order of subsets themselves is irrelevant
     */

    public static Set<Set<Integer>> getSubSets(Set<Integer> inputSet) {
        Set<Set<Integer>> result = new HashSet<>();

        Integer[] elements = new Integer[inputSet.size()];
        int idx = 0;
        for (Integer e : inputSet) {
            elements[idx++] = e;
        }

        int total = 1 << elements.length;

        for (int i = 0; i < total; i++) {
            Set<Integer> subset = new HashSet<>();
            for (int j = 0; j < elements.length; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(elements[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }
}