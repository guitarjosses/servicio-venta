package movil.pos.venta.repository.entity;

import java.io.Serializable;

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
@Table(name = "cliente", schema = "movilpos")
public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 959612097235675985L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "clienteSequenceGenerator")
    @SequenceGenerator(name = "clienteSequenceGenerator", sequenceName = "cliente_id_seq", allocationSize = 1,schema = "movilpos")
    private Long id;

    @Column(name = "ruc_ci")
    private String rucCi;

    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String movil;

    @Column(name = "correo_electronico")
    private String correoElectronico;

}