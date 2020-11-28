package movil.pos.venta.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.CotizacionRepository;
import movil.pos.venta.repository.entity.Cotizacion;

@Service
public class CotizacionServiceImpl implements CotizacionService  {
    
    @Autowired
    CotizacionRepository cotizacionRepository;

    @Override
    public List<Cotizacion> buscarTodasLasCotizaciones() {

        return cotizacionRepository.findAll();
    }

    @Override
    public Cotizacion crearCotizacion(Cotizacion cotizacion) {

        Cotizacion cotizacionDB = cotizacionRepository.findByNumeroCotizacion(cotizacion.getNumeroCotizacion());
        if (cotizacionDB != null) {
            return cotizacionDB;
        }

        cotizacionDB = cotizacionRepository.save(cotizacion);
        return cotizacionDB;
    }

    @Override
    public Cotizacion actualizarCotizacion(Cotizacion cotizacion) {

        Cotizacion cotizacionDB = obtenerCotizacion(cotizacion.getId());
        if (cotizacionDB == null) {
            return null;
        }

        cotizacionDB.setNumeroCotizacion(cotizacion.getNumeroCotizacion());
        cotizacionDB.setFecha(cotizacion.getFecha());
        cotizacionDB.setCliente(cotizacion.getCliente());
        cotizacionDB.setFuncionario(cotizacion.getFuncionario());
        cotizacionDB.setTipoDocumento(cotizacion.getTipoDocumento());
        cotizacionDB.setTipoPago(cotizacion.getTipoPago());
        cotizacionDB.setSemanaDiaPagoId(cotizacion.getSemanaDiaPagoId());
        cotizacionDB.setQuincenaDia1PagoId(cotizacion.getQuincenaDia1PagoId());
        cotizacionDB.setQuincenaDia2PagoId(cotizacion.getQuincenaDia2PagoId());
        cotizacionDB.setMesDiaPago(cotizacion.getMesDiaPago());
        cotizacionDB.setSubtotal(cotizacion.getSubtotal());
        cotizacionDB.setImpuesto(cotizacion.getImpuesto());
        cotizacionDB.setDescuento(cotizacion.getDescuento());
        cotizacionDB.setTotal(cotizacion.getTotal());
        cotizacionDB.setActiva(cotizacion.isActiva());

        return cotizacionRepository.save(cotizacionDB);

    }

    @Override
    public Cotizacion borrarCotizacion(Cotizacion cotizacion) {

        Cotizacion cotizacionDB = obtenerCotizacion(cotizacion.getId());
        if (cotizacionDB == null) {
            return null;
        }

        return cotizacionRepository.save(cotizacion);

    }

    @Override
    public Cotizacion obtenerCotizacion(Long id) {

        return cotizacionRepository.findById(id).orElse(null);

    }

    @Override
    public List<Cotizacion> obtenerCotizacionesPorRangoFecha(Timestamp fechaInicial, Timestamp fechaFinal) {
        
        return cotizacionRepository.findByFechaBetween(fechaInicial, fechaFinal);
    }



}
