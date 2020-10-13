package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.Recibo;
import movil.pos.venta.service.ReciboService;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/recibos")
public class ReciboRest {

    @Autowired
    ReciboService reciboService;

    @GetMapping
    public ResponseEntity<List<Recibo>> obtenerTodosLosRecibos() {
        List<Recibo> recibos =  new ArrayList<>();
        recibos = reciboService.buscarTodosLosRecibos();
            if (recibos.isEmpty()) 
                return ResponseEntity.noContent().build();
          
        return  ResponseEntity.ok(recibos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Recibo> obtenerRecibo(@PathVariable("id") long id) {
        Recibo recibo = reciboService.obtenerRecibo(id);
        if (  null == recibo) {

            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(recibo);
    }

    @PostMapping
    public ResponseEntity<Recibo> crearRecibo(@RequestBody Recibo recibo, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        Recibo reciboDB = reciboService.crearRecibo(recibo);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(reciboDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarRecibo(@PathVariable("id") long id, @RequestBody Recibo recibo) {
  
        Recibo reciboActual = reciboService.obtenerRecibo(id);
  
        if ( null == reciboActual ) {

            return  ResponseEntity.notFound().build();

        }
        recibo.setId(id);
        reciboActual = reciboService.actualizarRecibo(recibo);
        return  ResponseEntity.ok(reciboActual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Recibo> borrarRecibo(@PathVariable("id") long id) {
  
        Recibo recibo = reciboService.obtenerRecibo(id);
        if ( null == recibo ) {

            return  ResponseEntity.notFound().build();

        }

        recibo = reciboService.borrarRecibo(recibo);
        return  ResponseEntity.ok(recibo);
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