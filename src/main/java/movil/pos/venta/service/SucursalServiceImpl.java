package movil.pos.venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.SucursalRepository;
import movil.pos.venta.repository.entity.Sucursal;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> buscarTodasLasSucursales() {
        
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal crearSucursal(Sucursal sucursal) {
    
        sucursal = sucursalRepository.save (sucursal);
        return sucursal;

    }

    @Override
    public Sucursal actualizarSucursal(Sucursal sucursal) {

        Sucursal sucursalDB = obtenerSucursal(sucursal.getId());
        if (sucursalDB == null){
            return  null;
        }

        sucursalDB.setNombreSucursal(sucursal.getNombreSucursal());

        return  sucursalRepository.save(sucursalDB);
    }

    @Override
    public Sucursal borrarSucursal(Sucursal sucursal) {

        Sucursal sucursalDB = obtenerSucursal(sucursal.getId());
        if (sucursalDB == null){
            return  null;
        }

        return  sucursalRepository.save(sucursalDB);
    }

    @Override
    public Sucursal obtenerSucursal(Long id) {
        
        return sucursalRepository.findById(id).orElse(null);
    }
}