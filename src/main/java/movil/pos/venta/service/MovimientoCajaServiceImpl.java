package movil.pos.venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.MovimientoCajaRepository;
import movil.pos.venta.repository.entity.MovimientoCaja;

@Service
public class MovimientoCajaServiceImpl implements MovimientoCajaService {

    @Autowired
    MovimientoCajaRepository movcajaRepository;

    @Override
    public List<MovimientoCaja> buscarTodosLosMovCaja() {
        
        return movcajaRepository.findAll();
    }

    @Override
    public MovimientoCaja crearMovCaja(MovimientoCaja movcaja) {
    
        MovimientoCaja movcajaDB;
        
        movcajaDB = movcajaRepository.save(movcaja);
        return movcajaDB;
    }

    @Override
    public MovimientoCaja actualizarMovCaja(MovimientoCaja movcaja) {
        
        MovimientoCaja movcajaDB = obtenerMovCaja(movcaja.getId());

        if (movcajaDB == null){
            return  null;
        }

        movcajaDB.setCajaId(movcaja.getCajaId());
        movcajaDB.setFecha_movimiento(movcaja.getFecha_movimiento());
        movcajaDB.setTipo_mov(movcaja.getTipo_mov());
        movcajaDB.setMotivo(movcaja.getMotivo());
        movcajaDB.setMonto(movcaja.getMonto());
        movcajaDB.setEsApertura(movcaja.getEsApertura());

        return movcajaRepository.save(movcajaDB);
    }

    @Override
    public MovimientoCaja borrarMovCaja(MovimientoCaja movcaja)
    {
        MovimientoCaja movcajaDB = obtenerMovCaja(movcaja.getId());
        if (movcajaDB ==null){
            return  null;
        }

        return movcajaRepository.save(movcaja);
    }

    @Override
    public MovimientoCaja obtenerMovCaja(Long id) {
    
        return movcajaRepository.findById(id).orElse(null);
    }
}