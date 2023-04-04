package com.victormorais.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

  private int idEndereco;
  private String logradouro;
  private String cidade;
  private String estado;
  private String bairro;
  private String CEP;

  //Acredito que os metodos a seguir fazem mais sentido estarem na camada de controle/servico, ou aqui caso esta fosse uma interface ou algo do tipo.
  //Porem mantive para seguir as especificacoes do projeto
  private void cadastrarEndereco(){
    return;
  }

  private void editarEndereco(){
    return;
  }

  private void removerEndereco(){
    return;
  }

  private void listarEndereco(){
    return;
  }

}
