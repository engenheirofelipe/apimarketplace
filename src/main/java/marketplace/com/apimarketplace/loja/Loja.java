package marketplace.com.apimarketplace.loja;

import jakarta.persistence.*;
import lombok.*;
import marketplace.com.apimarketplace.endereco.Endereco;

@Table(name = "lojas")
@Entity(name = "Loja")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Loja {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cnpj;
    private String nomeproprietario;

    @Enumerated(EnumType.STRING)
    private Nicho nicho;

    @Embedded
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter para cnpj
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    public Loja() {

    }

    public Loja(DadosCadastroLoja dadosCadastro) {
        this.nome = dadosCadastro.nome();
        this.email = dadosCadastro.email();
        this.telefone = dadosCadastro.telefone();
        this.cnpj = dadosCadastro.cnpj();
        this.nomeproprietario = dadosCadastro.nomeproprietario();
        this.nicho = dadosCadastro.nicho();
        this.endereco = new Endereco(dadosCadastro.endereco());
    }

    public void atualizarDados( DadosAtualizadosLoja dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if(dados.endereco() != null) {
            this.endereco.atualizarDados(dados.endereco());
        }

    }
}
