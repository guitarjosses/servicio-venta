package movil.pos.venta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import movil.pos.venta.repository.ImpresoraRepository;
import movil.pos.venta.repository.entity.Impresora;

@RestController
@RequestMapping("/api")
public class ImpresoraControlador {

    @Autowired
    ImpresoraRepository impresoraRepositorio;

    @GetMapping("/impresora")
    public ResponseEntity<List<Impresora>> obtenerImpresoras() {

        List<Impresora> impresoras =  new ArrayList<>();
        impresoras = impresoraRepositorio.findAll();
            if (impresoras.isEmpty()) 
                return ResponseEntity.noContent().build();
          
        return  ResponseEntity.ok(impresoras);
    }

    @PostMapping("/impresora")
    public ResponseEntity<Impresora> crearImpresora( @Validated @RequestBody Impresora impresora, BindingResult result) {
        //this.impresora = impresora;

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        Impresora _impresora  = impresoraRepositorio.save(impresora);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(_impresora);
    
    }

    @GetMapping("/impresora/{id}")
    public ResponseEntity<Impresora> obtenerImpresoraPorId(@PathVariable(value = "id") Long impresoraId) {
        

                Impresora impresora = impresoraRepositorio.findById(impresoraId).orElse(null);
                if (  null == impresora) {
                    return  ResponseEntity.notFound().build();
                }
                return  ResponseEntity.ok(impresora);
    }

    @PutMapping("/impresora/{id}")
    public ResponseEntity<Impresora> actualizarImpresora(@PathVariable(value = "id") Long impresoraId,
                                            @Validated @RequestBody Impresora nuevaImpresora) {

        Impresora impresora = impresoraRepositorio.findById(impresoraId).orElse(null);

        if(impresora == null){
            return  ResponseEntity.notFound().build();
        }

                impresora.setModelo(nuevaImpresora.getModelo());
                impresora.setTipo(nuevaImpresora.getTipo());
                impresora.setNombre(nuevaImpresora.getNombre());
                impresora.setIp(nuevaImpresora.getIp());
                impresora.setPuerto(nuevaImpresora.getPuerto());
                impresora.setPaginaCodigo(nuevaImpresora.getPaginaCodigo());
                impresora.setFont(nuevaImpresora.getFont());
                impresora.setMm(nuevaImpresora.getMm());
                impresora.setCaracteres(nuevaImpresora.getCaracteres());
                impresora.setImprimeFactura(nuevaImpresora.isImprimeFactura());
                impresora.setImprimePago(nuevaImpresora.isImprimePago());
                impresora.setActiva(nuevaImpresora.isActiva());


        Impresora impresoraActualizada = impresoraRepositorio.save(impresora);
        return ResponseEntity.ok(impresoraActualizada);
    }

    @DeleteMapping("/impresora/{id}")
    public ResponseEntity<Impresora> borraImpresora(@PathVariable(value = "id") Long impresoraId) {
        Impresora impresora = impresoraRepositorio.findById(impresoraId).orElse(null);

        if(null == impresora){
            return  ResponseEntity.notFound().build();
        }

                impresoraRepositorio.delete(impresora);

        return ResponseEntity.ok(impresora);
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