package movil.pos.venta.service;

import java.sql.Timestamp;
import java.util.List;

import movil.pos.venta.repository.entity.Venta;

public interface VentaService {

    public List<Venta> buscarTodasLasVentas();

    public Venta crearVenta(Venta venta);
    public Venta actualizarVenta(Venta venta);
    public Venta borrarVenta(Venta venta);
    public Venta obtenerVenta(Long id);

    public List<Venta> obtenerVentasPorRangoFecha(Timestamp fechaInicial, Timestamp fechaFinal);
    
}