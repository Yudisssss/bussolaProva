package estudoEscolaDeTi.apiBasica.dtos.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;
}
