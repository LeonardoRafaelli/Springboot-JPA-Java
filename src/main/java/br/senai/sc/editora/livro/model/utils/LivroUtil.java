package br.senai.sc.editora.livro.model.utils;

import br.senai.sc.editora.livro.dto.LivroDTO;
import br.senai.sc.editora.livro.model.entities.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.io.DataInput;

@NoArgsConstructor
public class LivroUtil {


    private ObjectMapper objectMapper = new ObjectMapper();

    public Livro convertJsonToModel (String livroJson) {
        try {
            LivroDTO livroDto = convertToDTO(livroJson);
            return convertDtoToModel(livroDto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter o livroJson para objeto Livro") {
            };
        }
    }

    public LivroDTO convertToDTO (String  livroJson) {
        try{
            return this.objectMapper.readValue(livroJson, LivroDTO.class);
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public Livro convertDtoToModel(LivroDTO livroDto){
        return this.objectMapper.convertValue(livroDto, Livro.class);
    }

}
