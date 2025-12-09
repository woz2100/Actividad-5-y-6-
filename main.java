public class Main {

    public static void main(String[] args) {

        Cliente[] clientes = {
            new Cliente(35, "Ana", 5000, "2020-01-02", "2024-12-05"),
            new Cliente(12, "Luis", 3200, "2021-03-11", "2024-10-23"),
            new Cliente(88, "Mario", 2100, "2019-06-18", "2024-11-10"),
            new Cliente(4, "Rebeca", 7800, "2022-08-07", "2024-09-19")
        };

        ShellSort.shellSort(clientes);

        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
}
