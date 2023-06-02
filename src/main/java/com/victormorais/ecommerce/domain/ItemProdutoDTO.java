package com.victormorais.ecommerce.domain;

import com.victormorais.ecommerce.domain.entities.Fornecedor;
import com.victormorais.ecommerce.domain.entities.ItemProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemProdutoDTO {

  private UUID id;
  private ProdutoDTO produto;
  private Integer quantidade;

  private void cadastrarItemProduto(ProdutoDTO produto, int quantidade){
    return;
  }

  private void valorTotal(){
    return;
  }

  public static final ItemProdutoDTO from(ItemProduto itemProduto){
    return ItemProdutoDTO.builder()
            .id(itemProduto.getId())
            .produto(ProdutoDTO.from(itemProduto.getProduto()))
            .quantidade(itemProduto.getQuantidade())
            .build();
  }

}
