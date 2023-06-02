package com.victormorais.ecommerce.domain;

import com.victormorais.ecommerce.domain.entities.Fornecedor;
import com.victormorais.ecommerce.domain.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorDTO {

  private UUID id;
  private String nome;
  private String CNPJ;

  public static final FornecedorDTO from(Fornecedor fornecedor){
    return FornecedorDTO.builder()
            .id(fornecedor.getId())
            .nome(fornecedor.getNome())
            .CNPJ(fornecedor.getCNPJ())
            .build();
  }

}
