package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.Caja;
import movil.pos.venta.service.CajaService;

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

@RestController
@RequestMapping("/cajas")
public class CajaRest {

    @Autowired
    CajaService cajaService;

    @GetMapping
    public ResponseEntity<List<Caja>> obtenerTodasLasCajas() {
        List<Caja> cajas =  new ArrayList<>();
        cajas = cajaService.buscarTodasLasCajas();
            if (cajas.isEmpty()) 
                return ResponseEntity.noContent().build();
          
        return  ResponseEntity.ok(cajas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Caja> obtenerCaja(@PathVariable("id") long id) {
        Caja caja = cajaService.obtenerCaja(id);
        if (  null == caja) {

            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(caja);
    }

    @PostMapping
    public ResponseEntity<Caja> crearRecibo(@RequestBody Caja caja, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        Caja cajaDB = cajaService.crearCaja(caja);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(cajaDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarCaja(@PathVariable("id") long id, @RequestBody Caja caja) {
  
        Caja cajaActual = cajaService.obtenerCaja(id);
  
        if ( null == cajaActual ) {

            return  ResponseEntity.notFound().build();

        }
        caja.setId(id);
        cajaActual = cajaService.actualizarCaja(caja);
        return  ResponseEntity.ok(cajaActual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Caja> borrarCaja(@PathVariable("id") long id) {
  
        Caja caja = cajaService.obtenerCaja(id);
        if ( null == caja ) {

            return  ResponseEntity.notFound().build();

        }

        caja = cajaService.borrarCaja(caja);
        return  ResponseEntity.ok(caja);
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