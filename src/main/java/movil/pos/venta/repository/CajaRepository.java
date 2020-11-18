package movil.pos.venta.repository;

import java.util.List;

import movil.pos.venta.repository.entity.Caja;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CajaRepository extends JpaRepository<Caja, Long> {
    public Caja findByCodigoCaja(String codigoCaja);
    public List<Caja> findByNombre(String nombre);
}