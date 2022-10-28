package br.senai.sc.editora.livro.controller;

import br.senai.sc.editora.livro.dto.LivroDTO;
import br.senai.sc.editora.livro.model.entities.Autor;
import br.senai.sc.editora.livro.model.entities.Livro;
import br.senai.sc.editora.livro.model.entities.Pessoa;
import br.senai.sc.editora.livro.model.entities.Status;
import br.senai.sc.editora.livro.model.factory.PessoaFactory;
import br.senai.sc.editora.livro.model.service.LivroService;
import br.senai.sc.editora.livro.model.service.PessoaService;
import br.senai.sc.editora.livro.model.utils.LivroUtil;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/editoralivros/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Object> save (
            @RequestParam("livro") @Valid String livroJson,
            @RequestParam("arquivo") MultipartFile files
    ) {

        LivroUtil util = new LivroUtil();

        Livro livro = util.convertJsonToModel(livroJson);


        if(livroService.existsById(livro.getIsbn())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ISBN já cadastrado!");
        }

        for(int i = 0; i < livro.getAutores().size(); i++){
            if(!pessoaService.existsById(livro.getAutores().get(i).getCpf())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor com CPF: " + livro.getAutores().get(i).getCpf() + " não encontrado!");
            }
        }
        livro.setFile(files);
        livro.setStatus(Status.AGUARDANDO_REVISAO);

        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livro));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll(){
        return ResponseEntity.ok(livroService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Livro>> findAllPage(@PageableDefault(size = 9, sort = "isbn", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(livroService.findAll(pageable));
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
    public ResponseEntity<List<Livro>> findByAutor (@PathVariable (value = "cpfAutor") Autor autor){

        return ResponseEntity.status(HttpStatus.FOUND).body(
                livroService.findByAutor(autor)
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
