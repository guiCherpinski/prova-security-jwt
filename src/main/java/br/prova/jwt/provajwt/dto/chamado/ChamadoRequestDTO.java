package br.prova.jwt.provajwt.dto.chamado;

import br.prova.jwt.provajwt.entity.UsuarioEntity;
import br.prova.jwt.provajwt.entity.enumerated.PrioridadeChamadoEnum;
import br.prova.jwt.provajwt.entity.enumerated.StatusChamadoEnum;

import java.sql.Timestamp;

public record ChamadoRequestDTO(
        String titulo,
        String descricao,
        StatusChamadoEnum status,
        PrioridadeChamadoEnum prioridade,
        UsuarioEntity clienteId,
        UsuarioEntity tecnicoId,
        Timestamp dataCriacao
) {
}
