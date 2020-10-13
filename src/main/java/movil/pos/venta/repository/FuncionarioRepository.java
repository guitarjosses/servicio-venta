package movil.pos.venta.repository;

import java.util.List;

import movil.pos.venta.repository.entity.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

    public Funcionario findByCi(String ci);
    public List<Funcionario> findByNombre(String nombre);
    
}