package movil.pos.venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.DetalleVentaRepository;
import movil.pos.venta.repository.VentaRepository;
import movil.pos.venta.repository.entity.DetalleVenta;
import movil.pos.venta.repository.entity.Venta;

@Service
public class DetalleVentaImpl implements DetalleVentaService {

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @Autowired
    VentaRepository ventaRepository;

    @Override
    public List<DetalleVenta> buscarTodasLosDetalleVenta() {
        
        return detalleVentaRepository.findAll();

    }

    @Override
    public DetalleVenta crearDetalleVenta(DetalleVenta detalleVenta) {
        
        DetalleVenta detalleVentaDB; 

        detalleVentaDB = detalleVentaRepository.save (detalleVenta);
        return detalleVentaDB;
    
    }

    @Override
    public DetalleVenta actualizarDetalleVenta(DetalleVenta detalleVenta) {
        
        DetalleVenta detalleVentaDB = obtenerDetalleVenta(detalleVenta.getId());
        if (detalleVentaDB == null){
            return  null;
        }

        detalleVentaDB.setVentaId(detalleVenta.getVentaId());
        detalleVentaDB.setProductoId(detalleVenta.getProductoId());
        detalleVentaDB.setPrecio(detalleVenta.getPrecio());
        detalleVentaDB.setCantidad(detalleVenta.getCantidad());
        detalleVentaDB.setSubtotal(detalleVenta.getSubtotal());
        detalleVentaDB.setImpuesto(detalleVenta.getImpuesto());
        detalleVentaDB.setDescuento(detalleVenta.getDescuento());
        detalleVentaDB.setTotal(detalleVenta.getTotal());

        return  detalleVentaRepository.save(detalleVentaDB);

    }

    @Override
    public DetalleVenta borrarDetalleVenta(DetalleVenta detalleVenta) {

        DetalleVenta ventaDB = obtenerDetalleVenta(detalleVenta.getId());
        if (ventaDB ==null){
            return  null;
        }

        return detalleVentaRepository.save(detalleVenta);

    }

    @Override
    public DetalleVenta obtenerDetalleVenta(Long id) {
    
        return  detalleVentaRepository.findById(id).orElse(null);

    }

    @Override
    public List<DetalleVenta> obtenerDetalleVentaByVentaId(Long idVenta) {
        
        Venta venta = ventaRepository.findById(idVenta).orElse(null);

        if(venta == null){
            return null;
        }

        return detalleVentaRepository.findByVentaId(idVenta);
        
    }
    
}