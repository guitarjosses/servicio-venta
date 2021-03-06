package movil.pos.venta.controller;

import movil.pos.venta.repository.entity.Recibo;
import movil.pos.venta.repository.entity.Venta;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/ventas")
public class VentaRest {

    @Autowired
    VentaService ventaService;

    @Autowired
    ReciboService reciboService;

    @GetMapping("/por/rango/fecha1/{fechaInicial}/fecha2/{fechaFinal}")
    public ResponseEntity<List<Venta>> obtenerVentasPorRangoFecha(@PathVariable(value = "fechaInicial") Timestamp fechaInicial,
    @PathVariable(value = "fechaFinal") Timestamp fechaFinal){

        List<Venta> ventas =  new ArrayList<>();
        List<Venta> ventas1 =  new ArrayList<>();

        ventas = ventaService.obtenerVentasPorRangoFecha(fechaInicial, fechaFinal);

        if (ventas.isEmpty()) 
                return ResponseEntity.noContent().build();

                        for(Venta venta:ventas){
            BigDecimal total = BigDecimal.ZERO;
            List<Recibo> recibos =  new ArrayList<>();
            venta.setSaldo(venta.getTotal().subtract(total));

            recibos = reciboService.obtenerRecibosPorVentaId(venta.getId());
            if (!recibos.isEmpty()) 
                {
                    for(Recibo recibo:recibos){
                        total = total.add(recibo.getTotal());
                    }
                    System.out.println("entra total " + venta.getTotal().subtract(total));
                    venta.setSaldo(venta.getTotal().subtract(total));
                }
        ventas1.add(venta);
        }
          
        return  ResponseEntity.ok(ventas1);

    }

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodosLasVentas() {
        List<Venta> ventas =  new ArrayList<>();
        List<Venta> ventas1 =  new ArrayList<>();
        ventas = ventaService.buscarTodasLasVentas();
            if (ventas.isEmpty()) 
                return ResponseEntity.noContent().build();
        
        for(Venta venta:ventas){
            BigDecimal total = BigDecimal.ZERO;
            List<Recibo> recibos =  new ArrayList<>();
            venta.setSaldo(venta.getTotal().subtract(total));

            recibos = reciboService.obtenerRecibosPorVentaId(venta.getId());
            if (!recibos.isEmpty()) 
                {
                    for(Recibo recibo:recibos){
                        total = total.add(recibo.getTotal());
                    }
                    System.out.println("entra total " + venta.getTotal().subtract(total));
                    venta.setSaldo(venta.getTotal().subtract(total));
                }
        ventas1.add(venta);
        }
          
        return  ResponseEntity.ok(ventas1);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Venta> obtenerVenta(@PathVariable("id") long id) {
        Venta venta = ventaService.obtenerVenta(id);
        if (  null == venta) {

            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(venta);
    }

    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        Venta ventaDB = ventaService.crearVenta(venta);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(ventaDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarVenta(@PathVariable("id") long id, @RequestBody Venta venta) {
  
        Venta ventaActual = ventaService.obtenerVenta(id);
  
        if ( null == ventaActual ) {

            return  ResponseEntity.notFound().build();

        }
        venta.setId(id);
        ventaActual = ventaService.actualizarVenta(venta);
        return  ResponseEntity.ok(ventaActual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Venta> borrarRecibo(@PathVariable("id") long id) {
  
        Venta venta = ventaService.obtenerVenta(id);
        if ( null == venta ) {

            return  ResponseEntity.notFound().build();

        }

        venta = ventaService.borrarVenta(venta);
        return  ResponseEntity.ok(venta);
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