package movil.pos.venta.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "gasto", schema = "movilpos")
public class Gasto implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 2640784355517942229L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gastoSequenceGenerator")
    @SequenceGenerator(name = "gastoSequenceGenerator", sequenceName = "gasto_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    private Timestamp fecha;

    @Column(name = "categoria_gasto_id")
    private int categoriaGastoId;

    private String descripcion;

    private BigDecimal importe;

    private boolean activo;
    
}