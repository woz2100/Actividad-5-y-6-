import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase Cliente que implementa Comparable<Cliente> usando la clave (int).
 */
public class Cliente implements Comparable<Cliente> {
    private int clave;
    private String nombre;
    private double saldo;
    private LocalDate fechaCreacion;
    private LocalDate fechaUltMov;

    private static final DateTimeFormatter DF = DateTimeFormatter.ISO_LOCAL_DATE;

    public Cliente(int clave, String nombre, double saldo, LocalDate fechaCreacion, LocalDate fechaUltMov) {
        this.clave = clave;
        this.nombre = nombre;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltMov = fechaUltMov;
    }

    public int getClave() { return clave; }
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public LocalDate getFechaUltMov() { return fechaUltMov; }

    @Override
    public int compareTo(Cliente o) {
        return Integer.compare(this.clave, o.clave);
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%.2f,%s,%s",
                clave,
                nombre,
                saldo,
                fechaCreacion.format(DF),
                fechaUltMov.format(DF));
    }

    // Parsea una línea CSV con el formato: clave,nombre,saldo,fechaCreacion,fechaUltMov
    public static Cliente fromCSV(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length < 5) throw new IllegalArgumentException("CSV inválido: " + csvLine);
        int clave = Integer.parseInt(parts[0].trim());
        String nombre = parts[1].trim();
        double saldo = Double.parseDouble(parts[2].trim());
        LocalDate fc = LocalDate.parse(parts[3].trim());
        LocalDate fu = LocalDate.parse(parts[4].trim());
        return new Cliente(clave, nombre, saldo, fc, fu);
    }
}
