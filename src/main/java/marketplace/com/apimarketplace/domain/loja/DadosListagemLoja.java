package marketplace.com.apimarketplace.domain.loja;

public record DadosListagemLoja(Long id, String nome, String email, String cnpj) {

    public DadosListagemLoja(Loja loja){

        this(loja.getId(), loja.getNome(), loja.getEmail(), loja.getCnpj());

    }


}
