package com.victormorais.ecommerce.domain;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

import com.victormorais.ecommerce.domain.entities.Pedido;
import com.victormorais.ecommerce.domain.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

  private UUID id;
  private UsuarioDTO usuario;
  private EnderecoDTO endereco;
  private Pedido.TipoPagamento tipoPagamento;
  private CarrinhoDeComprasDTO carrinhoDeCompras;
  private LocalDate dataEntrada;
  private LocalDate dataEmissao;
  private Double desconto;
  private Double valorTotal;
  private String status;

  private void aplicarDesconto(Double desconto){
    return;
  }


  public static final PedidoDTO from(Pedido pedido){
    return PedidoDTO.builder()
            .usuario(UsuarioDTO.from(pedido.getUsuario()))
            .endereco(EnderecoDTO.from(pedido.getEndereco()))
            .carrinhoDeCompras(CarrinhoDeComprasDTO.from(pedido.getCarrinhoDeCompras()))
            .tipoPagamento(pedido.getTipoPagamento())
            .dataEntrada(pedido.getDataEntrada())
            .status(pedido.getStatus())
            .valorTotal(pedido.getValorTotal())
            .desconto(pedido.getDesconto() != null ? pedido.getDesconto() : null)
            .dataEmissao(pedido.getDataEmissao() != null ? pedido.getDataEmissao() : null)
            .build();
  }

}
