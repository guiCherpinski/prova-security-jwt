package br.prova.jwt.provajwt.security;

import br.prova.jwt.provajwt.exceptions.UsuarioNotFound;
import br.prova.jwt.provajwt.repository.UsuarioRepository;
import br.prova.jwt.provajwt.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class SecurityFilter extends OncePerRequestFilter {

    private final AuthenticationManager manager;
    private final TokenService service;
    private final UsuarioRepository repository;

    public SecurityFilter(TokenService service, AuthenticationManager manager,UsuarioRepository repository){
        this.service = service;
        this.manager = manager;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = buscarToken(request);

        if (token != null){
            var auth = service.getSubject(token);

            if (auth != null){
                var user = repository.findByNome(auth)
                        .orElseThrow(() -> new UsuarioNotFound("erro - usuario não encontrado"));

                if (user != null){
                    var autenticacao = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }
            }
        }

        filterChain.doFilter(request,response);
    }


    public String buscarToken(HttpServletRequest request){
        var token = request.getHeader("authorization");

        if (token == null){
            return null;
        }

        return null;
    }
}
