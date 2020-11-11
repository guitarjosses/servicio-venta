package movil.pos.venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.VentaRepository;
import movil.pos.venta.repository.entity.Venta;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    VentaRepository ventaRepository;
    
    @Override
    public List<Venta> buscarTodasLasVentas() {
        
        return ventaRepository.findAll();
    }

    @Override
    public Venta crearVenta(Venta venta) {
        
        Venta ventaDB = ventaRepository.findByNumeroVenta(venta.getNumeroVenta());
        if (ventaDB != null){
            return  ventaDB;
        }

        ventaDB = ventaRepository.save (venta);
        return ventaDB;
    }

    @Override
    public Venta actualizarVenta(Venta venta) {
        
        Venta ventaDB = obtenerVenta(venta.getId());
        if (ventaDB == null){
            return  null;
        }

        ventaDB.setNumeroVenta(venta.getNumeroVenta());
        ventaDB.setFecha(venta.getFecha());
        ventaDB.setCliente(venta.getCliente());
        ventaDB.setFuncionario(venta.getFuncionario());
        ventaDB.setTipoDocumento(venta.getTipoDocumento());
        ventaDB.setTipoPago(venta.getTipoPago());
        ventaDB.setSemanaDiaPagoId(venta.getSemanaDiaPagoId());
        ventaDB.setQuincenaDia1PagoId(venta.getQuincenaDia1PagoId());
        ventaDB.setQuincenaDia2PagoId(venta.getQuincenaDia2PagoId());
        ventaDB.setMesDiaPago(venta.getMesDiaPago());
        ventaDB.setSubtotal(venta.getSubtotal());
        ventaDB.setImpuesto(venta.getImpuesto());
        ventaDB.setDescuento(venta.getDescuento());
        ventaDB.setTotal(venta.getTotal());
        ventaDB.setActiva(venta.isActiva());

        return  ventaRepository.save(ventaDB);

    }

    @Override
    public Venta borrarVenta(Venta venta) {

        Venta ventaDB = obtenerVenta(venta.getId());
        if (ventaDB ==null){
            return  null;
        }

        return ventaRepository.save(venta);

    }

    @Override
    public Venta obtenerVenta(Long id) {
        
        return  ventaRepository.findById(id).orElse(null);

    }
    
}