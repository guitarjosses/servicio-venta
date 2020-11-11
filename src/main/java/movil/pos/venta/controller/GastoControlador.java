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

import movil.pos.venta.repository.GastoRepository;
import movil.pos.venta.repository.entity.Gasto;

@RestController
@RequestMapping("/api")

public class GastoControlador {

    @Autowired
    GastoRepository gastoRepositorio;

    @GetMapping("/gasto")
    public ResponseEntity<List<Gasto>> obtenerGastos() {

        List<Gasto> gastos =  new ArrayList<>();
        gastos = gastoRepositorio.findAll();
            if (gastos.isEmpty()) 
                return ResponseEntity.noContent().build();
          
        return  ResponseEntity.ok(gastos);
    }

    @PostMapping("/gasto")
    public ResponseEntity<Gasto> crearGasto( @Validated @RequestBody Gasto gasto, BindingResult result) {


        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        Gasto _gasto  = gastoRepositorio.save(gasto);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(_gasto);
    
    }

    @GetMapping("/gasto/{id}")
    public ResponseEntity<Gasto> obtenerGsstoPorId(@PathVariable(value = "id") Long gastoId) {
        

        Gasto gasto = gastoRepositorio.findById(gastoId).orElse(null);
                if (  null == gasto) {
                    return  ResponseEntity.notFound().build();
                }
                return  ResponseEntity.ok(gasto);
    }

    @PutMapping("/gasto/{id}")
    public ResponseEntity<Gasto> actualizarImpresora(@PathVariable(value = "id") Long gastoId,
                                            @Validated @RequestBody Gasto nuevoGasto) {

                                                Gasto gasto = gastoRepositorio.findById(gastoId).orElse(null);

        if(gasto == null){
            return  ResponseEntity.notFound().build();
        }

                gasto.setFecha(nuevoGasto.getFecha());
                gasto.setCategoriaGastoId(nuevoGasto.getCategoriaGastoId());
                gasto.setDescripcion(nuevoGasto.getDescripcion());
                gasto.setImporte(nuevoGasto.getImporte());                
                gasto.setActivo(nuevoGasto.isActivo());


                Gasto gastoActualizado = gastoRepositorio.save(gasto);
        return ResponseEntity.ok(gastoActualizado);
    }

    @DeleteMapping("/gasto/{id}")
    public ResponseEntity<Gasto> borraGasto(@PathVariable(value = "id") Long gastoId) {
        Gasto gasto = gastoRepositorio.findById(gastoId).orElse(null);

        if(null == gasto){
            return  ResponseEntity.notFound().build();
        }

                gastoRepositorio.delete(gasto);

        return ResponseEntity.ok(gasto);
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