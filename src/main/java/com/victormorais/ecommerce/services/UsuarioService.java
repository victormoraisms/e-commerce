package com.victormorais.ecommerce.services;

import com.victormorais.ecommerce.domain.EnderecoDTO;
import com.victormorais.ecommerce.domain.UsuarioDTO;
import com.victormorais.ecommerce.domain.entities.Endereco;
import com.victormorais.ecommerce.domain.entities.Usuario;
import com.victormorais.ecommerce.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO){

        Usuario user = new Usuario();
        user.setNome(usuarioDTO.getNome());
        user.setCpf(usuarioDTO.getCpf());
        user.setSenha(usuarioDTO.getSenha());
        user.setEmail(usuarioDTO.getEmail());

        if(!usuarioDTO.getEnderecos().isEmpty()){
            List<Endereco> enderecoList = new ArrayList<>();

            usuarioDTO.getEnderecos().forEach(enderecoDTO -> {
               Endereco endereco = new Endereco();
               endereco.setCEP(enderecoDTO.getCEP());
               endereco.setCidade(enderecoDTO.getCidade());
               endereco.setBairro(enderecoDTO.getBairro());
               endereco.setEstado(enderecoDTO.getEstado());
               endereco.setLogradouro(enderecoDTO.getLogradouro());
               enderecoList.add(endereco);
            });

            user.setEnderecos(enderecoList);
        }

        usuarioRepository.save(user);

        return UsuarioDTO.from(user);
    }

    public List<UsuarioDTO> listarUsuarios(Map<String, String> params) {

        String userId = params.get("user_id");

        try {

            if (userId != null) {

                Optional<Usuario> user = usuarioRepository.findById(UUID.fromString(userId));

                if (user.isPresent()) {
                    return List.of(UsuarioDTO.from(user.get()));
                } else {
                    throw new EntityNotFoundException();
                }

            }else{
                List<Usuario> users = usuarioRepository.findAll();

                return users.stream().map(u -> UsuarioDTO.builder()
                        .id(u.getId())
                        .cpf(u.getCpf())
                        .senha(u.getSenha())
                        .nome(u.getNome())
                        .email(u.getEmail())
                        .enderecos(u.getEnderecos().stream().map(e -> EnderecoDTO.builder()
                                .CEP(e.getCEP())
                                .bairro(e.getBairro())
                                .estado(e.getEstado())
                                .cidade(e.getCidade())
                                .logradouro(e.getLogradouro())
                                .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
            }

        }catch (Exception e){
            throw e;
        }

    }

    public String deletarUsuario(UUID id) {

        try {

           Optional<Usuario> user = usuarioRepository.findById(id);

           if(user.isPresent()){
               usuarioRepository.delete(user.get());
               return "Usuario deletado!";
           }else{
               return "Usuario não encontrado!";
           }

        }catch (Exception e){
            throw e;
        }

    }

    public UsuarioDTO updateUsuario(UUID userId, UsuarioDTO usuarioDTO) {

        try {

            Optional<Usuario> user = usuarioRepository.findById(userId);

            if(user.isPresent()){
                if(usuarioDTO.getNome() != null) user.get().setNome(usuarioDTO.getNome());
                if(usuarioDTO.getEmail() != null) user.get().setEmail(usuarioDTO.getEmail());
                if(usuarioDTO.getSenha() != null) user.get().setSenha(usuarioDTO.getSenha());
                if(usuarioDTO.getCpf() != null) user.get().setCpf(usuarioDTO.getCpf());
                user = Optional.of(usuarioRepository.save(user.get()));
                return UsuarioDTO.from(user.get());
            }else{
                throw new EntityNotFoundException();
            }

        }catch (Exception e){
            throw e;
        }

    }

}
