package com.projetoweb.projetoweb.service;

import com.projetoweb.projetoweb.dto.PedidoItemDTO;
import com.projetoweb.projetoweb.dto.PedidoRequestDTO;
import com.projetoweb.projetoweb.dto.PedidoResponseDTO;
import com.projetoweb.projetoweb.model.Pedido;
import com.projetoweb.projetoweb.model.Produto;
import com.projetoweb.projetoweb.model.StatusPedido;
import com.projetoweb.projetoweb.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EmailService emailService;

    public PedidoService(PedidoRepository pedidoRepository, EmailService emailService) {
        this.pedidoRepository = pedidoRepository;
        this.emailService = emailService;
    }

    @Transactional
    public PedidoResponseDTO createPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCodCliente());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.valueOf("Recebido"));

        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        if (dto.getItens() != null) {
            for (PedidoItemDTO itemDTO : dto.getItens()) {
                PedidoItemDTO item = new PedidoItemDTO();
                item.setId(itemDTO.getId());
                item.setModelo(itemDTO.getModelo());
                item.setQuantidade(itemDTO.getQuantidade());
                item.setPreco(itemDTO.getPreco());
                BigDecimal subtotal = item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
                item.setPreco(subtotal);
                total = valorTotal.add(subtotal);
            }
        }

        pedido.setValorTotal(total);
        Pedido salvo = pedidoRepository.save(pedido);
        //enviar e-mail de confirmação simplificada
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            String subject = "Confirmação do pedido #" + salvo.getCodPedido();
            String text = "Seu pedido foi recebido. Código do Pedido: " + salvo.getCodPedido() + "\nTotal:" + salvo.getValorTotal();
            emailService.sendSimpleMessage(dto.getEmail(), subject, text);
        }
        return toResponseDTO(salvo);
    }

    @Transactional
    public PedidoResponseDTO updatePedido(Long id, PedidoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
        pedido.setCliente(dto.getCodCliente());
        // substituir itens
        pedido.setProduto(null);
        BigDecimal valorTotal = BigDecimal.ZERO;
        if (dto.getItens() != null) {
            for (PedidoItemDTO itemDTO : dto.getItens()) {
                PedidoItemDTO item = new PedidoItemDTO();
                item.setId(itemDTO.getId());
                item.setModelo(itemDTO.getModelo());
                item.setQuantidade(itemDTO.getQuantidade());
                item.setPreco(itemDTO.getPreco());
                BigDecimal subtotal = item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
                item.setPreco(subtotal);
                valorTotal = valorTotal.add(subtotal);
            }
        }
        pedido.setValorTotal(valorTotal);
        Pedido salvo = pedidoRepository.save(pedido);
        return toResponseDTO(salvo);
    }
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public PedidoResponseDTO buscarPedido(Long id) {
        Pedido p = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
        return toResponseDTO(p);
    }

    public List<PedidoResponseDTO> buscarCliente(Long codCliente) {
        return pedidoRepository.findBycodCliente(codCliente).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
    private PedidoResponseDTO toResponseDTO(Pedido p) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(p.getCodPedido());
        dto.setCodCliente(p.getCliente().getCodCliente());
        dto.setDataCriacao(p.getDataPedido());
        dto.setTotal(p.getValorTotal());
        dto.setStatus(String.valueOf(p.getStatus()));
        dto.setItens(p.getProduto().stream().map(item -> {
            PedidoItemDTO i = new PedidoItemDTO();
            i.setId(i.getId());
            i.setModelo(i.getModelo());
            i.setQuantidade(i.getQuantidade());
            i.setPreco(i.getPreco());
            return i;
        }).collect(Collectors.toList()));
        return dto;
    }
}