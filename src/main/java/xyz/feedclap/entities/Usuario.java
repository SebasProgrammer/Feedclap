package xyz.feedclap.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name="id_usuario")
    private Integer id_usuario;

    @Column(name="nombre_usuario")
    private String nombre_usuario;

    @Column(name="nombre")
    private String nombre;

    @Column(name="edad")
    private Integer edad;

    @Column(name="tipo_usuario")
    private String tipo_usuario;

    @Column(name="correo")
    private String correo;

    @Column(name="id_pago")
    private Integer id_pago;


}
