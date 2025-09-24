package com.projetoweb.projetoweb.dto;

import com.projetoweb.projetoweb.model.Cliente;

import java.util.List;

public class PedidoRequestDTO {
    private Cliente codCliente;
    private List<PedidoItemDTO> itens;
    private String email;

    //getters e setters
    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }

    public List<PedidoItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemDTO> itens) {
        this.itens = itens;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}