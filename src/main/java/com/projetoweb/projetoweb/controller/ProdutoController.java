package com.projetoweb.projetoweb.controller;

import com.projetoweb.projetoweb.model.Produto;
import com.projetoweb.projetoweb.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    //POST
    @PostMapping
    public ResponseEntity<Produto> gravar(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.salvarProduto(produto));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable Long id, @RequestBody Produto produto){
        Produto atualizado = produtoService.alterarProduto(id, produto);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        boolean removido = produtoService.deletarProduto(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    //GET buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarCodigoProduto(@PathVariable Long id){
        return produtoService.buscarCodigoProduto(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //GET listar todos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos(){
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    //GET vitrine
    @GetMapping("/vitrine")
    public ResponseEntity<List<Produto>> vitrine(){
        return ResponseEntity.ok(produtoService.vitrine());
    }

    //GET Busca por keyword
    @GetMapping("/busca")
    public ResponseEntity<List<Produto>> buscarPorTermo(@RequestParam String termo){
        return ResponseEntity.ok(produtoService.buscarPorTermo(termo));
    }
}
