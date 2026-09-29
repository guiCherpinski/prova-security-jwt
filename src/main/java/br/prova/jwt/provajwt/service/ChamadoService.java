package br.prova.jwt.provajwt.service;

import br.prova.jwt.provajwt.dto.chamado.ChamadoResponseDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoRequestDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoUpdateDTO;
import br.prova.jwt.provajwt.entity.ChamadoEntity;
import br.prova.jwt.provajwt.entity.UsuarioEntity;
import br.prova.jwt.provajwt.exceptions.ChamadoNotFound;
import br.prova.jwt.provajwt.exceptions.EmptyListException;
import br.prova.jwt.provajwt.exceptions.UsuarioNotFound;
import br.prova.jwt.provajwt.mapper.ChamadoMapper;
import br.prova.jwt.provajwt.repository.ChamadoRepository;
import br.prova.jwt.provajwt.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe onde ocorre a implementação das regras de negocio
 */

@Service
public class ChamadoService {

    private final ChamadoRepository repository;
    private final UsuarioRepository userRepository;
    private final ChamadoMapper mapper;

    public ChamadoService(ChamadoRepository repository, ChamadoMapper mapper,UsuarioRepository userRepository){
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    /**
     * Lista os chamados
     * @return List<ChamadoResponseDTO>
     */
    @Transactional
    public List<ChamadoResponseDTO> listarChamados(){
        if (repository.findAll().isEmpty()){
            throw new EmptyListException("erro - não há nenhum chamado");
        }

        List<ChamadoEntity> entities = repository.findAll();
        List<ChamadoResponseDTO> responses = mapper.toResponseList(entities);

        return responses;
    }

    /**
     * Deletar os chamados
     * @param id
     */
    @Transactional
    public void deletarChamado(Long id){
        ChamadoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ChamadoNotFound("erro - chamado não encontrado"));

        repository.deleteById(id);
    }

    /**
     * Abrir o chamado
     * @param requestDTO
     * @return
     */
    @Transactional
    public ChamadoResponseDTO abrirChamado(ChamadoRequestDTO requestDTO){
        UsuarioEntity cliente = userRepository.findById(requestDTO.clienteId())
                .orElseThrow(() -> new UsuarioNotFound("erro - usuario não encontrado"));

        UsuarioEntity tecnico = userRepository.findById(requestDTO.tecnicoId())
                .orElseThrow(() -> new UsuarioNotFound("erro - usuario não encontrado"));

        ChamadoEntity entity = mapper.toEntity(requestDTO,cliente,tecnico);
        ChamadoResponseDTO response = mapper.toResponse(entity);

        return response;
    }

    /**
     * Atualizar o chamado
     * @param id
     * @param updateDTO
     * @return
     */
    @Transactional
    public ChamadoResponseDTO atualizarChamado(Long id,ChamadoUpdateDTO updateDTO){
        ChamadoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ChamadoNotFound("erro - chamado não encontrado"));

        ChamadoEntity entityUpdated = mapper.toUpdate(entity,updateDTO);
        repository.save(entityUpdated);
        ChamadoResponseDTO response = mapper.toResponse(entityUpdated);

        return response;
    }
}
