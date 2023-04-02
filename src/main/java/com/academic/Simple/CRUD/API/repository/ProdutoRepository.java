package com.academic.Simple.CRUD.API.repository;

import com.academic.Simple.CRUD.API.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
