package br.senai.sc.editora.livro.security;

import br.senai.sc.editora.livro.model.entities.Pessoa;
import br.senai.sc.editora.livro.repository.PessoaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
@Service
public class AutenticacaoService implements UserDetailsService {


    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findByEmail(username);
        System.out.println(pessoaOptional.get());
        if(pessoaOptional.isPresent()){}
        return pessoaOptional.get();
    }
}
