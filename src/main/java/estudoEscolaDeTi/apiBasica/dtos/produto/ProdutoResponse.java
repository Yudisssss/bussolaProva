package estudoEscolaDeTi.apiBasica.dtos.produto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoResponse {

    private UUID id;
    private String nome;
    private BigDecimal preco;
    private String details;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}