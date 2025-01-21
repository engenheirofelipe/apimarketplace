package marketplace.com.apimarketplace.controller;

import marketplace.com.apimarketplace.cliente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarCliente(@RequestBody DadosCadastroCliente dadosCadastro){
        repository.save(new Cliente(dadosCadastro));
    }

    @GetMapping
    public List<DadosListagemCliente> listarCliente(){
        return repository.findAll().stream().map(DadosListagemCliente::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarCliente(@RequestBody DadosAtualizadosCliente dadosAtualizadosCliente){
        var cliente = repository.getReferenceById(dadosAtualizadosCliente.id());
        cliente.atualizarCliente(dadosAtualizadosCliente);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluirCliente(@PathVariable long id){
        repository.deleteById(id);
    }

}
