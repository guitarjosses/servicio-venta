package movil.pos.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import movil.pos.venta.repository.entity.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
    
}