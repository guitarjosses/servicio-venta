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
@Table(name = "impresora", schema = "movilpos")
public class Impresora  implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = -6626547282941895780L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "impresoraSequenceGenerator")
    @SequenceGenerator(name = "impresoraSequenceGenerator", sequenceName = "impresora_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    private int modelo;
    private int tipo;
    private String nombre;
    private String ip;
    private int puerto;

    @Column(name = "pagina_codigo")
    private int paginaCodigo; 

    private char font;
    private int mm;
    private int caracteres;

    @Column(name = "imprime_factura")
    private boolean imprimeFactura;

    @Column(name = "imprime_pago")
    private boolean imprimePago;

    private boolean activa;

}