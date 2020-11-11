package movil.pos.venta.repository;

import java.util.List;

import movil.pos.venta.repository.entity.Venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

    public Venta findByNumeroVenta(String numeroVenta);
    public List<Venta> findByClienteId(Long clienteId);
    
}