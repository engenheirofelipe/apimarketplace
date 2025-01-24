package marketplace.com.apimarketplace.domain.controller;

import jakarta.validation.Valid;
import marketplace.com.apimarketplace.domain.loja.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarLoja(@RequestBody @Valid DadosCadastroLoja dadosCadastro, UriComponentsBuilder uriBuilder) {
        var loja = new Loja(dadosCadastro);
        repository.save(loja);
        var uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosAtualizacaoTotalLoja(loja));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemLoja>> listar(Pageable paginacao){
       var page = repository.findAll(paginacao).map(DadosListagemLoja::new);
       return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarLoja(@RequestBody @Valid DadosAtualizadosLoja dados){
        var loja = repository.getReferenceById(dados.id());
        loja.atualizarDados(dados);
        return ResponseEntity.ok(new DadosAtualizacaoTotalLoja(loja));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirLoja(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharLoja(@PathVariable Long id){
        var loja = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosAtualizacaoTotalLoja(loja));
    }

}
