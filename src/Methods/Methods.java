package Methods;

import java.util.*;

public abstract class Methods {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();
    private static final int elementsInLine = 10;

    public static int getInt() {
        return sc.nextInt();
    }

    public static String getString() {
        return sc.nextLine();
    }

    public static ArrayList<Integer> inIntArray(int size) {
        ArrayList<Integer> array = new ArrayList<>();
        System.out.println("Enter " + size + " elements of an array: ");
        for (int i = 0; i < size; i++) {
            array.add(sc.nextInt());
        }
        return array;
    }

    public static ArrayList<Integer> inIntArray() {
        System.out.println("Enter the size of an array: ");
        int size = sc.nextInt();
        return inIntArray(size);
    }

    public static ArrayList<ArrayList<Integer>> inIntMatrix(int n, int m) {
        ArrayList<ArrayList<Integer>> array = new ArrayList<>();
        System.out.println("Enter " + n * m + " elements of matrix:");
        for (int i = 0; i < n; i++) {
            array.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                array.get(i).add(sc.nextInt());
            }
        }
        return array;
    }

    public static ArrayList<ArrayList<Integer>> inIntMatrix() {
        System.out.println("Specify dimensions of the matrix: ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        return inIntMatrix(n, m);
    }

    public static ArrayList<ArrayList<Integer>> inIntSquareMatrix(int n) {
        return inIntMatrix(n, n);
    }

    public static ArrayList<ArrayList<Integer>> inIntSquareMatrix() {
        System.out.println("Specify size of the square matrix: ");
        int n = sc.nextInt();
        return inIntMatrix(n, n);
    }

    public static ArrayList<Integer> inAnyLengthIntArray() {
        ArrayList<Integer> array = new ArrayList<>();
        System.out.println("Enter elements of an array, then finish with '-1' sign: ");
        int current = sc.nextInt();
        while (current != -1) {
            array.add(current);
            current = sc.nextInt();
        }
        return array;
    }

    public static ArrayList<String> inStringArray(int size) {
        ArrayList<String> array = new ArrayList<>();
        System.out.println("Enter " + size + " elements of an array: ");
        for (int i = 0; i < size; i++) {
            array.add(sc.next());
        }
        return array;
    }

    public static ArrayList<String> inStringArray() {
        System.out.println("Enter the size of an array: ");
        int size = sc.nextInt();
        return inStringArray(size);
    }

    public static <T> void outArray(ArrayList<T> array) {
        System.out.println("Array of " + array.size() + " elements:");
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + " ");
            if (i % elementsInLine == 0 && i != 0) System.out.println();
        }
        System.out.println();
    }

    public static void outIntMatrix(ArrayList<ArrayList<Integer>> array) {
        int n = array.size();
        int m = array.get(0).size();
        System.out.println("Matrix of " + array.size() * array.get(0).size() + " elements:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(array.get(i).get(j) + " ");
                if (j % elementsInLine == 0 && j != 0) System.out.println();
            }
            System.out.println();
        }
    }

    public static ArrayList<Integer> makeRandArray(int size, int bound) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            array.add(rand.nextInt(bound));
        }
        return array;
    }

    public static ArrayList<Integer> makeRandArray(int bound) {
        System.out.println("Enter the size of a randomly filled array: ");
        int size = sc.nextInt();
        return makeRandArray(size, bound);
    }

    public static void sort(ArrayList<Integer> array) {
        quickSort(array, 0, array.size() - 1);
    }

    private static void quickSort(ArrayList<Integer> array, int left, int right) {
        int key = array.get(left + (right - left) / 2);
        int i = left, j = right;
        while (i <= j) {
            while (array.get(i) < key)
                i++;

            while (array.get(j) > key)
                j--;

            if (i <= j) {
                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSort(array, left, j);
        if (right > i)
            quickSort(array, i, right);
    }

    public static void line() {
        System.out.println("-----------------------------------" +
                "------------------------------------------------" +
                "------------------------------------------------" +
                "------------------------------------------------");
    }
}