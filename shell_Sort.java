public class ShellSort {

    public static <T extends Comparable<T>> void shellSort(T[] arreglo) {

        int n = arreglo.length;
        int gap = n / 2;

        while (gap > 0) {

            for (int i = gap; i < n; i++) {

                T temporal = arreglo[i];
                int j = i;

                while (j >= gap && arreglo[j - gap].compareTo(temporal) > 0) {
                    arreglo[j] = arreglo[j - gap];
                    j -= gap;
                }

                arreglo[j] = temporal;
            }

            gap /= 2;
        }
    }
}
