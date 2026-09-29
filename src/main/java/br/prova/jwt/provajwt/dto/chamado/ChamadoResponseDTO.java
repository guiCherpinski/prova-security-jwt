package br.prova.jwt.provajwt.dto.chamado;

import br.prova.jwt.provajwt.entity.UsuarioEntity;
import br.prova.jwt.provajwt.entity.enumerated.PrioridadeChamadoEnum;
import br.prova.jwt.provajwt.entity.enumerated.StatusChamadoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;

/**
 * Essa é a ChamadoResponseDTO, ela define tudo que vai ser retornado
 * @param id
 * @param titulo
 * @param descricao
 * @param status
 * @param prioridade
 * @param cliente
 * @param tecnico
 * @param dataCriacao
 * @param dataAtualizacao
 */

@Schema(description = "Essa é a ChamadoResponseDTO, ela define tudo que vai ser retornado")
public record ChamadoResponseDTO(
        @Schema(description = "Identificador unico",example = "1")
        Long id,

        @Schema(description = "Titulo",example = "Motor quebrou")
        String titulo,

        @Schema(description = "descricao",example = "Motor quebrou caindo")
        String descricao,

        @Schema(description = "status do motor",example = "ABERTO")
        StatusChamadoEnum status,

        @Schema(description = "Prioridade",example = "ALTA")
        PrioridadeChamadoEnum prioridade,

        @Schema(description = "Objeto do cliente")
        UsuarioEntity cliente,

        @Schema(description = "Objeto do tecnico")
        UsuarioEntity tecnico,

        @Schema(description = "data de criacao",example = "2026-10-24")
        Timestamp dataCriacao,

        @Schema(description = "data de atualizacao",example = "2026-11-24")
        Timestamp dataAtualizacao
) {
}
