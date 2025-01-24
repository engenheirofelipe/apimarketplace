package marketplace.com.apimarketplace.domain.loja;

public record DadosAtualizacaoTotalLoja(Long id, String nome, String email, String nomeproprietario, String cnpj, String telefone) {

    public DadosAtualizacaoTotalLoja(Loja loja) {
        this(loja.getId(), loja.getNome(), loja.getEmail(), loja.getNomeproprietario(), loja.getCnpj(), loja.getTelefone());
    }
}
