package movil.pos.venta.repository;

import java.util.List;

import movil.pos.venta.repository.entity.Sucursal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
   public List<Sucursal> findByNombreSucursal(String nombreSucursal);
}