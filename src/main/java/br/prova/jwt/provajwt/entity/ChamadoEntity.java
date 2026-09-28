package br.prova.jwt.provajwt.entity;

import br.prova.jwt.provajwt.entity.enumerated.PrioridadeChamadoEnum;
import br.prova.jwt.provajwt.entity.enumerated.StatusChamadoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_chamado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChamadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "titulo",
            length = 150,
            nullable = false
    )
    private String titulo;

    @Column(
            name = "descricao",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String descricao;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false
    )
    private StatusChamadoEnum status;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "prioridade",
            nullable = false
    )
    private PrioridadeChamadoEnum prioridade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private UsuarioEntity cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tecnico_id")
    private UsuarioEntity tecnico;

    @Column(
            name = "data_criacao",
            nullable = false,
            updatable = false
    )
    private Timestamp dataCriacao;

    @Column(
            name = "data_atualizacao",
            nullable = false
    )
    private Timestamp dataAtualizacao;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RespostaEntity> respostas = new HashSet<>();
}
