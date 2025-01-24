package marketplace.com.apimarketplace.domain.cliente;

public record DadosListagemCliente(Long id, String nome, String email, String cpf, String telefone) {

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getTelefone());
    }

}
