package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.Cliente;

public interface ClienteService {

    public List<Cliente> findCustomerAll();

    public Cliente createCustomer(Cliente customer);
    public Cliente updateCustomer(Cliente customer);
    public Cliente deleteCustomer(Cliente customer);
    public Cliente getCustomer(Long id);



}