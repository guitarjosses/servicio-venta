package movil.pos.venta.repository;

import movil.pos.venta.repository.entity.DetalleCotizacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCotizacionRepository extends JpaRepository<DetalleCotizacion, Long>{

    public List<DetalleCotizacion> findByCotizacionId(Long cotizacionId);
}