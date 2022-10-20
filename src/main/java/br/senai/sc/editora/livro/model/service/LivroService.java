package br.senai.sc.editora.livro.model.service;

import br.senai.sc.editora.livro.model.entities.Autor;
import br.senai.sc.editora.livro.model.entities.Livro;
import br.senai.sc.editora.livro.model.entities.Status;
import br.senai.sc.editora.livro.repository.LivroRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro save(Livro livro){
        return livroRepository.save(livro);
    }

    public Optional<Livro> findById(Long isbn) {
        return livroRepository.findById(isbn);
    }

    public List<Livro> findByStatus(Status status) {
        return livroRepository.findByStatus(status);
    }

    public List<Livro> findByAutor(Autor autor) {
        return livroRepository.findByAutores(autor);
    }

    public boolean existsById(Long isbn) {
        return livroRepository.existsById(isbn);
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }
}
