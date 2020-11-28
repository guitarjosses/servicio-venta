package movil.pos.venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.CajaRepository;
import movil.pos.venta.repository.entity.Caja;

@Service
public class CajaServiceImpl implements CajaService {

    @Autowired
    CajaRepository cajaRepository;

    @Override
    public List<Caja> buscarTodasLasCajas() {
        
        return cajaRepository.findAll();
    }

    @Override
    public Caja crearCaja(Caja caja) {
    
        caja = cajaRepository.save (caja);
        return caja;

    }

    @Override
    public Caja actualizarCaja(Caja caja) {

        Caja cajaDB = obtenerCaja(caja.getId());
        if (cajaDB == null){
            return  null;
        }

        cajaDB.setNombreCaja(caja.getNombreCaja());

        return  cajaRepository.save(cajaDB);
    }

    @Override
    public Caja borrarCaja(Caja caja) {

        Caja cajaDB = obtenerCaja(caja.getId());
        if (cajaDB == null){
            return  null;
        }

        return  cajaRepository.save(cajaDB);
    }

    @Override
    public Caja obtenerCaja(Long id) {
        
        return  cajaRepository.findById(id).orElse(null);
    }
    
}