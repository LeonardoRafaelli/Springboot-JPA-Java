package br.senai.sc.editora.livro.controller;

import br.senai.sc.editora.livro.dto.PessoaDTO;
import br.senai.sc.editora.livro.model.entities.Pessoa;
import br.senai.sc.editora.livro.model.service.PessoaService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/editoralivros/pessoa")
public class PessoaController {
    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.ok(pessoaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PessoaDTO pessoaDTO) {

        Optional<Pessoa> optionalPerson = pessoaService.findById(pessoaDTO.getCPF());

        if (pessoaService.existsById(pessoaDTO.getCPF())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Pessoa já cadastrada");
        }

        if (pessoaService.existByEmail(pessoaDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
        }

        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDTO, pessoa);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Object> findById(@PathVariable(value = "cpf") Long cpf) {
        Optional<Pessoa> pessoa = pessoaService.findById(cpf);
        if (pessoa.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pessoa.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "cpf") Long cpf) {
        Optional<Pessoa> pessoa = pessoaService.findById(cpf);
        if(!pessoaService.existsById(cpf)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pessoaService.deleteById(cpf);
        return ResponseEntity.ok("Pessoa deletada com sucesso!");
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value = "email") String email) {
        Optional<Pessoa> pessoa = pessoaService.findByEmail(email);

        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma pessoa com o email informado");
        }
        return ResponseEntity.ok(pessoa.get());
    }
}
