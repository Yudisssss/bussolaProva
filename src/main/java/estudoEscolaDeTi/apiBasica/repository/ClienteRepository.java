package estudoEscolaDeTi.apiBasica.repository;

import estudoEscolaDeTi.apiBasica.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
