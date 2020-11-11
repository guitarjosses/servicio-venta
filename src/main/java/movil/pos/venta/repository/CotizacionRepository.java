package movil.pos.venta.repository;

import java.util.List;

import movil.pos.venta.repository.entity.Cotizacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Long>{

    public Cotizacion findByNumeroVenta(String numeroVenta);
    public List<Cotizacion> findByClienteId(Long clienteId);
    
}