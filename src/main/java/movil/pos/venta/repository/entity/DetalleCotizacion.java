package movil.pos.venta.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "detalle_cotizacion", schema = "movilpos")
public class DetalleCotizacion implements Serializable{

    private static final long serialVersionUID = -2699946521373993394L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "detalleCotizacionSequenceGenerator")
    @SequenceGenerator(name = "detalleCotizacionSequenceGenerator", sequenceName = "detalle_cotizacion_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    @Column(name = "cotizacion_id")
    private Long cotizacionId;

    @Column(name = "producto_id")
    private Long productoId;

    BigDecimal precio;
    BigDecimal cantidad;
    BigDecimal subtotal;
    BigDecimal impuesto;
    BigDecimal descuento;
    BigDecimal total;

    
}