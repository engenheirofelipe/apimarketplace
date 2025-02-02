package marketplace.com.apimarketplace.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        String numero,
        String complemento,
        @NotBlank
        String bairro,
        String cidade,
        @NotBlank
        String uf,
        @Pattern(regexp = "\\d{8}")
        String cep) {

}
