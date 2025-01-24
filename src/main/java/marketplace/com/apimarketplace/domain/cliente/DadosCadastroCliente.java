package marketplace.com.apimarketplace.domain.cliente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import marketplace.com.apimarketplace.endereco.DadosEndereco;

public record DadosCadastroCliente(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String cpf,
        @NotBlank
        String telefone,
        @NotNull
        @Valid
        DadosEndereco endereco ) {
}
