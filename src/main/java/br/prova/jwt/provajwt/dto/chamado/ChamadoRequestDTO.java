package br.prova.jwt.provajwt.dto.chamado;

import br.prova.jwt.provajwt.entity.UsuarioEntity;
import br.prova.jwt.provajwt.entity.enumerated.PrioridadeChamadoEnum;
import br.prova.jwt.provajwt.entity.enumerated.StatusChamadoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;

/**
 * Essa é a ChamadoRequestDTO, ela define o que deve ser passado para criar um chamado
 * @param titulo
 * @param descricao
 * @param status
 * @param prioridade
 * @param clienteId
 * @param tecnicoId
 * @param dataCriacao
 */

@Schema(description = "Define o que deve ser passado para criar um chamado")

public record ChamadoRequestDTO(
        @Schema(description = "titulo", example = "Motor quebrou")
        String titulo,

        @Schema(description = "descricao",example = "O motor quebrou caindo")
        String descricao,

        @Schema(description = "status do chamado",example = "ABERTO")
        StatusChamadoEnum status,

        @Schema(description = "prioridade do chamado",example = "ALTA")
        PrioridadeChamadoEnum prioridade,

        @Schema(description = "Identificador do cliente",example = "1")
        Long clienteId,

        @Schema(description = "Identificador do tecnico",example = "2")
        Long tecnicoId,

        @Schema(description = "data de criacao",example = "2026-10-23")
        Timestamp dataCriacao
) {
}
