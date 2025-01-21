package marketplace.com.apimarketplace.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import marketplace.com.apimarketplace.endereco.Endereco;

@Table(name = "clientes")
@Entity(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cliente(){

    }

    public Cliente(DadosCadastroCliente dadosCadastro){
        this.nome = dadosCadastro.nome();
        this.email = dadosCadastro.email();
        this.cpf = dadosCadastro.cpf();
        this.telefone = dadosCadastro.telefone();
        this.endereco = new Endereco(dadosCadastro.endereco());

    }

    public void atualizarCliente(DadosAtualizadosCliente dadosAtualizadosCliente) {
        if(dadosAtualizadosCliente.nome() != null) {
            this.nome = dadosAtualizadosCliente.nome();
        }
        if(dadosAtualizadosCliente.email() != null) {
            this.email = dadosAtualizadosCliente.email();
        }
        if(dadosAtualizadosCliente.telefone() != null) {
            this.telefone = dadosAtualizadosCliente.telefone();
        }
        if(dadosAtualizadosCliente.cpf() != null) {
            this.cpf = dadosAtualizadosCliente.cpf();
        }

    }



}
