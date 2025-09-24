package com.projetoweb.projetoweb.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private Long id;
    private Long codCliente;
    private LocalDateTime dataCriacao;
    private BigDecimal total;
    private String status;
    private List<PedidoItemDTO> itens;

    //getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCodCliente() { return codCliente; }
    public void setCodCliente(Long codCliente) { this.codCliente = codCliente; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<PedidoItemDTO> getItens() { return itens; }
    public void setItens(List<PedidoItemDTO> itens) { this.itens = itens; }
}