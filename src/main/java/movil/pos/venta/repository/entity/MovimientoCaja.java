package movil.pos.venta.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "movimiento_caja", schema = "movilpos")
public class MovimientoCaja implements Serializable {

    private static final long serialVersionUID = -8981377173465696512L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "movcajaSequenceGenerator")
    @SequenceGenerator(name = "movcajaSequenceGenerator", sequenceName = "movcaja_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    @Column(name = "caja_id")
    private Long cajaId;

    LocalDateTime fecha_movimiento;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "tipo_mov")
    private int tipo_mov;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "es_apertura")
    private int esApertura;    
}