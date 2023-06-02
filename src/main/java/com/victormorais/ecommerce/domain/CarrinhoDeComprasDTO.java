package com.victormorais.ecommerce.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.victormorais.ecommerce.domain.entities.CarrinhoDeCompras;
import com.victormorais.ecommerce.domain.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoDeComprasDTO {

  private UUID id;
  private Double valorTotal;
  private LocalDate dataEmissao;
  private LocalDate dataEntrada;
  private List<ItemProdutoDTO> itemProdutos;

  public static final CarrinhoDeComprasDTO from(CarrinhoDeCompras carrinhoDeCompras){
    return CarrinhoDeComprasDTO.builder()
            .id(carrinhoDeCompras.getId())
            .valorTotal(carrinhoDeCompras.getValorTotal())
            .dataEntrada(carrinhoDeCompras.getDataEntrada())
            .dataEmissao(carrinhoDeCompras.getDataEmissao() != null ? carrinhoDeCompras.getDataEmissao() : null)
            .itemProdutos(carrinhoDeCompras.getItemProdutos().stream().map(x -> ItemProdutoDTO.builder()
                    .quantidade(x.getQuantidade())
                    .produto(ProdutoDTO.from(x.getProduto()))
                    .id(x.getId()).build()).collect(Collectors.toList()))
            .build();
  }

}
