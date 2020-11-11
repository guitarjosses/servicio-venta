package movil.pos.venta.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;

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
@Table(name = "venta", schema = "movilpos")
public class Venta implements Serializable {

private static final long serialVersionUID = -3632876529395882815L;

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ventaSequenceGenerator")
@SequenceGenerator(name = "ventaSequenceGenerator", sequenceName = "venta_id_seq", allocationSize = 1, schema = "movilpos")
private Long id;

@Column(name = "numero_venta")
String numeroVenta;

Timestamp fecha;

@ManyToOne
@JoinColumn(name = "cliente_id")
private Cliente cliente;


@ManyToOne
@JoinColumn(name = "vendedor_id")
private Funcionario funcionario;


@Column(name = "tipo_documento")
int tipoDocumento;

@Column(name = "tipo_pago")
int tipoPago;

@Column(name = "semana_dia_pago_id")
int semanaDiaPagoId;

@Column(name = "quincena_dia1_pago_id")
int quincenaDia1PagoId;

@Column(name = "quincena_dia2_pago_id")
int quincenaDia2PagoId;

@Column(name = "mes_dia_pago")
Date mesDiaPago;

BigDecimal subtotal;
BigDecimal impuesto;
BigDecimal descuento;
BigDecimal total;

String estado;

boolean activa;

}