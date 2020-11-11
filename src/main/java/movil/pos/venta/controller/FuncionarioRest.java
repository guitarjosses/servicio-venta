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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import movil.pos.venta.repository.entity.Funcionario;
import movil.pos.venta.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioRest {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<Funcionario>> obtenerTodosLosFuncionarios() {
        List<Funcionario> funcionarios =  new ArrayList<>();
        funcionarios = funcionarioService.buscarTodosLosFuncionarios();
            if (funcionarios.isEmpty()) 
                return ResponseEntity.noContent().build();
          
        return  ResponseEntity.ok(funcionarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> obtenerFuncionario(@PathVariable("id") long id) {

        Funcionario funcionario = funcionarioService.obtenerFuncionario(id);
        if (  null == funcionario) {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(funcionario);
    }

    @PostMapping
    public ResponseEntity<Funcionario> crearFuncionario(@RequestBody Funcionario funcionario, BindingResult result) {

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
  
        Funcionario funcionarioDB = funcionarioService.crearFuncionario(funcionario);
  
        return  ResponseEntity.status( HttpStatus.CREATED).body(funcionarioDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarFuncionario(@PathVariable("id") long id, @RequestBody Funcionario funcionario) {
  
        Funcionario funcionarioActual = funcionarioService.obtenerFuncionario(id);
  
        if ( null == funcionarioActual ) {
            return  ResponseEntity.notFound().build();
        }

        funcionario.setId(id);

        funcionarioActual = funcionarioService.actualizarFuncionario(funcionario);
        return  ResponseEntity.ok(funcionarioActual);
    }

    
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Funcionario> deleteCustomer(@PathVariable("id") long id) {

    Funcionario funcionario = funcionarioService.obtenerFuncionario(id);
      if ( null == funcionario ) {

          return  ResponseEntity.notFound().build();
      }
      funcionario = funcionarioService.borrarFuncionario(funcionario);
      return  ResponseEntity.ok(funcionario);
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