package Models;

import java.util.HashMap;

public class Sistema {
    private HashMap<String, Double> mapaServicios = new HashMap<>();

    public Sistema() {
        inicializarMapaServicios();
    }

    private void inicializarMapaServicios() {
        mapaServicios.put("MiniBar", 20.0);
        mapaServicios.put("Jacuzzi", 50.0);
        mapaServicios.put("TV", 15.0);
    }

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
