package com.academic.Simple.CRUD.API.service;

import com.academic.Simple.CRUD.API.model.Produto;
import com.academic.Simple.CRUD.API.model.exception.ResourceNotFoundException;
import com.academic.Simple.CRUD.API.repository.ProdutoRepository;
import com.academic.Simple.CRUD.API.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para receber todos os produtos.
     * @return todos os produtos da lista.
     */
    public List<ProdutoDTO> obterTodosProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class)).collect(Collectors.toList());
    }

    /**
     * Método que retorna um produto por seu ID.
     * @param id do produto buscado.
     * @return um produto.
     */
    public Optional<ProdutoDTO> obterProdutoPorId(Integer id) {
        // Obtendo Optional de produto pelo ID.
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não encontrar, lança uma Exception
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto ID +" + id + " não foi encontrado");
        }

        // Convertendo o Optional de produto em um ProdutoDTO
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        // Criando e retornando um optional de ProdutoDTO
        return Optional.of(dto);
    }

    /**
     * Método que adiciona um produto na lista
     * @param produtoDto a ser adicionado.
     * @return o produto após ser adicionado na lista.
     */
    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDto) {
        // Removendo ID para garantir o cadastro
        produtoDto.setId(null);
        Produto produto = new ModelMapper().map(produtoDto, Produto.class);
        produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }

    /**
     * Deleta um produto da lista.
     * @param id do produto a ser deletado.
     */
    public void deletarProduto(Integer id) {
        if (obterProdutoPorId(id).isEmpty()) {
            throw new ResourceNotFoundException("Produto ID " + id + " não existe");
        }
        produtoRepository.deleteById(id);
    }

    /**
     * Método que atualiza um produto da lista.
     * @param id do produto a ser atualizado
     * @param produtoDto a ser atualizado
     * @return produto após ser atualizado na lista.
     */
    public ProdutoDTO atualizarProduto(Integer id, ProdutoDTO produtoDto) {
        if (obterProdutoPorId(id).isEmpty()) {
            throw new ResourceNotFoundException("Produto ID " + id + " não encontrado.");
        }
        /**
         * Instanciando uma variável do tipo Produto com nome produto,
         * que recebe um ModelMapper e chama o método map para
         * popular/copiar as informações do ProdutoDTO recebido na requisição.
         */
        produtoDto.setId(id);
        Produto produto = new ModelMapper().map(produtoDto, Produto.class);
        produtoRepository.deleteById(id);
        Produto produtoAtualizado = produtoRepository.save(produto);
        return new ProdutoDTO(produtoAtualizado);
    }
}
