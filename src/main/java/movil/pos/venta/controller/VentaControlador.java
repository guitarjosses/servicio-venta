package movil.pos.venta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movil.pos.venta.ResourceNotFoundException;
import movil.pos.venta.repository.ClienteRepository;
import movil.pos.venta.repository.FuncionarioRepository;
import movil.pos.venta.repository.VentaRepository;
import movil.pos.venta.repository.entity.Venta;

@RestController
@RequestMapping("/api")
public class VentaControlador {

    private Venta venta;

    @Autowired
    VentaRepository ventaRepositorio;

    @Autowired
    ClienteRepository clienteRepositorio;

    @Autowired
    FuncionarioRepository funcionarioRepositorio;

    @GetMapping("/venta")
    public List<Venta> getVentas() {
        return ventaRepositorio.findAll();
    }

    @PostMapping("cliente/{clienteId}/vendedor/{vendedorId}/venta")
    public Venta crearVenta(@PathVariable(value = "clienteId") Long clienteId,
            @PathVariable(value = "vendedorId") Long vendedorId, @Validated @RequestBody Venta venta) {
        this.venta = venta;
        clienteRepositorio.findById(clienteId).map(cliente -> {
            this.venta.setCliente(cliente);
            return this.venta;
        }).orElseThrow(() -> new ResourceNotFoundException("Cliente ","id",clienteId));
        
        funcionarioRepositorio.findById(vendedorId).map(vendedor -> {
            this.venta.setFuncionario(vendedor);
            return this.venta;
        }).orElseThrow(() -> new ResourceNotFoundException("Vendedor ","id",vendedorId));
        
        return ventaRepositorio.save(venta);
    }
    
}