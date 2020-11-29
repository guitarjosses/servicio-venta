package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.Sucursal;

public interface SucursalService {

    public List<Sucursal> buscarTodasLasSucursales();

    public Sucursal crearSucursal(Sucursal caja);
    public Sucursal actualizarSucursal(Sucursal caja);
    public Sucursal borrarSucursal(Sucursal caja);
    public Sucursal obtenerSucursal(Long id);
    
}