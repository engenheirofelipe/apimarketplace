package marketplace.com.apimarketplace.loja;

import marketplace.com.apimarketplace.endereco.DadosEndereco;

public record DadosAtualizadosLoja(Long id, String nome, String telefone, DadosEndereco endereco) {

}
