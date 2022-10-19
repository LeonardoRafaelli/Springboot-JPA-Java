package br.senai.sc.editora.livro.controller;

import br.senai.sc.editora.livro.dto.LivroDTO;
import br.senai.sc.editora.livro.model.entities.Livro;
import br.senai.sc.editora.livro.model.entities.Status;
import br.senai.sc.editora.livro.model.service.LivroService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/editoralivros/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Object> save (@RequestBody @Valid LivroDTO livroDto) {

        System.out.println("Post called");
        System.out.println(livroDto.toString());

        if(livroService.existsById(livroDto.getIsbn())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ISBN já cadastrado!");
        }

        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDto, livro);
        livro.setStatus(Status.AGUARDANDO_REVISAO);

        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livro));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll(){
        return ResponseEntity.ok(livroService.findAll());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Object> findById(@PathVariable(value = "isbn") Long isbn){
        Optional<Livro> livro = livroService.findById(isbn);
        if(livro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado!");
        }
        return ResponseEntity.ok(livro.get());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Livro>> findByStatus (@PathVariable (value = "status") Status status){
        return ResponseEntity.status(HttpStatus.FOUND).body(
                livroService.findByStatus(status)
        );
    }

    @GetMapping("/autor/{cpfAutor}")
    public ResponseEntity<List<Livro>> findByAutor (@PathVariable (value = "cpfAutor") Long cpfAutor){
        return ResponseEntity.status(HttpStatus.FOUND).body(
                livroService.findByAutor(cpfAutor)
        );
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "isbn") Long isbn){

        if(!livroService.existsById(isbn)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado!");
        }

        return ResponseEntity.ok("Livro com ISBN: " + isbn + " deletado com sucesso!");
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid LivroDTO livroDto){
        if(!livroService.existsById(livroDto.getIsbn())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado!");
        }


        Livro livro = livroService.findById(livroDto.getIsbn()).get();
        BeanUtils.copyProperties(livroDto, livro);

        return ResponseEntity.ok(livro);
    }

}
