package movil.pos.venta.repository;

import java.util.List;

import movil.pos.venta.repository.entity.Recibo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReciboRepository extends JpaRepository<Recibo, Long>{

    
    public Recibo findByNumeroRecibo(String numeroRecibo);
    public List<Recibo> findByClienteId(Long clienteId);
    
}