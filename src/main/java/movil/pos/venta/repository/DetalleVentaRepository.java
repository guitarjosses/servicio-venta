package movil.pos.venta.repository;

import movil.pos.venta.repository.entity.DetalleVenta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long>{

    
}