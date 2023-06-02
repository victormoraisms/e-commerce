package com.victormorais.ecommerce.domain;

import com.victormorais.ecommerce.domain.entities.Endereco;
import com.victormorais.ecommerce.domain.entities.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

  private UUID id;
  private String logradouro;
  private String cidade;
  private String estado;
  private String bairro;
  private String CEP;

  public static final EnderecoDTO from(Endereco endereco){
    return EnderecoDTO.builder()
            .id(endereco.getId())
            .logradouro(endereco.getLogradouro())
            .cidade(endereco.getCidade())
            .estado(endereco.getEstado())
            .bairro(endereco.getBairro())
            .CEP(endereco.getCEP())
            .build();
  }

}
