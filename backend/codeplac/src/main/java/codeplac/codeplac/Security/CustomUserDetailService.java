package codeplac.codeplac.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Repository.UsersRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    private final UsersRepository repository;

    public CustomUserDetailService(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        logger.debug("Tentando carregar usuário com matrícula: {}", matricula);

        // Tenta encontrar o usuário pela matrícula
        UsersModel user = repository.findByMatricula(matricula)
                .orElseThrow(() -> {
                    logger.warn("Usuário não encontrado com matrícula: {}", matricula);
                    return new UsernameNotFoundException("Usuário não encontrado com matrícula: " + matricula);
                });

        logger.debug("Usuário encontrado: {}", user.getMatricula());

        // Retorna o UserDetails com a senha e as roles/authorities
        return new org.springframework.security.core.userdetails.User(
                user.getMatricula(),
                user.getSenha(),
                user.getTipoUsuario().getAuthorities() // Assumindo que tipoUser tem authorities mapeadas
        );
    }
}