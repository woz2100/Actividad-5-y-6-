import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

/**
 * Main para ejecutar y comparar algoritmos. Ajusta rutas y cantidades según lo necesites.
 */
public class Main {
    private static final Random RNG = new Random();

    public static void main(String[] args) throws IOException {
        // Si se proporciona un CSV como argumento, lo usamos
        Cliente[] base;
        if (args.length > 0) {
            String path = args[0];
            base = CSVLoader.loadClientesFromCSV(path);
            System.out.printf("Leídos %d clientes desde %s%n", base.length, path);
        } else {
            // Si no, generamos un conjunto pequeño de ejemplo
            base = generateSampleClientes(30);
            System.out.println("Generados clientes de ejemplo.");
        }

        // Mostrar algunos
        System.out.println("Primeros 5 registros (entrada):");
        for (int i = 0; i < Math.min(5, base.length); i++) System.out.println(base[i]);

        // Ejecutar varias corridas: mejor (ya ordenado), peor (orden inverso), aleatorio
        Cliente[] arrBest = Sorter.copyOf(base);
        Arrays.sort(arrBest); // estándar java (mejor: ya ordenado)

        Cliente[] arrWorst = invertArray(Arrays.copyOf(base, base.length));
        Arrays.sort(arrWorst); // ahora arrWorst contiene datos ordenados asc, los invertimos abajo
        arrWorst = invertArray(arrWorst); // para simular arreglo en orden descendente

        Cliente[] arrRandom = shuffleCopy(Arrays.copyOf(base, base.length));

        runAndReport("QuickSort (mejor)", Sorter.copyOf(arrBest), "quick");
        runAndReport("QuickSort (peor)", Sorter.copyOf(arrWorst), "quick");
        runAndReport("QuickSort (aleatorio)", Sorter.copyOf(arrRandom), "quick");

        runAndReport("MergeSort (aleatorio)", Sorter.copyOf(arrRandom), "merge");
        runAndReport("ShellSort (aleatorio)", Sorter.copyOf(arrRandom), "shell");
        runAndReport("BubbleSort (aleatorio)", Sorter.copyOf(arrRandom), "bubble");

        // Búsqueda binaria: buscar una clave conocida (por ejemplo clave del primer elemento ordenado)
        Cliente[] sortedForSearch = Sorter.copyOf(arrRandom);
        Arrays.sort(sortedForSearch);
        if (sortedForSearch.length > 0) {
            Cliente objetivo = sortedForSearch[sortedForSearch.length/2];
            System.out.println("Buscando (binary search) cliente: " + objetivo.getClave());
            int idx = Search.binarySearch(sortedForSearch, objetivo);
            System.out.println("Resultado búsqueda: índice = " + idx + ", valor = " + (idx >= 0 ? sortedForSearch[idx] : "no encontrado"));
        }
    }

    private static void runAndReport(String name, Cliente[] arr, String method) {
        Cliente[] working = arr;
        long t0 = System.nanoTime();
        switch (method) {
            case "quick": Sorter.quickSort(working); break;
            case "merge": Sorter.mergeSort(working); break;
            case "shell": Sorter.shellSort(working); break;
            case "bubble": Sorter.bubbleSort(working); break;
            default: throw new IllegalArgumentException("Método desconocido");
        }
        long t1 = System.nanoTime();
        System.out.printf("%s -> n=%d, tiempo=%.3f ms, ordenado=%b%n",
                name, working.length, (t1 - t0) / 1_000_000.0, isSorted(working));
    }

    private static boolean isSorted(Cliente[] arr) {
        for (int i = 1; i < arr.length; i++) if (arr[i-1].compareTo(arr[i]) > 0) return false;
        return true;
    }

    private static Cliente[] invertArray(Cliente[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            Cliente tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
        return arr;
    }

    private static Cliente[] shuffleCopy(Cliente[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = RNG.nextInt(i + 1);
            Cliente tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
        return arr;
    }

    private static Cliente[] generateSampleClientes(int n) {
        Cliente[] a = new Cliente[n];
        for (int i = 0; i < n; i++) {
            int clave = i + 1;
            String nombre = "Cliente" + clave;
            double saldo = RNG.nextDouble() * 10000;
            LocalDate fc = LocalDate.of(2020 + RNG.nextInt(6), 1 + RNG.nextInt(12), 1 + RNG.nextInt(28));
            LocalDate fu = fc.plusDays(RNG.nextInt(1000));
            a[i] = new Cliente(clave, nombre, saldo, fc, fu);
        }
        // Desordenamos para tener caso aleatorio
        for (int i = n-1; i > 0; i--) {
            int j = RNG.nextInt(i+1);
            Cliente tmp = a[i]; a[i] = a[j]; a[j] = tmp;
        }
        return a;
    }
}
