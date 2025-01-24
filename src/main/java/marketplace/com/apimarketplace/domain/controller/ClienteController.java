package marketplace.com.apimarketplace.domain.controller;


import marketplace.com.apimarketplace.domain.cliente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping( "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCliente(@RequestBody DadosCadastroCliente dadosCadastro, UriComponentsBuilder uriBuilder){
        var cliente = new Cliente(dadosCadastro);
        repository.save(cliente);
        var uri = uriBuilder.path("/lojas/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosAtualizacaoTotalCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> listarCliente(){
        var list = repository.findAll().stream().map(DadosListagemCliente::new).toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarCliente(@RequestBody DadosAtualizadosCliente dadosAtualizadosCliente){
        var cliente = repository.getReferenceById(dadosAtualizadosCliente.id());
        cliente.atualizarCliente(dadosAtualizadosCliente);
        return ResponseEntity.ok(new DadosAtualizacaoTotalCliente(cliente));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirCliente(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharClient(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosAtualizacaoTotalCliente(cliente));
    }

}
