package br.senai.sc.editora.livro.repository;

import br.senai.sc.editora.livro.model.entities.Livro;
import br.senai.sc.editora.livro.model.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByStatus(Status status);

    List<Livro> findByAutor(Long cpfAutor);
}
