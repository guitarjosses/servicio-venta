package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.entity.Sequencia;

public interface SequenciaService {

    public List<Sequencia> buscarTodasLasSequencias();
    
    public Sequencia crearSequencia(Sequencia secuencia);
    public Sequencia actualizarSequencia(Sequencia secuencia);
    public Sequencia borrarSequencia(Sequencia secuencia);
    public Sequencia obtenerSequencia(Long id);
    public Sequencia obtenerSequenciaPorTipoDocumento(String tipoDocumento);

}