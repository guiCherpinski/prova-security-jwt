package br.prova.jwt.provajwt.entity;

import br.prova.jwt.provajwt.entity.enumerated.PrioridadeChamadoEnum;
import br.prova.jwt.provajwt.entity.enumerated.StatusChamadoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Essa entity é a representação de um chamado
 */
@Schema(description = "Essa entity é a representação de um chamado")
@Entity
@Table(name = "tb_chamado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChamadoEntity {
    @Schema(description = "Identificador unico")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "titulo")
    @Column(
            name = "titulo",
            length = 150,
            nullable = false
    )
    private String titulo;

    @Schema(description = "descricao")
    @Column(
            name = "descricao",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String descricao;

    @Schema(description = "Status do chamado")
    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false
    )
    private StatusChamadoEnum status;

    @Schema(description = "Prioridade da chamada")
    @Enumerated(EnumType.STRING)
    @Column(
            name = "prioridade",
            nullable = false
    )
    private PrioridadeChamadoEnum prioridade;

    @Schema(description = "Identificador do usuario")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private UsuarioEntity cliente;

    @Schema(description = "Identificador do tecnico")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tecnico_id")
    private UsuarioEntity tecnico;

    @Schema(description = "data da criacao")
    @Column(
            name = "data_criacao",
            nullable = false,
            updatable = false
    )
    private Timestamp dataCriacao;

    @Schema(description = "Data da atualizacao")
    @Column(
            name = "data_atualizacao",
            nullable = false
    )
    private Timestamp dataAtualizacao;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RespostaEntity> respostas = new HashSet<>();
}
