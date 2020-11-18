package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.MovimientoCaja;
import movil.pos.venta.service.MovimientoCajaService;

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
@RequestMapping("/movimientos_caja")
public class MovimientoCajaRest
{
    
    @Autowired
    MovimientoCajaService movcajaService;

    @GetMapping
    public ResponseEntity<List<MovimientoCaja>> obtenerTodosLosMovCaja() {
        List<MovimientoCaja> movcaja =  new ArrayList<>();
        movcaja = movcajaService.buscarTodosLosMovCaja();
            if (movcaja.isEmpty()) 
                return ResponseEntity.noContent().build();
          
        return  ResponseEntity.ok(movcaja);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovimientoCaja> obtenerMovCaja(@PathVariable("id") long id) {
        MovimientoCaja movcaja = movcajaService.obtenerMovCaja(id);
        if (  null == movcaja) {

            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(movcaja);
    }

    @PostMapping
    public ResponseEntity<MovimientoCaja> crearMovCaja(@RequestBody MovimientoCaja movcaja, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        MovimientoCaja movcajaDB = movcajaService.crearMovCaja(movcaja);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(movcajaDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarMovCaja(@PathVariable("id") long id, @RequestBody MovimientoCaja movcaja) {
  
        MovimientoCaja movcajaActual = movcajaService.obtenerMovCaja(id);
  
        if ( null == movcajaActual ) {

            return  ResponseEntity.notFound().build();

        }
        movcaja.setId(id);
        movcajaActual = movcajaService.actualizarMovCaja(movcaja);
        return ResponseEntity.ok(movcajaActual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MovimientoCaja> borrarMovCaja(@PathVariable("id") long id) {
  
        MovimientoCaja movcaja = movcajaService.obtenerMovCaja(id);
        if ( null == movcaja ) {

            return ResponseEntity.notFound().build();

        }

        movcaja = movcajaService.borrarMovCaja(movcaja);
        return ResponseEntity.ok(movcaja);
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
