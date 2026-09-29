package br.prova.jwt.provajwt.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Identificador unico")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Mensagem")
    @Column(
            name = "mensagem",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String mensagem;

    @Schema(description = "Identificador do chamado")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chamado_id", nullable = false)
    private ChamadoEntity chamado;

    @Schema(description = "Identificador do autor ")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false)
    private UsuarioEntity usuario;

    @Schema(description = "Data da criacao")
    @Column(
            name = "data_criacao",
            nullable = false,
            updatable = false
    )
    private Timestamp dataCriacao;

}
