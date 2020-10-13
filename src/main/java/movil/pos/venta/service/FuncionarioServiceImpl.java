package movil.pos.venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.FuncionarioRepository;
import movil.pos.venta.repository.entity.Funcionario;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public List<Funcionario> buscarTodosLosFuncionarios() {
        
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario crearFuncionario(Funcionario funcionario) {

        Funcionario funcionarioDB = funcionarioRepository.findByCi(funcionario.getCi());
        if (funcionarioDB != null){
            return  funcionarioDB;
        }

        funcionarioDB = funcionarioRepository.save (funcionario);
        return funcionarioDB;

    }

    @Override
    public Funcionario actualizarFuncionario(Funcionario funcionario) {

        Funcionario funcionarioDB = obtenerFuncionario(funcionario.getId());
        if (funcionarioDB == null){
            return  null;
        }

        funcionarioDB.setNombre(funcionario.getNombre());
        funcionarioDB.setUsuario(funcionario.getUsuario());
        funcionarioDB.setContrasenia(funcionario.getContrasenia());
        funcionarioDB.setCi(funcionario.getCi());
        funcionarioDB.setCategoriaId(funcionario.getCategoriaId());
        funcionarioDB.setActivo(funcionario.isActivo());
        funcionarioDB.setCiudad(funcionario.getCiudad());
        funcionarioDB.setTelefono(funcionario.getTelefono());
        funcionarioDB.setMovil(funcionario.getMovil());
        funcionarioDB.setCorreoElectronico(funcionario.getCorreoElectronico());
        funcionarioDB.setDireccion(funcionario.getDireccion());
        funcionarioDB.setEfectuarVenta(funcionario.isEfectuarVenta());
        funcionarioDB.setAdministrarProducto(funcionario.isAdministrarProducto());
        funcionarioDB.setReponerStock(funcionario.isReponerStock());
        funcionarioDB.setAdministrarCliente(funcionario.isAdministrarCliente());
        funcionarioDB.setReciboCliente(funcionario.isReciboCliente());
        funcionarioDB.setDescuentoPorItem(funcionario.isDescuentoPorItem());
        funcionarioDB.setDescuentoTotalVenta(funcionario.isDescuentoTotalVenta());
        funcionarioDB.setCambiarTipoPrecioVenta(funcionario.isCambiarTipoPrecioVenta());


        return  funcionarioRepository.save(funcionarioDB);

    }

    @Override
    public Funcionario borrarFuncionario(Funcionario funcionario) {

        Funcionario funcionarioDB = obtenerFuncionario(funcionario.getId());
        if (funcionarioDB ==null){
            return  null;
        }

        return funcionarioRepository.save(funcionario);

    }

    @Override
    public Funcionario obtenerFuncionario(Long id) {

        return  funcionarioRepository.findById(id).orElse(null);

    }
    
}