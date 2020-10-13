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
@Table(name = "sequencia", schema = "movilpos")
public class Sequencia  implements Serializable{

    private static final long serialVersionUID = 460436944248399000L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenciaPagoSequenceGenerator")
    @SequenceGenerator(name = "sequenciaPagoSequenceGenerator", sequenceName = "sequencia_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    private int valor;
    private String prefijo;
    private boolean activo;     
    
}