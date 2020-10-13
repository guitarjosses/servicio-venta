package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.Funcionario;

public interface FuncionarioService {

    public List<Funcionario> buscarTodosLosFuncionarios();

    public Funcionario crearFuncionario(Funcionario funcionario);
    public Funcionario actualizarFuncionario(Funcionario funcionario);
    public Funcionario borrarFuncionario(Funcionario funcionario);
    public Funcionario obtenerFuncionario(Long id);
    
}