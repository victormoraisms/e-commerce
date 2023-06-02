package com.victormorais.ecommerce.domain.repository;

import com.victormorais.ecommerce.domain.entities.CarrinhoDeCompras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarrinhoDeComprasRepository extends JpaRepository<CarrinhoDeCompras, UUID> {
}
