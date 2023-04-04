package com.victormorais.ecommerce.domain;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoDeCompras {

  private int idCarrinhoDeCompra;
  private Double valorTotal;
  private LocalDate dataEmissao;
  private LocalDate dataEntrada;
  private List<ItemProduto> itemProdutos;

  private void cadastrarCarrinho(List<ItemProduto> produtos){
    return;
  }

  private void listarCarrinho(){
    return;
  }

  private void removerItemProduto(){
    return;
  }

  private void addItemProduto(){
    return;
  }

  private void valorTotal(){
    return;
  }

}
