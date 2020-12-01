package movil.pos.venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.DetalleCotizacionRepository;
import movil.pos.venta.repository.CotizacionRepository;
import movil.pos.venta.repository.entity.DetalleCotizacion;
import movil.pos.venta.repository.entity.Cotizacion;

@Service
public class DetalleCotizacionImpl implements DetalleCotizacionService {

    @Autowired
    DetalleCotizacionRepository detalleCotizacionRepository;

    @Autowired
    CotizacionRepository cotizacionRepository;

    @Override
    public List<DetalleCotizacion> buscarTodosLosDetalleCotizacion() {
        
        return detalleCotizacionRepository.findAll();

    }

    @Override
    public DetalleCotizacion crearDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        
        DetalleCotizacion detalleCotizacionDB; 

        detalleCotizacionDB = detalleCotizacionRepository.save (detalleCotizacion);
        return detalleCotizacionDB;
    
    }

    @Override
    public DetalleCotizacion actualizarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        
        DetalleCotizacion detalleCotizacionDB = obtenerDetalleCotizacion(detalleCotizacion.getId());
        if (detalleCotizacionDB == null){
            return  null;
        }

        detalleCotizacionDB.setCotizacionId(detalleCotizacion.getCotizacionId());
        detalleCotizacionDB.setProductoId(detalleCotizacion.getProductoId());
        detalleCotizacionDB.setPrecio(detalleCotizacion.getPrecio());
        detalleCotizacionDB.setCantidad(detalleCotizacion.getCantidad());
        detalleCotizacionDB.setSubtotal(detalleCotizacion.getSubtotal());
        detalleCotizacionDB.setImpuesto(detalleCotizacion.getImpuesto());
        detalleCotizacionDB.setDescuento(detalleCotizacion.getDescuento());
        detalleCotizacionDB.setTotal(detalleCotizacion.getTotal());

        return  detalleCotizacionRepository.save(detalleCotizacionDB);

    }

    @Override
    public DetalleCotizacion borrarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {

        DetalleCotizacion cotizacionDB = obtenerDetalleCotizacion(detalleCotizacion.getId());
        if (cotizacionDB ==null){
            return  null;
        }

        return detalleCotizacionRepository.save(detalleCotizacion);
    }

    @Override
    public DetalleCotizacion obtenerDetalleCotizacion(Long id) {
    
        return  detalleCotizacionRepository.findById(id).orElse(null);

    }

    @Override
    public List<DetalleCotizacion> obtenerDetalleCotizacionByCotizacionId(Long idCotizacion) {
        
        Cotizacion cotizacion = cotizacionRepository.findById(idCotizacion).orElse(null);

        if(cotizacion == null){
            return null;
        }

        return detalleCotizacionRepository.findByCotizacionId(idCotizacion);
        
    }
    
}