public class Cliente implements Comparable<Cliente> {

    private int clave;
    private String nombre;
    private double saldo;
    private String fechaCreacion;
    private String fechaUltimoMovimiento;

    public Cliente(int clave, String nombre, double saldo,
                   String fechaCreacion, String fechaUltimoMovimiento) {
        this.clave = clave;
        this.nombre = nombre;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimoMovimiento = fechaUltimoMovimiento;
    }

    public int getClave() {
        return clave;
    }

    @Override
    public int compareTo(Cliente otro) {
        return Integer.compare(this.clave, otro.clave);
    }

    @Override
    public String toString() {
        return clave + "," + nombre + "," + saldo + ","
               + fechaCreacion + "," + fechaUltimoMovimiento;
    }
}
