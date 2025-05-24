package Models;

import java.util.HashMap;

public class Sistema {
    private HashMap<String, Double> mapaServicios = new HashMap<>();


    public double obtenerPrecioServicio(String servicio) {
        return mapaServicios.getOrDefault(servicio, 0.0);
    }

    public double calcularCostoServicios(HashMap<String, Boolean> serviciosSeleccionados) {
        return serviciosSeleccionados.entrySet().stream()
                .filter(entry -> entry.getValue()) // Only include selected services
                .mapToDouble(entry -> obtenerPrecioServicio(entry.getKey()))
                .sum();
    }

    public HashMap<String, Double> getMapaServicios() {
        return mapaServicios;
    }
}
