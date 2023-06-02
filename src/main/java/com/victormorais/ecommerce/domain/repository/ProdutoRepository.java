package com.victormorais.ecommerce.domain.repository;

import com.victormorais.ecommerce.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
