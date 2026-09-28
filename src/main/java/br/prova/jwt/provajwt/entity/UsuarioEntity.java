package br.prova.jwt.provajwt.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEntity extends UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "nome",
            length = 100,
            nullable = false
    )
    private String nome;

    @Column(
            name = "email",
            length = 100,
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "senha",
            nullable = false,
            length = 255
    )
    private String senha;

    @Column(
            name = "data_criacao",
            nullable = false,
            updatable = false
    )
    private Timestamp dataCriacao;

    @OneToMany(mappedBy = "cliente")
    private Set<ChamadoEntity> chamadosCliente = new HashSet<>();

    @OneToMany(mappedBy = "tecnico")
    private Set<ChamadoEntity> chamadosTecnico = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<RespostaEntity> respostas = new HashSet<>();

    @ManyToMany(mappedBy = "usuarios")
    private Set<PerfilEntity> perfis = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+perfis));
    }
}
