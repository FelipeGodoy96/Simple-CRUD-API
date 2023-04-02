package com.academic.Simple.CRUD.API.view.controller;

import com.academic.Simple.CRUD.API.model.Produto;
import com.academic.Simple.CRUD.API.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> obterProdutos() {
        return produtoService.obterTodosProdutos();
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoService.adicionarProduto(produto);
    }

    @GetMapping("/{id}")
    public Optional<Produto> obterProdutoPorId(@PathVariable Integer id) {
        return produtoService.obterProdutoPorId(id);
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return "Produto com ID " + id + " foi deletado com sucesso.";
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        return produtoService.atualizarProduto(id, produto);
    }

}
