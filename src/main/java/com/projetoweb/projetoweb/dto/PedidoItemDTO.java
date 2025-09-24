package com.projetoweb.projetoweb.dto;

import com.projetoweb.projetoweb.model.Produto;

import java.math.BigDecimal;

public class PedidoItemDTO {
    private String id;
    private String modelo;
    private Integer quantidade;
    private BigDecimal preco;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}