package com.academic.Simple.CRUD.API.controller;

import com.academic.Simple.CRUD.API.model.Produto;
import com.academic.Simple.CRUD.API.service.ProdutoService_old;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController_old {

    @Autowired
    private ProdutoService_old produtoServiceOld;

    @GetMapping
    public List<Produto> obterProdutos() {
        return produtoServiceOld.obterTodosProdutos();
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoServiceOld.adicionarProduto(produto);
    }

    @GetMapping("/{id}")
    public Optional<Produto> obterProdutoPorId(@PathVariable Integer id) {
        return produtoServiceOld.obterProdutoPorId(id);
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Integer id) {
        produtoServiceOld.deletarProduto(id);
        return "Produto com ID " + id + " foi deletado com sucesso.";
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        return produtoServiceOld.atualizarProduto(id, produto);
    }

}