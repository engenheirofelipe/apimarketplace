package marketplace.com.apimarketplace.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(){

    }

    public Endereco(DadosEndereco dadosCadastro) {
        this.logradouro = dadosCadastro.logradouro();
        this.numero = dadosCadastro.numero();
        this.complemento = dadosCadastro.complemento();
        this.bairro = dadosCadastro.bairro();
        this.cidade = dadosCadastro.cidade();
        this.uf = dadosCadastro.uf();
        this.cep = dadosCadastro.cep();
    }


    public void atualizarDados(DadosEndereco dados) {
        if(dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }

        if(dados.numero() != null) {
            this.numero = dados.numero();
        }

        if(dados.complemento() != null) {
            this.complemento = dados.complemento();
        }

        if(dados.bairro() != null) {
            this.bairro = dados.bairro();
        }

        if(dados.cidade() != null) {
            this.cidade = dados.cidade();
        }

        if(dados.uf() != null) {
            this.uf = dados.uf();
        }

        if(dados.cep() != null) {
            this.cep = dados.cep();
        }
    }
}
