package com.projetoweb.projetoweb.service;

import com.projetoweb.projetoweb.model.Produto;
import com.projetoweb.projetoweb.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    //gravar
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }



}
