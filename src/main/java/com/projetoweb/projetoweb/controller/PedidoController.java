package controller;

import com.projetoweb.projetoweb.dto.PedidoRequestDTO;
import com.projetoweb.projetoweb.dto.PedidoResponseDTO;
import com.projetoweb.projetoweb.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> createPedido(@RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO criado = pedidoService.createPedido(dto);
        return ResponseEntity.ok(criado);
    }

    @PutMapping
    public ResponseEntity<PedidoResponseDTO> updatePedido(@PathVariable long id, @RequestBody PedidoRequestDTO dto)  {
        PedidoResponseDTO atualizado = pedidoService.updatePedido(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping
    public ResponseEntity<PedidoResponseDTO> deletePedido(@PathVariable long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getPedido(@PathVariable long id) {
        return ResponseEntity.ok(pedidoService.buscarPedido(id));
    }

    @GetMapping("/cliente/{codCliente}")
    public ResponseEntity<PedidoResponseDTO> listarPorCliente(@PathVariable long codCliente) {
        return ResponseEntity.ok((PedidoResponseDTO) pedidoService.buscarCliente(codCliente));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}