package com.victormorais.ecommerce.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

  private int idPedido;
  private Usuario usuario;
  private Endereco endereco;
  private TipoPagamento tipoPagamento;
  private CarrinhoDeCompras carrinhoDeCompras;
  private LocalDate dataEntrada;
  private LocalDate dataEmissao;
  private Double desconto;
  private Double valorTotal;

  private void concluirPedido(int idPedido, int idUsuario,
      int idCarrinhoDeCompras, int idEndereco, int idPagamento){
    return;
  }

  private void cancelarPedido(int idPedido){
    return;
  }

  private void rastreioPedido(int idPedido){
    return;
  }

  private void aplicarDesconto(int desconto){
    return;
  }

  private void calcularFrete(){
    return;
  }

  private void formaEnvio(){
    return;
  }

  private void statusPedido(){
    return;
  }

  //Optei por definir o enum diretamente, ao inves de uma classe separada apenas pra ter um ID pra cada tipo.
      public enum TipoPagamento{
        CREDITO, DEBITO, PIX
      }
}
