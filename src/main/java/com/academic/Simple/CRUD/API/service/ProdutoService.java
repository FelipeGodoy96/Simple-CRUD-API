package com.academic.Simple.CRUD.API.service;

import com.academic.Simple.CRUD.API.model.Produto;
import com.academic.Simple.CRUD.API.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService  {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para receber todos os produtos.
     * @return todos os produtos da lista.
     */
    public List<Produto> obterTodosProdutos() {
        return produtoRepository.obterTodos();
    }

    /**
     * Método que retorna um produto por seu ID.
     * @param id do produto buscado.
     * @return um produto.
     */
    public Optional<Produto> obterProdutoPorId(Integer id) {
        return produtoRepository.obterPorId(id);
    }

    /**
     * Método que adiciona um produto na lista
     * @param produto a ser adicionado.
     * @return o produto após ser adicionado na lista.
     */
    public Produto adicionarProduto(Produto produto) {
        // Validação de regra de negócio
        return produtoRepository.adicionar(produto);
    }

    /**
     * Deleta um produto da lista.
     * @param id do produto a ser deletado.
     */
    public void deletarProduto(Integer id) {
        produtoRepository.deletar(id);
    }

    /**
     * Método que atualiza um produto da lista.
     * @param id do produto a ser atualizado
     * @param produto a ser atualizado
     * @return produto após ser atualizado na lista.
     */
    public Produto atualizarProduto(Integer id, Produto produto) {
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }
}
