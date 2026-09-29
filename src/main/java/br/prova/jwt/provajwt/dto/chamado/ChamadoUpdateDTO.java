package br.prova.jwt.provajwt.dto.chamado;

import br.prova.jwt.provajwt.entity.enumerated.StatusChamadoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Essa é a ChamadoUpdateDTO, ela define o que deve ser enviado para ser atualizado
 * @param status
 */

public record ChamadoUpdateDTO(
        @Schema(description = "status",example = "ABERTO")
        StatusChamadoEnum status
) {
}
