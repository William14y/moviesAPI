package br.com.modulo4.javapro4.controllers;


import br.com.modulo4.javapro4.entities.Filmes;
import br.com.modulo4.javapro4.services.IFilmesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("filmes")
public class ApiFilmesController {

    private final List<Filmes> filmeList;

    @Autowired
    private final IFilmesService iFilmesService;

    public ApiFilmesController(IFilmesService filmesService) {
        this.iFilmesService = filmesService;
        this.filmeList = new ArrayList<>();
    }

    @GetMapping
    public List<Filmes> procuraFilme(@RequestParam(required = false) String nome, String diretor) {
        if (nome != null) {
            return iFilmesService.procurarNomeFilme(nome);
        } else if (diretor != null) {
            return iFilmesService.diretorFilme(diretor);
        }
        return iFilmesService.procuraFilme();
    }

    @PostMapping
    public ResponseEntity<String> validaFilme(@RequestBody final Filmes filme) {
        iFilmesService.validaFilme(filme);
        final String criadoComSucesso = "O filme foi criado com sucesso!!";
        return new ResponseEntity<String>(criadoComSucesso, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> editFilme(@RequestBody final Filmes filme){
        iFilmesService.editFilme(filme);
        final String editadoComSucesso = "O filme foi editado com sucesso!!";
        return new ResponseEntity<String>(editadoComSucesso, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFilme(@PathVariable("id") Integer id){
        iFilmesService.deleteFilme(id);
        final String deletadoComSucesso = "O filme foi deletado com sucesso!!";
        return new ResponseEntity<String>(deletadoComSucesso, HttpStatus.OK);
    }
}