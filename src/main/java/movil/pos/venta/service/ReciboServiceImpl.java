package movil.pos.venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import movil.pos.venta.repository.ReciboRepository;
import movil.pos.venta.repository.entity.Recibo;

@Service
public class ReciboServiceImpl implements ReciboService {

    @Autowired
    ReciboRepository reciboRepository;

    @Override
    public List<Recibo> buscarTodosLosRecibos() {
        
        return reciboRepository.findAll();
    }

    @Override
    public Recibo crearRecibo(Recibo recibo) {
    
        Recibo reciboDB = reciboRepository.findByNumeroRecibo(recibo.getNumeroRecibo());
        if (reciboDB != null){
            return  reciboDB;
        }

        reciboDB = reciboRepository.save (recibo);
        return reciboDB;

    }

    @Override
    public Recibo actualizarRecibo(Recibo recibo) {

        Recibo reciboDB = obtenerRecibo(recibo.getId());
        if (reciboDB == null){
            return  null;
        }

reciboDB.setNumeroRecibo(recibo.getNumeroRecibo());
reciboDB.setVenta(recibo.getVenta());
reciboDB.setFecha(recibo.getFecha());
reciboDB.setCliente(recibo.getCliente());
reciboDB.setMetodoPago(recibo.getMetodoPago());
reciboDB.setMontoEfectivo(recibo.getMontoEfectivo());
reciboDB.setMontoTarjeta(recibo.getMontoTarjeta());
reciboDB.setMontoCheque(recibo.getMontoCheque());
reciboDB.setMontoTransferencia(recibo.getMontoTransferencia());
reciboDB.setReferenciaTarjeta(recibo.getReferenciaTarjeta());
reciboDB.setNumeroCheque(recibo.getNumeroCheque());
reciboDB.setReferenciaTransferencia(recibo.getReferenciaTransferencia());
reciboDB.setSubtotal(recibo.getSubtotal());
reciboDB.setImpuesto(recibo.getImpuesto());
reciboDB.setDescuento(recibo.getDescuento());
reciboDB.setTotal(recibo.getTotal());
reciboDB.setCambio(recibo.getCambio());
reciboDB.setActivo(recibo.isActivo());

        return  reciboRepository.save(reciboDB);
    }

    @Override
    public Recibo borrarRecibo(Recibo recibo) {

        Recibo reciboDB = obtenerRecibo(recibo.getId());
        if (reciboDB == null){
            return  null;
        }

        return  reciboRepository.save(reciboDB);
    }

    @Override
    public Recibo obtenerRecibo(Long id) {
        
        return  reciboRepository.findById(id).orElse(null);
    }

    @Override
    public List<Recibo> obtenerRecibosPorVentaId(Long ventaId) {
        
        return reciboRepository.findByVentaId(ventaId);
    }
    
}