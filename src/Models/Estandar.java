package Models;

public class Estandar extends Habitacion {

    private boolean tv; // tiene o no tele una tele
    private boolean desayuno; // tiene o no desayuno

    public Estandar(String codigo, int piso, double precio, boolean aireAcondicionado, boolean wifi, int numCamas, int capacidad, boolean tv, boolean desayuno) {
        super(codigo, piso, precio, aireAcondicionado, wifi, "Estandar"); // Pass "Estandar" as tipo
        this.tv = tv;
        this.desayuno = desayuno;
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
        double precioFinal = super.calcularPrecio(dias); // Base price
        if (this.tv) {
            precioFinal += 15 * dias; // Additional cost for TV
        }
        if (this.desayuno) {
            precioFinal += 10 * dias; // Additional cost for breakfast
        }
        return precioFinal;
    }
}
