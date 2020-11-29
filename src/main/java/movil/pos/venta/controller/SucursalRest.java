package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.Sucursal;
import movil.pos.venta.service.SucursalService;

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
@RequestMapping("/sucursales")
public class SucursalRest {

    @Autowired
    SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> obtenerTodasLasSucursales() {
        List<Sucursal> sucursales =  new ArrayList<>();
        sucursales = sucursalService.buscarTodasLasSucursales();
            if (sucursales.isEmpty()) 
                return ResponseEntity.noContent().build();
          
        return  ResponseEntity.ok(sucursales);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sucursal> obtenerSucursal(@PathVariable("id") long id) {
        Sucursal sucursal = sucursalService.obtenerSucursal(id);
        if (  null == sucursal) {

            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(sucursal);
    }

    @PostMapping
    public ResponseEntity<Sucursal> crearRecibo(@RequestBody Sucursal sucursal, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        Sucursal sucursalDB = sucursalService.crearSucursal(sucursal);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(sucursalDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarSucursal(@PathVariable("id") long id, @RequestBody Sucursal sucursal) {
  
        Sucursal sucursalActual = sucursalService.obtenerSucursal(id);
  
        if ( null == sucursalActual ) {

            return  ResponseEntity.notFound().build();

        }
        sucursal.setId(id);
        sucursalActual = sucursalService.actualizarSucursal(sucursal);
        return  ResponseEntity.ok(sucursalActual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Sucursal> borrarSucursal(@PathVariable("id") long id) {
  
        Sucursal sucursal = sucursalService.obtenerSucursal(id);
        if ( null == sucursal ) {

            return  ResponseEntity.notFound().build();

        }

        sucursal = sucursalService.borrarSucursal(sucursal);
        return  ResponseEntity.ok(sucursal);
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