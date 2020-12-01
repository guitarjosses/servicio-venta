package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.DetalleCotizacion;
import movil.pos.venta.service.DetalleCotizacionService;

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
@RequestMapping("/detalle/cotizaciones")

public class DetalleCotizacionRest {

    @Autowired
    DetalleCotizacionService detalleCotizacionService;

    @GetMapping(value = "por/cotizacion/{id}")
    public ResponseEntity<List<DetalleCotizacion>> obtenerDetalleCotizacionPorCotizacionId(@PathVariable("id") long id) {
        List<DetalleCotizacion> detallesCotizacion =  new ArrayList<>();
        detallesCotizacion = detalleCotizacionService.obtenerDetalleCotizacionByCotizacionId(id);
            if (detallesCotizacion.isEmpty()) 
                return ResponseEntity.noContent().build();

        return  ResponseEntity.ok(detallesCotizacion);
    }

    @GetMapping
    public ResponseEntity<List<DetalleCotizacion>> obtenerTodosLosDetallesCotizacion() {
        List<DetalleCotizacion> detallesCotizacion =  new ArrayList<>();
        detallesCotizacion = detalleCotizacionService.buscarTodosLosDetalleCotizacion();
            if (detallesCotizacion.isEmpty()) 
                return ResponseEntity.noContent().build();

        return  ResponseEntity.ok(detallesCotizacion);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DetalleCotizacion> obtenerDetalleCotizacion(@PathVariable("id") long id) {
        DetalleCotizacion detallesCotizacion = detalleCotizacionService.obtenerDetalleCotizacion(id);
        if (  null == detallesCotizacion) {

            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(detallesCotizacion);
    }

    @PostMapping
    public ResponseEntity<DetalleCotizacion> crearDetalleCotizacion(@RequestBody DetalleCotizacion detalleCotizacion, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        DetalleCotizacion detalleCotizacionDB = detalleCotizacionService.crearDetalleCotizacion(detalleCotizacion);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(detalleCotizacionDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarDetalleCotizacion(@PathVariable("id") long id, @RequestBody DetalleCotizacion detalleCotizacion) {
  
        DetalleCotizacion detalleCotizacionActual = detalleCotizacionService.obtenerDetalleCotizacion(id);
  
        if ( null == detalleCotizacionActual ) {

            return  ResponseEntity.notFound().build();

        }
        detalleCotizacion.setId(id);
        detalleCotizacionActual = detalleCotizacionService.actualizarDetalleCotizacion(detalleCotizacion);
        return  ResponseEntity.ok(detalleCotizacionActual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DetalleCotizacion> borrarDetalleCotizacion(@PathVariable("id") long id) {
  
        DetalleCotizacion detalleCotizacion = detalleCotizacionService.obtenerDetalleCotizacion(id);
        if ( null == detalleCotizacion ) {

            return  ResponseEntity.notFound().build();

        }

        detalleCotizacion = detalleCotizacionService.borrarDetalleCotizacion(detalleCotizacion);
        return  ResponseEntity.ok(detalleCotizacion);
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