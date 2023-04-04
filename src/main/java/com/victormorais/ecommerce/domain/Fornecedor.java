package com.victormorais.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {

  private int idFornecedor;
  private String nomeFornecedor;
  private String CNPJFornecedor;

  private void listarFornecedor(){
    return;
  }

  //Metodo pode ser substituido por um construtor/builder apenas, ja que o verdadeiro responsavel pelo cadastro
  //sera a camada de persistencia, que por sua vez pode receber esse objeto inteiro.
  private void cadastrarFornecedor(String nomeFornecedor, String CNPJFornecedor){
    return;
  }

}
