package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.Caja;

public interface CajaService {

    public List<Caja> buscarTodasLasCajas();

    public Caja crearCaja(Caja caja);
    public Caja actualizarCaja(Caja caja);
    public Caja borrarCaja(Caja caja);
    public Caja obtenerCaja(Long id);
    
}