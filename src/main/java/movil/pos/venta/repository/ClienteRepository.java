package movil.pos.venta.repository;

import java.util.List;

import movil.pos.venta.repository.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByRucCi(String rucci);
    public List<Cliente> findByNombre(String nombre);

}