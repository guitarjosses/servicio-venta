package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.Cliente;
import movil.pos.venta.repository.entity.Recibo;
import movil.pos.venta.repository.entity.Venta;
import movil.pos.venta.service.ClienteService;
import movil.pos.venta.service.ReciboService;
import movil.pos.venta.service.VentaService;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/recibos")
public class ReciboRest {

    @Autowired
    ReciboService reciboService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    VentaService ventaService;

    @GetMapping(value = "total/recibos/por/venta/{id}")
    public ResponseEntity<Recibo> obteneTotalRecibosPorVentaId(@PathVariable("id") long id) {
        BigDecimal total = BigDecimal.ZERO;
        List<Recibo> recibos =  new ArrayList<>();
        Recibo recib = new Recibo();
        recib.setTotal(total);
        recibos = reciboService.obtenerRecibosPorVentaId(id);
            if (!recibos.isEmpty()) 
                {
                    for(Recibo recibo:recibos){
                        total = total.add(recibo.getTotal());
                    }
                    recib.setTotal(total);
                }

        return  ResponseEntity.ok(recib);
    }

    @GetMapping(value = "por/venta/{id}")
    public ResponseEntity<List<Recibo>> obteneRecibosPorVentaId(@PathVariable("id") long id) {
        List<Recibo> recibos =  new ArrayList<>();
        recibos = reciboService.obtenerRecibosPorVentaId(id);
            if (recibos.isEmpty()) 
                return ResponseEntity.noContent().build();

        return  ResponseEntity.ok(recibos);
    }

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

    @PostMapping("cliente/{clienteId}/venta/{ventaId}/recibo")
    public ResponseEntity<Recibo> crearRecibo(@PathVariable(value = "clienteId") Long clienteId,
    @PathVariable(value = "ventaId") Long ventaId,@RequestBody Recibo recibo, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Cliente cliente = clienteService.getCustomer(clienteId);

        recibo.setCliente(cliente);

        Venta venta = ventaService.obtenerVenta(ventaId);

        recibo.setVenta(venta);
  
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