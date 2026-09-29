package br.prova.jwt.provajwt.service;


import br.prova.jwt.provajwt.exceptions.UsuarioNotFound;
import br.prova.jwt.provajwt.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository repository;

    public AutenticacaoService(UsuarioRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByNome(username)
                .orElseThrow(() -> new UsuarioNotFound("erro - usuario não encontrado"));
    }
}
