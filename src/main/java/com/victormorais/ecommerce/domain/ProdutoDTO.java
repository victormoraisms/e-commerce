package com.victormorais.ecommerce.domain;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.victormorais.ecommerce.domain.entities.Endereco;
import com.victormorais.ecommerce.domain.entities.Produto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

  private UUID id;
  @NotNull
  private String nome;
  @NotNull
  private Float preco;
  @NotNull
  private Integer qtdEstoque;

  private FornecedorDTO fornecedor;

  public static final ProdutoDTO from(Produto produto){
    return ProdutoDTO.builder()
            .id(produto.getId())
            .nome(produto.getNome())
            .preco(produto.getPreco())
            .qtdEstoque(produto.getQtdEstoque())
            .fornecedor(FornecedorDTO.from(produto.getFornecedor()))
            .build();
  }

}
