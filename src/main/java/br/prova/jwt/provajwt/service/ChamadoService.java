package br.prova.jwt.provajwt.service;

import br.prova.jwt.provajwt.dto.chamado.ChamadoResponseDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoRequestDTO;
import br.prova.jwt.provajwt.entity.ChamadoEntity;
import br.prova.jwt.provajwt.exceptions.ChamadoNotFound;
import br.prova.jwt.provajwt.exceptions.EmptyListException;
import br.prova.jwt.provajwt.mapper.ChamadoMapper;
import br.prova.jwt.provajwt.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository repository;
    private final ChamadoMapper mapper;

    public ChamadoService(ChamadoRepository repository, ChamadoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ChamadoResponseDTO> listarChamados(){
        if (repository.findAll().isEmpty()){
            throw new EmptyListException("erro - não há nenhum chamado");
        }

        List<ChamadoEntity> entities = repository.findAll();
        List<ChamadoResponseDTO> responses = mapper.toResponseList(entities);

        return responses;
    }

    public void deletarChamado(Long id){
        ChamadoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ChamadoNotFound("erro - chamado não encontrado"));

        repository.deleteById(id);
    }

    public ChamadoRequestDTO abrirChamado(ChamadoRequestDTO requestDTO){

    }
}
