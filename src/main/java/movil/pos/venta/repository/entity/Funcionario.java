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
@Table(name = "funcionario", schema = "movilpos")
public class Funcionario implements Serializable{

    private static final long serialVersionUID = -2261975052302467267L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "funcionarioSequenceGenerator")
    @SequenceGenerator(name = "funcionarioSequenceGenerator", sequenceName = "funcionario_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    private String nombre;
    private String usuario;
    private String contrasenia;
    private String ci;
    
    // Agregando sucursal
    @Column(name = "sucursal_id")
    private Long sucursalId;

    @Column(name = "categoria_id")
    private Long categoriaId;

    private boolean activo;
    private String ciudad;
    private String telefono;
    private String movil;

    @Column(name = "correo_electronico")
    private String correoElectronico;    

    private String direccion;

    @Column(name = "efectuar_venta")
    private boolean efectuarVenta;

    @Column(name = "administrar_producto")
    private boolean administrarProducto;
    
    @Column(name = "reponer_stock")
    private boolean reponerStock;
    
    @Column(name = "administrar_cliente")
    private boolean administrarCliente;
    
    @Column(name = "recibo_cliente")
    private boolean reciboCliente;
    
    @Column(name = "descuento_por_item")
    private boolean descuentoPorItem;
    
    @Column(name = "descuento_total_venta")
    private boolean descuentoTotalVenta;
    
    @Column(name = "cambiar_tipo_precio_venta")
	private boolean cambiarTipoPrecioVenta;
    
}
