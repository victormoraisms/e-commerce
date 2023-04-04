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
public class Usuario {

  private int idUsuario;
  private String nomeUsuario;
  private String emailUsuario;
  //senha como String para permitir alfanumerico
  private String senhaUsuario;
  private String cpfUsuario;
  private List<Endereco> enderecos;


  //Metodos de CRUD a seguir poderiam ir na camada de controle/servico.
  private void cadastrarUsuario(){
    return;
  }

  private void removeUsuario(){
    return;
  }

  private void alterarUsuario(){
    return;
  }

  private void listarUsuario(){
    return;
  }


}
