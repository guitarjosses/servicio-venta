package movil.pos.venta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movil.pos.venta.ResourceNotFoundException;
import movil.pos.venta.repository.ClienteRepository;
import movil.pos.venta.repository.FuncionarioRepository;
import movil.pos.venta.repository.CotizacionRepository;
import movil.pos.venta.repository.entity.Cotizacion;

@RestController
@RequestMapping("/api")
public class CotizacionControlador {

    private Cotizacion cotizacion;

    @Autowired
    CotizacionRepository cotizacionRepositorio;

    @Autowired
    ClienteRepository clienteRepositorio;

    @Autowired
    FuncionarioRepository funcionarioRepositorio;

    @GetMapping("/cotizacion")
    public List<Cotizacion> getCotizaciones() {
        return cotizacionRepositorio.findAll();
    }

    @PostMapping("cliente/{clienteId}/vendedor/{vendedorId}/cotizacion")
    public Cotizacion crearCotizacion(@PathVariable(value = "clienteId") Long clienteId,
            @PathVariable(value = "vendedorId") Long vendedorId, @Validated @RequestBody Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
        clienteRepositorio.findById(clienteId).map(cliente -> {
            this.cotizacion.setCliente(cliente);
            return this.cotizacion;
        }).orElseThrow(() -> new ResourceNotFoundException("Cliente ","id",clienteId));
        
        funcionarioRepositorio.findById(vendedorId).map(vendedor -> {
            this.cotizacion.setFuncionario(vendedor);
            return this.cotizacion;
        }).orElseThrow(() -> new ResourceNotFoundException("Vendedor ","id",vendedorId));
        
        return cotizacionRepositorio.save(cotizacion);
    }

    @PutMapping(value = "/anular/cotizacion/{id}")
    public Cotizacion anularCotizacion(@PathVariable("id") long id){

        Cotizacion cotizacionActual = cotizacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cotizacion ","id",id));

        cotizacionActual.setActiva(false);

        return cotizacionRepositorio.save(cotizacionActual);
    }
    
}