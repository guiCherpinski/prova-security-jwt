package br.prova.jwt.provajwt.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Essa é a TokenRequestDTO, ela define o que deve ser passado para retornar o token
 * @param username
 * @param password
 */
@Schema(description = "Essa é a TokenRequestDTO, ela define o que deve ser passado para retornar o token")
public record TokenRequestDTO (
        @Schema(description = "nome do usuario",example = "Carlos fabio andrade")
        String username,

        @Schema(description = "senha do usuario",example = "batatinha123")
        String password
){
}
