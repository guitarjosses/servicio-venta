package movil.pos.venta.repository;

import java.sql.Timestamp;
import java.util.List;

import movil.pos.venta.repository.entity.Cliente;
import movil.pos.venta.repository.entity.Cotizacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Long>{

    public Cotizacion findByNumeroCotizacion(String numeroCotizacion);
    public List<Cotizacion> findByCliente(Cliente cliente);
    public List<Cotizacion> findByFechaBetween(Timestamp fechaInicial, Timestamp fechaFinal);
}