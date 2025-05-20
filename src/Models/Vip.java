package Models;

public class Vip extends Habitacion{

    private String tipoCamas;
    private double metrosCuadrados;
    private int numCuartos;
    private int numTvs;
    private boolean TvStreamingPremium;
    private boolean jacuzzi;
    private boolean serviciohabitacion;

    public Vip(String codigo, int piso, double precio, boolean disponible, boolean aireAcondicionado, boolean wifi, String tipoCamas, double metrosCuadrados, int numCuartos, int numTvs, boolean tvStreamingPremium, boolean jacuzzi, boolean serviciohabitacion) {
        super(codigo, piso, precio, disponible, aireAcondicionado, wifi);
        this.tipoCamas = tipoCamas;
        this.metrosCuadrados = metrosCuadrados;
        this.numCuartos = numCuartos;
        this.numTvs = numTvs;
        TvStreamingPremium = tvStreamingPremium;
        this.jacuzzi = jacuzzi;
        this.serviciohabitacion = serviciohabitacion;
    }

    public Vip() {
    }

    public String getTipoCamas() {
        return tipoCamas;
    }

    public void setTipoCamas(String tipoCamas) {
        this.tipoCamas = tipoCamas;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getNumCuartos() {
        return numCuartos;
    }

    public void setNumCuartos(int numCuartos) {
        this.numCuartos = numCuartos;
    }

    public int getNumTvs() {
        return numTvs;
    }

    public void setNumTvs(int numTvs) {
        this.numTvs = numTvs;
    }

    public boolean isTvStreamingPremium() {
        return TvStreamingPremium;
    }

    public void setTvStreamingPremium(boolean tvStreamingPremium) {
        TvStreamingPremium = tvStreamingPremium;
    }

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public boolean isServiciohabitacion() {
        return serviciohabitacion;
    }

    public void setServiciohabitacion(boolean serviciohabitacion) {
        this.serviciohabitacion = serviciohabitacion;
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
        if (this.jacuzzi) {
            precioFinal += 30 * dias; // Costo adicional por jacuzzi
        }
        if (this.serviciohabitacion) {
            precioFinal += 50 * dias; // Costo adicional por servicio de mayordomo
        }

        if (this.numTvs > 0) {
            precioFinal += 15 * (this.numTvs) * dias; // Costo adicional por cada TV extra
            if (this.TvStreamingPremium) {
                precioFinal += 25 * dias; // Costo adicional por TV Streaming Premium
            }
        }
        if (this.numCuartos > 0) {
            precioFinal += 50 * (this.numCuartos) * dias; // Costo adicional por cada cuarto extra
        }

        return precioFinal;
    }
}
