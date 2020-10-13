package movil.pos.venta.repository.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "forma_pago", schema = "movilpos")
public class FormaPago implements Serializable{

    private static final long serialVersionUID = -2690597933923340015L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "formaPagoSequenceGenerator")
    @SequenceGenerator(name = "formaPagoSequenceGenerator", sequenceName = "forma_pago_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    private String nombre;

    private boolean predeterminado;
    private boolean activo;    
    
}