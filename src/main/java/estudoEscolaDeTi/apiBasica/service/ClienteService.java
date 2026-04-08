package estudoEscolaDeTi.apiBasica.service;

import estudoEscolaDeTi.apiBasica.dtos.cliente.ClienteRequest;
import estudoEscolaDeTi.apiBasica.dtos.cliente.ClienteResponse;
import estudoEscolaDeTi.apiBasica.dtos.produto.ProdutoResponse;
import estudoEscolaDeTi.apiBasica.entity.Cliente;
import estudoEscolaDeTi.apiBasica.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponse criar(ClienteRequest request) {
        LocalDateTime agora = LocalDateTime.now();

        Cliente cliente = Cliente.builder()
                .id(UUID.randomUUID())
                .nome(request.getNome())
                .email(request.getEmail())
                .createdAt(agora)
                .updatedAt(agora)
                .build();

        Cliente salvo = clienteRepository.save(cliente);

        return toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorId(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return toResponse(cliente);
    }

    private ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .produtos(
                        cliente.getProdutos().stream()
                                .map(produto -> ProdutoResponse.builder()
                                        .id(produto.getId())
                                        .nome(produto.getNome())
                                        .preco(produto.getPreco())
                                        .details(produto.getDetails())
                                        .createdAt(produto.getCreatedAt())
                                        .updatedAt(produto.getUpdatedAt())
                                        .build())
                                .toList()
                )
                .createdAt(cliente.getCreatedAt())
                .updatedAt(cliente.getUpdatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
}
