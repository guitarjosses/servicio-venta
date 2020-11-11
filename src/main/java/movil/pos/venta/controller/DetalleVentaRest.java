package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.DetalleVenta;
import movil.pos.venta.service.DetalleVentaService;

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
@RequestMapping("/detalle/ventas")

public class DetalleVentaRest {

    @Autowired
    DetalleVentaService detalleVentaService;

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> obtenerTodosLosDetallesVenta() {
        List<DetalleVenta> detallesVenta =  new ArrayList<>();
        detallesVenta = detalleVentaService.buscarTodasLosDetalleVenta();
            if (detallesVenta.isEmpty()) 
                return ResponseEntity.noContent().build();

        return  ResponseEntity.ok(detallesVenta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DetalleVenta> obtenerDetalleVenta(@PathVariable("id") long id) {
        DetalleVenta detallesVenta = detalleVentaService.obtenerDetalleVenta(id);
        if (  null == detallesVenta) {

            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(detallesVenta);
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> crearDetalleVenta(@RequestBody DetalleVenta detalleVenta, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        DetalleVenta detalleVentaDB = detalleVentaService.crearDetalleVenta(detalleVenta);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(detalleVentaDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarDetalleVenta(@PathVariable("id") long id, @RequestBody DetalleVenta detalleVenta) {
  
        DetalleVenta detalleVentaActual = detalleVentaService.obtenerDetalleVenta(id);
  
        if ( null == detalleVentaActual ) {

            return  ResponseEntity.notFound().build();

        }
        detalleVenta.setId(id);
        detalleVentaActual = detalleVentaService.actualizarDetalleVenta(detalleVenta);
        return  ResponseEntity.ok(detalleVentaActual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DetalleVenta> borrarDetalleVenta(@PathVariable("id") long id) {
  
        DetalleVenta detalleVenta = detalleVentaService.obtenerDetalleVenta(id);
        if ( null == detalleVenta ) {

            return  ResponseEntity.notFound().build();

        }

        detalleVenta = detalleVentaService.borrarDetalleVenta(detalleVenta);
        return  ResponseEntity.ok(detalleVenta);
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