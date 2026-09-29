package br.prova.jwt.provajwt.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Essa é a TokenResponseDTO, ela retorna o token
 * @param token
 */

@Schema(description = "Essa é a TokenResponseDTO, ela retorna o token")
public record TokenResponseDTO (
        @Schema(description = "token",example = "12341243355451475j436b56b")
        String token
){
}
