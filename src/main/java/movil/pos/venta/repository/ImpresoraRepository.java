package movil.pos.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import movil.pos.venta.repository.entity.Impresora;

public interface ImpresoraRepository extends JpaRepository<Impresora, Long>{

    public Impresora findByNombre(String nombre);
    
}