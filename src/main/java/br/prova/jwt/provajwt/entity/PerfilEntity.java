package br.prova.jwt.provajwt.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_perfil")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilEntity {
    @Schema(description = "Identificador unico")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome")
    @Column(
            name = "nome",
            nullable = false,
            unique = true,
            length = 50
    )
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<UsuarioEntity> usuarios = new HashSet<>();

}
