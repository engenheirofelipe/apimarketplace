package marketplace.com.apimarketplace.controller;

import jakarta.validation.Valid;
import marketplace.com.apimarketplace.loja.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarLoja( @RequestBody @Valid DadosCadastroLoja loja) {
        repository.save(new Loja(loja));
    }

    @GetMapping
    public Page<DadosListagemLoja> listar( Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemLoja::new);
    }

    @PutMapping
    @Transactional
    public void atualizarLoja(@RequestBody @Valid DadosAtualizadosLoja dados){
        var loja = repository.getReferenceById(dados.id());
        loja.atualizarDados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirLoja(@PathVariable long id){
        repository.deleteById(id);
    }



}
