package com.victormorais.ecommerce.domain.repository;

import com.victormorais.ecommerce.domain.entities.Fornecedor;
import com.victormorais.ecommerce.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

    Fornecedor findByProduto(Produto produto);

}
