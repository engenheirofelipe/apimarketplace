package marketplace.com.apimarketplace.domain.cliente;

public record DadosAtualizacaoTotalCliente(Long id, String nome, String email, String cpf, String telefone) {

    public DadosAtualizacaoTotalCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getTelefone());
    }
}
