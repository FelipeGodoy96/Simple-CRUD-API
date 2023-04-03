package com.academic.Simple.CRUD.API.view.controller;

import com.academic.Simple.CRUD.API.model.Produto;
import com.academic.Simple.CRUD.API.service.ProdutoService;
import com.academic.Simple.CRUD.API.shared.ProdutoDTO;
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
    public List<ProdutoDTO> obterProdutos() {
        return produtoService.obterTodosProdutos();
    }

    @PostMapping
    public ProdutoDTO cadastrarProduto(@RequestBody ProdutoDTO produtoDto) {
        return produtoService.adicionarProduto(produtoDto);
    }

    @GetMapping("/{id}")
    public Optional<ProdutoDTO> obterProdutoPorId(@PathVariable Integer id) {
        return produtoService.obterProdutoPorId(id);
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return "Produto de ID " + id + " foi deletado com sucesso.";
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDto) {
        return produtoService.atualizarProduto(id, produtoDto);
    }

}
