package movil.pos.venta.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "recibo", schema = "movilpos")
public class Recibo implements Serializable {

    private static final long serialVersionUID = -8981377173465696512L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reciboSequenceGenerator")
    @SequenceGenerator(name = "reciboSequenceGenerator", sequenceName = "recibo_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    @Column(name = "numero_recibo")
    private String numeroRecibo;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    Timestamp fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "metodo_pago")
    private int metodoPago;

    @Column(name = "monto_efectivo")
    private BigDecimal montoEfectivo;

    @Column(name = "monto_tarjeta")
    private BigDecimal montoTarjeta;

    @Column(name = "monto_cheque")
    private BigDecimal montoCheque;

    @Column(name = "monto_transferencia")
    private BigDecimal montoTransferencia;

    @Column(name = "referencia_tarjeta")
    private String referenciaTarjeta;

    @Column(name = "numero_cheque")
    private String numeroCheque;

    @Column(name = "referencia_transferencia")
    private String referenciaTransferencia;

    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal descuento;
    private BigDecimal total;
    private BigDecimal cambio;
    private String estado;
    private boolean activo;
    
}