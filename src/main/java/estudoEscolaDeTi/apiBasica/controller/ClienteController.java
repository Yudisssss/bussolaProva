package estudoEscolaDeTi.apiBasica.controller;

import estudoEscolaDeTi.apiBasica.dtos.cliente.ClienteRequest;
import estudoEscolaDeTi.apiBasica.dtos.cliente.ClienteResponse;
import estudoEscolaDeTi.apiBasica.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ClienteResponse criar(@RequestBody @Valid ClienteRequest request) {
        return clienteService.criar(request);
    }

    @GetMapping("/{id}")
    public ClienteResponse buscarPorId(@PathVariable UUID id) {
        return clienteService.buscarPorId(id);
    }

    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listarTodos();
    }
}
