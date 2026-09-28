package br.prova.jwt.provajwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tb_resposta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespostaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "mensagem",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String mensagem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chamado_id", nullable = false)
    private ChamadoEntity chamado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false)
    private UsuarioEntity usuario;

    @Column(
            name = "data_criacao",
            nullable = false,
            updatable = false
    )
    private Timestamp dataCriacao;

}
