package com.victormorais.ecommerce.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

  private int idProduto;
  private String nomeProduto;
  private float precoProduto;
  private int qtdEstoque;
  private List<Fornecedor> fornecedores;


  //Acredito que os metodos a seguir fazem mais sentido estarem na camada de controle/servico, ou aqui caso esta fosse uma interface ou algo do tipo.
  //Porem mantive para seguir as especificacoes do projeto
  private void cadastrarProduto(){
    return;
  }

  private void removerProduto(int idProduto){
    return;
  }

  private void alterarProduto(int idProduto){
    return;
  }

  private void listarProdutos(){
    return;
  }

}
