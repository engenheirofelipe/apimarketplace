package marketplace.com.apimarketplace.domain.loja;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import marketplace.com.apimarketplace.endereco.DadosEndereco;

public record DadosCadastroLoja(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String nomeproprietario,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String cnpj,
        @NotNull
        Nicho nicho,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
