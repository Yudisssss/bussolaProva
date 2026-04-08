package estudoEscolaDeTi.apiBasica.controller;

import estudoEscolaDeTi.apiBasica.dtos.produto.ProdutoRequest;
import estudoEscolaDeTi.apiBasica.dtos.produto.ProdutoResponse;
import estudoEscolaDeTi.apiBasica.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ProdutoResponse criar(@RequestBody @Valid ProdutoRequest request) {
        return produtoService.criar(request);
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable UUID id) {
        return produtoService.buscarPorId(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<ProdutoResponse> listarPorCliente(@PathVariable UUID clienteId) {
        return produtoService.listarPorCliente(clienteId);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid ProdutoRequest request
    ) {
        return produtoService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        produtoService.deletar(id);
    }
}
