package Models;

public class Vip extends Habitacion {

    private String tipoCamas;
    private double metrosCuadrados;
    private int numCuartos;
    private int numTvs;
    private boolean TvStreamingPremium;
    private boolean jacuzzi;
    private boolean serviciohabitacion;

    public Vip(String codigo, int piso, double precio, boolean aireAcondicionado, boolean wifi, String tipoCamas, double metrosCuadrados, int numCuartos, int numTvs, boolean tvStreamingPremium, boolean jacuzzi, boolean serviciohabitacion) {
        super(codigo, piso, precio, aireAcondicionado, wifi, "Vip"); // Pass "Vip" as tipo
        this.tipoCamas = tipoCamas;
        this.metrosCuadrados = metrosCuadrados;
        this.numCuartos = numCuartos;
        this.numTvs = numTvs;
        this.TvStreamingPremium = tvStreamingPremium;
        this.jacuzzi = jacuzzi;
        this.serviciohabitacion = serviciohabitacion;
    }

    @Override
    public double calcularPrecio(int dias) {
        double precioFinal = super.calcularPrecio(dias); // Base price
        if (this.jacuzzi) {
            precioFinal += 30 * dias; // Additional cost for jacuzzi
        }
        if (this.serviciohabitacion) {
            precioFinal += 50 * dias; // Additional cost for room service
        }
        if (this.numTvs > 0) {
            precioFinal += 15 * this.numTvs * dias; // Additional cost for TVs
            if (this.TvStreamingPremium) {
                precioFinal += 25 * dias; // Additional cost for streaming
            }
        }
        if (this.numCuartos > 0) {
            precioFinal += 50 * this.numCuartos * dias; // Additional cost for extra rooms
        }
        return precioFinal;
    }
}
