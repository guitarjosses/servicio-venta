package movil.pos.venta.service;

import java.util.List;

import movil.pos.venta.repository.SequenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movil.pos.venta.repository.entity.Sequencia;

@Service
public class SequenciaServiceImpl implements SequenciaService {

    @Autowired
    SequenciaRepository sequenciaRepository;

    @Override
    public List<Sequencia> buscarTodasLasSequencias() {

        return sequenciaRepository.findAll();
    }

    @Override
    public Sequencia crearSequencia(Sequencia sequencia) {

        Sequencia sequenciaDB = sequenciaRepository.findByTipoDocumento( sequencia.getTipoDocumento() );
        if (sequenciaDB != null){
            return  sequenciaDB;
        }

        sequenciaDB = sequenciaRepository.save ( sequencia );
        return sequenciaDB;

    }

    @Override
    public Sequencia actualizarSequencia(Sequencia sequencia) {

        Sequencia sequenciaDB = obtenerSequencia(sequencia.getId());
        if (sequenciaDB == null){
            return  null;
        }

        //sequenciaDB.setTipoDocumento(sequencia.getTipoDocumento());
        sequenciaDB.setValor(sequencia.getValor());
        //sequenciaDB.setPrefijo(sequencia.getPrefijo());
        //sequenciaDB.setActivo(sequencia.isActivo());


        return  sequenciaRepository.save(sequenciaDB);

    }

    @Override
    public Sequencia borrarSequencia(Sequencia sequencia) {

        Sequencia sequenciaDB = obtenerSequencia(sequencia.getId());
        if (sequenciaDB ==null){
            return  null;
        }

        return sequenciaRepository.save(sequencia);

    }

    @Override
    public Sequencia obtenerSequencia(Long id) {

        return  sequenciaRepository.findById(id).orElse(null);

    }

    @Override
    public Sequencia obtenerSequenciaPorTipoDocumento(String tipoDocumento) {
        
        return sequenciaRepository.findByTipoDocumento(tipoDocumento);
    }
   
}