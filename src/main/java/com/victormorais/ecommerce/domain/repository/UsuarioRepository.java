package com.victormorais.ecommerce.domain.repository;

import com.victormorais.ecommerce.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
