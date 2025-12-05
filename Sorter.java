import java.util.Arrays;
import java.util.Random;

/**
 * Sorter genérico: funciona con T extends Comparable<T>
 */
public class Sorter {

    private static final Random RNG = new Random();

    /* QUICK SORT */
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(arr, lo, hi);
        quickSort(arr, lo, p - 1);
        quickSort(arr, p + 1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int lo, int hi) {
        // pivote aleatorio -> reduce chance de peor caso
        int pivotIndex = lo + RNG.nextInt(hi - lo + 1);
        T pivot = arr[pivotIndex];
        swap(arr, pivotIndex, hi); // mover pivote a la derecha
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (arr[i].compareTo(pivot) < 0) {
                swap(arr, i, store);
                store++;
            }
        }
        swap(arr, store, hi); // colocar pivote en su sitio final
        return store;
    }

    /* MERGE SORT */
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        if (arr.length <= 1) return;
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[arr.length];
        mergeSortRec(arr, aux, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSortRec(T[] arr, T[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSortRec(arr, aux, lo, mid);
        mergeSortRec(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private static <T extends Comparable<T>> void merge(T[] arr, T[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (arr[i].compareTo(arr[j]) <= 0) aux[k++] = arr[i++];
            else aux[k++] = arr[j++];
        }
        while (i <= mid) aux[k++] = arr[i++];
        while (j <= hi) aux[k++] = arr[j++];
        for (int idx = lo; idx <= hi; idx++) arr[idx] = aux[idx];
    }

    /* SHELL SORT */
    public static <T extends Comparable<T>> void shellSort(T[] arr) {
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap].compareTo(temp) > 0) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    /* BUBBLE SORT */
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n-1; i++) {
            swapped = false;
            for (int j = 0; j < n-1-i; j++) {
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    swap(arr, j, j+1);
                    swapped = true;
                }
            }
            if (!swapped) break; // ya está ordenado
        }
    }

    private static <T> void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /* Helper: copia del arreglo */
    public static <T> T[] copyOf(T[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }
}
