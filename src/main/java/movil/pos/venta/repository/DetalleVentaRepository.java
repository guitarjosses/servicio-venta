package movil.pos.venta.repository;

import movil.pos.venta.repository.entity.DetalleVenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long>{

    public List<DetalleVenta> findByVentaId(Long ventaId);

    
}