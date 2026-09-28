package br.prova.jwt.provajwt.dto.chamado;

import br.prova.jwt.provajwt.entity.UsuarioEntity;
import br.prova.jwt.provajwt.entity.enumerated.PrioridadeChamadoEnum;
import br.prova.jwt.provajwt.entity.enumerated.StatusChamadoEnum;

import java.sql.Timestamp;

public record ChamadoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        StatusChamadoEnum status,
        PrioridadeChamadoEnum prioridade,
        UsuarioEntity cliente,
        UsuarioEntity tecnico,
        Timestamp dataCriacao,
        Timestamp dataAtualizacao
) {
}
