package com.victormorais.ecommerce.domain.repository;

import com.victormorais.ecommerce.domain.entities.ItemProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemProdutoRepository extends JpaRepository<ItemProduto, UUID> {
}
