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
@Table(name = "detalle_venta", schema = "movilpos")
public class DetalleVenta implements Serializable{

    private static final long serialVersionUID = -2699946521373993394L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "detalleVentaSequenceGenerator")
    @SequenceGenerator(name = "detalleVentaSequenceGenerator", sequenceName = "detalle_venta_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    @Column(name = "venta_id")
    private Long ventaId;

    @Column(name = "producto_id")
    private Long productoId;

    BigDecimal precio;
    int cantidad;
    BigDecimal subtotal;
    BigDecimal impuesto;
    BigDecimal descuento;
    BigDecimal total;

    
}