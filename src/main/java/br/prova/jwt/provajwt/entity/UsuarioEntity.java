package br.prova.jwt.provajwt.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Essa entity é a representação de um usuário
 */
@Schema(description = "Essa entity é a representação de um usuário")

@Entity
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEntity implements UserDetails {
    @Schema(description = "Identificado unico")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome")
    @Column(
            name = "nome",
            length = 100,
            nullable = false
    )
    private String nome;

    @Schema(description = "Email")
    @Column(
            name = "email",
            length = 100,
            nullable = false,
            unique = true
    )
    private String email;

    @Schema(description = "Senha")
    @Column(
            name = "senha",
            nullable = false,
            length = 255
    )
    private String senha;

    @Schema(description = "Data de criacao")
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
        return this.perfis.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
