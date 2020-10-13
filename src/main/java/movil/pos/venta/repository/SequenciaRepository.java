package movil.pos.venta.repository;

import movil.pos.venta.repository.entity.Sequencia;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SequenciaRepository extends JpaRepository<Sequencia, Long>{

    public Sequencia findByTipoDocumento(String tipoDocumento);
}