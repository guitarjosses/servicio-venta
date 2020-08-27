package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.ClienteRepository;
import movil.pos.venta.repository.entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findCustomerAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente createCustomer(Cliente cliente) {
        Cliente clienteDB = clienteRepository.findByRucCi( cliente.getRucCi() );
        if (clienteDB != null){
            return  clienteDB;
        }

        clienteDB = clienteRepository.save ( cliente );
        return clienteDB;
    }

    @Override
    public Cliente updateCustomer(Cliente cliente) {
        Cliente clienteDB = getCustomer(cliente.getId());
        if (clienteDB == null){
            return  null;
        }
        clienteDB.setRucCi(cliente.getRucCi());
        clienteDB.setNombre(cliente.getNombre());
        clienteDB.setDireccion(cliente.getDireccion());
        clienteDB.setTelefono(cliente.getTelefono());
        clienteDB.setMovil(cliente.getMovil());
        clienteDB.setCorreoElectronico(cliente.getCorreoElectronico());

        return  clienteRepository.save(clienteDB);
    }

    @Override
    public Cliente deleteCustomer(Cliente cliente) {
        Cliente clienteDB = getCustomer(cliente.getId());
        if (clienteDB ==null){
            return  null;
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getCustomer(Long id) {
        return  clienteRepository.findById(id).orElse(null);
    }
    
}