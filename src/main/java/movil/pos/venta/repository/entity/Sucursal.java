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
@Table(name = "sucursal", schema = "movilpos")
public class Sucursal implements Serializable {

    private static final long serialVersionUID = -8981377173465696513L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sucursalSequenceGenerator")
    @SequenceGenerator(name = "sucursalSequenceGenerator", sequenceName = "sucursal_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    @Column(name = "nombre")
    private String nombreSucursal;    
}