package com.academic.Simple.CRUD.API.view.controller;

import com.academic.Simple.CRUD.API.model.Produto;
import com.academic.Simple.CRUD.API.service.ProdutoService;
import com.academic.Simple.CRUD.API.shared.ProdutoDTO;
import com.academic.Simple.CRUD.API.view.model.ProdutoRequest;
import com.academic.Simple.CRUD.API.view.model.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterProdutos() {
        List<ProdutoDTO> produtos = produtoService.obterTodosProdutos();
        List<ProdutoResponse> resposta = produtos.stream().map(produtoDto -> new ModelMapper().map(produtoDto, ProdutoResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoReq) {
        ProdutoDTO produtoDto = new ModelMapper().map(produtoReq, ProdutoDTO.class);
        produtoDto = produtoService.adicionarProduto(produtoDto);
        ProdutoResponse resposta = new ModelMapper().map(produtoDto, ProdutoResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterProdutoPorId(@PathVariable Integer id) {
        Optional<ProdutoDTO> dto = produtoService.obterProdutoPorId(id);
        ProdutoResponse resposta = new ModelMapper().map(dto.get(), ProdutoResponse.class);
        return new ResponseEntity<>(Optional.of(resposta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
//        String mensagem = "Produto de ID " + id + " foi deletado com sucesso.";
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoRequest produtoReq) {
        ProdutoDTO produtoDto = new ModelMapper().map(produtoReq, ProdutoDTO.class);
        produtoDto = produtoService.atualizarProduto(id, produtoDto);
        ProdutoResponse resposta = new ModelMapper().map(produtoDto, ProdutoResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
