package movil.pos.venta.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "termino_pago", schema = "movilpos")
public class TerminoPago implements Serializable {

    private static final long serialVersionUID = -6239718348001119686L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "terminoPagoSequenceGenerator")
    @SequenceGenerator(name = "terminoPagoSequenceGenerator", sequenceName = "termino_pago_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    private String nombre;

    @Column(name = "dias_credito")
    private int diasCredito;
    
    private boolean predeterminado;
    private boolean activo;
    
}