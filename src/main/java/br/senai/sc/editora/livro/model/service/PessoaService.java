package br.senai.sc.editora.livro.model.service;

import br.senai.sc.editora.livro.model.entities.Pessoa;
import br.senai.sc.editora.livro.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public <S extends Pessoa> S save(S entity) {
        return pessoaRepository.save(entity);
    }

    public Optional<Pessoa> findById(Long aLong) {
        return pessoaRepository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        pessoaRepository.deleteById(aLong);
    }


    public Optional<Pessoa> findByEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }

    public boolean existsById(Long cpf) {
        return pessoaRepository.existsById(cpf);
    }

    public boolean existByEmail(String email) {
        return pessoaRepository.existsByEmail(email);
    }
}
