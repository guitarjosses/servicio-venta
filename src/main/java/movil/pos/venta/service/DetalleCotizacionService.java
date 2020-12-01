package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.DetalleCotizacion;


public interface DetalleCotizacionService
{
    public List<DetalleCotizacion> buscarTodosLosDetalleCotizacion();

    public DetalleCotizacion crearDetalleCotizacion(DetalleCotizacion detalleCotizacion);
    public DetalleCotizacion actualizarDetalleCotizacion(DetalleCotizacion detalleCotizacion);
    public DetalleCotizacion borrarDetalleCotizacion(DetalleCotizacion detalleCotizacion);
    public DetalleCotizacion obtenerDetalleCotizacion(Long id);
    public List<DetalleCotizacion> obtenerDetalleCotizacionByCotizacionId(Long idCotizacion);
}
