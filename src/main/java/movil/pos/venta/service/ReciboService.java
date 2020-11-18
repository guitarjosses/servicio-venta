package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.Recibo;

public interface ReciboService {

    public List<Recibo> buscarTodosLosRecibos();

    public Recibo crearRecibo(Recibo recibo);
    public Recibo actualizarRecibo(Recibo recibo);
    public Recibo borrarRecibo(Recibo recibo);
    public Recibo obtenerRecibo(Long id);
    public List<Recibo> obtenerRecibosPorVentaId(Long ventaId);
    
}