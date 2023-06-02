package com.victormorais.ecommerce.domain;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.victormorais.ecommerce.domain.entities.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {


  private UUID id;
  @NotNull
  private String nome;
  @NotNull
  private String email;
  @NotNull
  private String senha;
  @NotNull
  private String cpf;

  private List<EnderecoDTO> enderecos;

  public static final UsuarioDTO from(Usuario usuario){
    return UsuarioDTO.builder()
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .senha(usuario.getSenha())
            .cpf(usuario.getCpf())
            .enderecos(usuario.getEnderecos().stream().map(x -> EnderecoDTO.builder()
                    .bairro(x.getBairro())
                    .cidade(x.getCidade())
                    .CEP(x.getCEP())
                    .logradouro(x.getLogradouro())
                    .estado(x.getEstado()).build()).collect(Collectors.toList()))
            .build();
  }

}
