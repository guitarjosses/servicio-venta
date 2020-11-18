package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.MovimientoCaja;

public interface MovimientoCajaService {

    public List<MovimientoCaja> buscarTodosLosMovCaja();

    public MovimientoCaja crearMovCaja(MovimientoCaja movcaja);
    public MovimientoCaja actualizarMovCaja(MovimientoCaja movcaja);
    public MovimientoCaja borrarMovCaja(MovimientoCaja movcaja);
    public MovimientoCaja obtenerMovCaja(Long id);
    
}