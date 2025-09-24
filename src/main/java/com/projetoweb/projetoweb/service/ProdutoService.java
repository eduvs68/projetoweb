package com.projetoweb.projetoweb.service;

import com.projetoweb.projetoweb.model.Produto;
import com.projetoweb.projetoweb.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    //gravar
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    //alterar
    public Produto alterarProduto(Long id, Produto produtoDetalhes) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não Encontramos o produto"+id));

        produto.setCategoria(produtoDetalhes.getCategoria());
        produto.setMarca(produtoDetalhes.getMarca());
        produto.setModelo(produtoDetalhes.getModelo());
        produto.setCor(produtoDetalhes.getCor());
        produto.setPreco(produtoDetalhes.getPreco());

        return produtoRepository.save(produto);
    }

    //deletar
    public boolean deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //buscar por id
    public Optional<Produto> buscarCodigoProduto(Long id) {
        return Optional.ofNullable(produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontramos o produto" + id)));
    }

    //listar
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    //vitrine
    public List<Produto> vitrine(){
        return produtoRepository.findAll().stream().filter(p -> p.getPreco() != null && p.getPreco() > 700).toList();
    }

    //buscar por termo
    public List<Produto> buscarPorTermo(String keyword){

        return produtoRepository.findByKeyword(keyword);
    }


}
