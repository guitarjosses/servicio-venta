package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.Cotizacion;
import movil.pos.venta.service.CotizacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/cotizaciones")
public class CotizacionRest {

    @Autowired
    CotizacionService cotizacionService;

    @GetMapping("/por/rango/fecha1/{fechaInicial}/fecha2/{fechaFinal}")
    public ResponseEntity<List<Cotizacion>> obtenerCotizacionesPorRangoFecha(@PathVariable(value = "fechaInicial") Timestamp fechaInicial,
    @PathVariable(value = "fechaFinal") Timestamp fechaFinal){

        List<Cotizacion> cotizaciones =  new ArrayList<>();
        List<Cotizacion> cotizaciones1 =  new ArrayList<>();

        cotizaciones = cotizacionService.obtenerCotizacionesPorRangoFecha(fechaInicial, fechaFinal);

        if (cotizaciones.isEmpty()) 
                return ResponseEntity.noContent().build();

                        for(Cotizacion cotizacion:cotizaciones){
            
        cotizaciones1.add(cotizacion);
        }
          
        return  ResponseEntity.ok(cotizaciones1);

    }

    @GetMapping
    public ResponseEntity<List<Cotizacion>> obtenerTodosLasCotizaciones() {
        List<Cotizacion> cotizaciones =  new ArrayList<>();
        List<Cotizacion> cotizaciones1 =  new ArrayList<>();
        cotizaciones = cotizacionService.buscarTodasLasCotizaciones();
            if (cotizaciones.isEmpty()) 
                return ResponseEntity.noContent().build();
        
        for(Cotizacion cotizacion:cotizaciones){
            cotizaciones1.add(cotizacion);
        }
          
        return  ResponseEntity.ok(cotizaciones1);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cotizacion> obtenerCotizacion(@PathVariable("id") long id) {
        Cotizacion cotizacion = cotizacionService.obtenerCotizacion(id);
        if (  null == cotizacion) {

            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(cotizacion);
    }

    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Cotizacion> crearCotizacion(@RequestBody Cotizacion cotizacion, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        Cotizacion cotizacionDB = cotizacionService.crearCotizacion(cotizacion);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(cotizacionDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarCotizacion(@PathVariable("id") long id, @RequestBody Cotizacion cotizacion) {
  
        Cotizacion cotizacionActual = cotizacionService.obtenerCotizacion(id);
  
        if ( null == cotizacionActual ) {

            return  ResponseEntity.notFound().build();

        }
        cotizacion.setId(id);
        cotizacionActual = cotizacionService.actualizarCotizacion(cotizacion);
        return  ResponseEntity.ok(cotizacionActual);
    }

    /*@DeleteMapping(value = "/{id}")
    public ResponseEntity<Cotizacion> borrarRecibo(@PathVariable("id") long id) {
  
        Cotizacion venta = ventaService.obtenerVenta(id);
        if ( null == venta ) {

            return  ResponseEntity.notFound().build();

        }

        venta = ventaService.borrarVenta(venta);
        return  ResponseEntity.ok(venta);
    }*/

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