package movil.pos.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import movil.pos.venta.repository.entity.Sequencia;
import movil.pos.venta.service.SequenciaService;


@RestController
@RequestMapping("/sequencias")
public class SequenciaRest {

    @Autowired
    SequenciaService sequenciaService;

    @GetMapping
    public ResponseEntity<List<Sequencia>> obtenerTodasLasSequencias() {
        List<Sequencia> sequencias =  new ArrayList<>();
        sequencias = sequenciaService.buscarTodasLasSequencias();
            if (sequencias.isEmpty()) 
                return ResponseEntity.noContent().build();
          
        return  ResponseEntity.ok(sequencias);
    }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Sequencia> obtenerSequencia(@PathVariable("id") long id) {
    Sequencia sequencia = sequenciaService.obtenerSequencia(id);
      if (  null == sequencia) {
          return  ResponseEntity.notFound().build();
      }
      return  ResponseEntity.ok(sequencia);
  }    

  @GetMapping(value = "/tipo/{tipo}")
  public ResponseEntity<Sequencia> obtenerSequencia(@PathVariable("tipo") String tipo) {
    Sequencia sequencia = sequenciaService.obtenerSequenciaPorTipoDocumento(tipo);
      if (  null == sequencia) {
          return  ResponseEntity.notFound().build();
      }
      return  ResponseEntity.ok(sequencia);
  }

  @PostMapping
  public ResponseEntity<Sequencia> crearSequencia(@RequestBody Sequencia sequencia, BindingResult result) {
      if (result.hasErrors()){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
      }

      Sequencia sequenciaDB = sequenciaService.crearSequencia(sequencia);

      return  ResponseEntity.status( HttpStatus.CREATED).body(sequenciaDB);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> actualizarSequencia(@PathVariable("id") long id, @RequestBody Sequencia sequencia) {

    Sequencia sequenciaActual = sequenciaService.obtenerSequencia(id);

      if ( null == sequenciaActual ) {       
          return  ResponseEntity.notFound().build();
      }
      sequencia.setId(id);
      sequenciaActual=sequenciaService.actualizarSequencia(sequencia);
      return  ResponseEntity.ok(sequenciaActual);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Sequencia> borrarSequencia(@PathVariable("id") long id) {

    Sequencia sequencia = sequenciaService.obtenerSequencia(id);
      if ( null == sequencia ) {

          return  ResponseEntity.notFound().build();
      }
      sequencia = sequenciaService.borrarSequencia(sequencia);
      return  ResponseEntity.ok(sequencia);
  }

  private String formatMessage( BindingResult result){
    List<Map<String,String>> errors = result.getFieldErrors().stream()
            .map(err ->{
                Map<String,String>  error =  new HashMap<>();
                error.put(err.getField(), err.getDefaultMessage());
                return error;

            }).collect(Collectors.toList());
    ErrorMessage errorMessage = ErrorMessage.builder()
            .code("01")
            .messages(errors).build();
    ObjectMapper mapper = new ObjectMapper();
    String jsonString="";
    try {
        jsonString = mapper.writeValueAsString(errorMessage);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }
    return jsonString;
} 
    
}