package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.Venta;

public interface VentaService {

    public List<Venta> buscarTodasLasVentas();

    public Venta crearVenta(Venta venta);
    public Venta actualizarVenta(Venta venta);
    public Venta borrarVenta(Venta venta);
    public Venta obtenerVenta(Long id);
    
}