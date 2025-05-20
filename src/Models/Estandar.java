package Models;

public class Estandar extends Habitacion{

    private int numCamas; // maximo 2 camas
    private int capacidad; // maximo 2-3 personas
    private boolean tv; // tiene o no tele una tele
    private boolean desayuno; // tiene o no desayuno

    public Estandar(String codigo, int piso, double precio, boolean disponible, boolean aireAcondicionado, boolean wifi, int numCamas, int capacidad, boolean tv, boolean desayuno) {
        super(codigo, piso, precio, disponible, aireAcondicionado, wifi);
        this.numCamas = numCamas;
        this.capacidad = capacidad;
        this.tv = tv;
        this.desayuno = desayuno;
    }

    public Estandar() {
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isDesayuno() {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
    }

    @Override
    public double calcularPrecio(int dias) {
        double precioFinal = getPrecio() * dias; // Precio base por los días de estancia

        if (isAireAcondicionado()) {
            precioFinal += 20 * dias; // Costo adicional por aire acondicionado
        }
        if (isWifi()) {
            precioFinal += 10 * dias; // Costo adicional por wifi
        }
        if (this.tv) {
            precioFinal += 15 * dias; // Costo adicional por TV
        }
        if (this.desayuno) {
            precioFinal += 10 * dias; // Costo adicional por desayuno
        }
        return precioFinal;
    }

}
