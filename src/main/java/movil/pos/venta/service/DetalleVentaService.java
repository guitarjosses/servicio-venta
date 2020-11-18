package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.DetalleVenta;

public interface DetalleVentaService {

    public List<DetalleVenta> buscarTodasLosDetalleVenta();

    public DetalleVenta crearDetalleVenta(DetalleVenta detalleVenta);
    public DetalleVenta actualizarDetalleVenta(DetalleVenta detalleVenta);
    public DetalleVenta borrarDetalleVenta(DetalleVenta detalleVenta);
    public DetalleVenta obtenerDetalleVenta(Long id);
    public List<DetalleVenta> obtenerDetalleVentaByVentaId(Long idVenta);
    
}