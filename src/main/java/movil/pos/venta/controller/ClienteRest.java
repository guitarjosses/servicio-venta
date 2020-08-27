package movil.pos.venta.controller;

import movil.pos.venta.service.ClienteService;

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
import movil.pos.venta.repository.entity.Cliente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class ClienteRest {

  @Autowired
  ClienteService clienteService;

  // -------------------Retrieve All Customers--------------------------------------------

  @GetMapping
  public ResponseEntity<List<Cliente>> listAllCustomers() {
      List<Cliente> clientes =  new ArrayList<>();
      clientes = clienteService.findCustomerAll();
          if (clientes.isEmpty()) 
              return ResponseEntity.noContent().build();
        
      return  ResponseEntity.ok(clientes);
  }

  // -------------------Retrieve Single Customer------------------------------------------

  @GetMapping(value = "/{id}")
  public ResponseEntity<Cliente> getCustomer(@PathVariable("id") long id) {
      log.info("Fetching Customer with id {}", id);
      Cliente cliente = clienteService.getCustomer(id);
      if (  null == cliente) {
          log.error("Customer with id {} not found.", id);
          return  ResponseEntity.notFound().build();
      }
      return  ResponseEntity.ok(cliente);
  }

  // -------------------Create a Customer-------------------------------------------

  @PostMapping
  public ResponseEntity<Cliente> createCustomer(@RequestBody Cliente cliente, BindingResult result) {
      log.info("Creating Customer : {}", cliente);
      if (result.hasErrors()){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
      }

     Cliente clienteDB = clienteService.createCustomer (cliente);

      return  ResponseEntity.status( HttpStatus.CREATED).body(clienteDB);
  }

  // ------------------- Update a Customer ------------------------------------------------

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Cliente cliente) {
      log.info("Updating Customer with id {}", id);

      Cliente currentCliente = clienteService.getCustomer(id);

      if ( null == currentCliente ) {
          log.error("Unable to update. Customer with id {} not found.", id);
          return  ResponseEntity.notFound().build();
      }
      cliente.setId(id);
      currentCliente=clienteService.updateCustomer(cliente);
      return  ResponseEntity.ok(currentCliente);
  }

  // ------------------- Delete a Customer-----------------------------------------

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Cliente> deleteCustomer(@PathVariable("id") long id) {
      log.info("Fetching & Deleting Customer with id {}", id);

      Cliente cliente = clienteService.getCustomer(id);
      if ( null == cliente ) {
          log.error("Unable to delete. Customer with id {} not found.", id);
          return  ResponseEntity.notFound().build();
      }
      cliente = clienteService.deleteCustomer(cliente);
      return  ResponseEntity.ok(cliente);
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