package com.victormorais.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemProduto {

  private int idItemProduto;
  private Produto produto;
  private int quantidade;

  private void cadastrarItemProduto(Produto produto, int quantidade){
    return;
  }

  private void valorTotal(){
    return;
  }

}
