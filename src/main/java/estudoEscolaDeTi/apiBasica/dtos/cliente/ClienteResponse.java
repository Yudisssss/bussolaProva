package estudoEscolaDeTi.apiBasica.dtos.cliente;
import estudoEscolaDeTi.apiBasica.dtos.produto.ProdutoResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {

    private UUID id;
    private String nome;
    private String email;
    private List<ProdutoResponse> produtos;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}