package movil.pos.venta.service;

import java.sql.Timestamp;
import java.util.List;

import movil.pos.venta.repository.entity.Cotizacion;

public interface CotizacionService {
    
    public List<Cotizacion> buscarTodasLasCotizaciones();

    public Cotizacion crearCotizacion(Cotizacion cotizacion);
    public Cotizacion actualizarCotizacion(Cotizacion cotizacion);
    public Cotizacion borrarCotizacion(Cotizacion cotizacion);
    public Cotizacion obtenerCotizacion(Long id);

    public List<Cotizacion> obtenerCotizacionesPorRangoFecha(Timestamp fechaInicial, Timestamp fechaFinal);
    
}
