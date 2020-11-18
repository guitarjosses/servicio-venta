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
@Table(name = "caja", schema = "movilpos")
public class Caja implements Serializable {

    private static final long serialVersionUID = -8981377173465696513L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cajaSequenceGenerator")
    @SequenceGenerator(name = "cajaSequenceGenerator", sequenceName = "caja_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    @Column(name = "codigo")
    private String codigoCaja;

    @Column(name = "nombre")
    private String nombreCaja;    
}