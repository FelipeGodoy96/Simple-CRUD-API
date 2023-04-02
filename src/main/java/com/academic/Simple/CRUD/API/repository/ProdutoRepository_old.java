package com.academic.Simple.CRUD.API.repository;

import com.academic.Simple.CRUD.API.model.Produto;
import com.academic.Simple.CRUD.API.model.exception.BadRequestException;
import com.academic.Simple.CRUD.API.model.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository_old {


    // ArrayList simulando um banco de dados
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    // Variável para auto_increment simulando do banco de dados
    private Integer ultimoId = 0;

    /**
     * Método que retorna uma lista de produtos.
     * @return lista com todos os produtos.
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Método que retorna um produto específico por seu ID.
     * @param id do produto que está buscando.
     * @return produto, caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id) {
        Optional<Produto> produtoEncontrado = produtos.stream().filter(produto -> produto.getId() == id).findFirst();
        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto ID " + id + " não foi encontrado.");
        }
        return produtoEncontrado;
    }

    /**
     * Método para adicionar um produto na lista.
     * @param produto que será adicionado.
     * @return o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto) {
        produto.setId(ultimoId);
        if (produto.getNome() == null) {
            throw new BadRequestException("O campo 'nome' não pode ser vazio");
        }
        produtos.add(produto);
        ultimoId++;
        return produto;
    }

    /**
     * Método que remove um produto da lista por seu ID.
     * @param id do produto que deseja remover.
     */
    public void deletar(Integer id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Método que atualiza um produto na lista se for existente.
     * @param produto que será atualizado.
     * @return produto após atualizar a lista.
     */
    public Produto atualizar(Produto produto) {

        // Encontrar um produto na lista
        // Se não encontrar retorna uma exceção
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto ID " + produto.getId() + " não encontrado");
        }

        // Produto encontrado: deleta o produto antigo
        deletar(produto.getId());

        // Produto antigo deletado: adiciona o produto "novo" (atualizado) mantendo o ID
        produtos.add(produto);

        // Retorna o produto que foi atualizado
        return produto;
    }
}
