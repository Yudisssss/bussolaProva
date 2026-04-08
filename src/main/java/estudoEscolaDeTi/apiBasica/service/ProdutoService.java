package estudoEscolaDeTi.apiBasica.service;

import estudoEscolaDeTi.apiBasica.dtos.produto.ProdutoRequest;
import estudoEscolaDeTi.apiBasica.dtos.produto.ProdutoResponse;
import estudoEscolaDeTi.apiBasica.entity.Cliente;
import estudoEscolaDeTi.apiBasica.entity.Produto;
import estudoEscolaDeTi.apiBasica.repository.ClienteRepository;
import estudoEscolaDeTi.apiBasica.repository.ProdutoRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        LocalDateTime agora = LocalDateTime.now();

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome(request.getNome())
                .preco(request.getPreco())
                .details(request.getDetails())
                .cliente(cliente)
                .createdAt(agora)
                .updatedAt(agora)
                .build();

        Produto salvo = produtoRepository.save(produto);

        return toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorId(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return toResponse(produto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listarPorCliente(UUID clienteId) {
        return produtoRepository.findByClienteId(clienteId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ProdutoResponse atualizar(UUID id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        produto.setNome(request.getNome());
        produto.setPreco(request.getPreco());
        produto.setDetails(request.getDetails());
        produto.setCliente(cliente);
        produto.setUpdatedAt(LocalDateTime.now());

        Produto atualizado = produtoRepository.save(produto);

        return toResponse(atualizado);
    }

    @Transactional
    public void deletar(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }

    private ProdutoResponse toResponse(Produto produto) {
        return ProdutoResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .preco(produto.getPreco())
                .details(produto.getDetails())
                .createdAt(produto.getCreatedAt())
                .updatedAt(produto.getUpdatedAt())
                .build();
    }
}
