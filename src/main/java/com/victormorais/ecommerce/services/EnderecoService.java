package com.victormorais.ecommerce.services;

import com.victormorais.ecommerce.domain.EnderecoDTO;
import com.victormorais.ecommerce.domain.UsuarioDTO;
import com.victormorais.ecommerce.domain.entities.Endereco;
import com.victormorais.ecommerce.domain.entities.Usuario;
import com.victormorais.ecommerce.domain.repository.EnderecoRepository;
import com.victormorais.ecommerce.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public EnderecoDTO cadastrarEndereco(EnderecoDTO enderecoDTO, String userId){

        Endereco endereco = new Endereco();

        Optional<Usuario> user = usuarioRepository.findById(UUID.fromString(userId));

        if (user.isPresent()) {
            endereco.setUsuario(user.get());
        } else {
            throw new EntityNotFoundException();
        }

        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCEP(enderecoDTO.getCEP());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setEstado(enderecoDTO.getEstado());

        enderecoRepository.save(endereco);

        return EnderecoDTO.from(endereco);
    }

    public List<EnderecoDTO> listarEnderecos(Map<String, String> params) {

        String userId = params.get("user_id");

        try {

            if (userId != null) {

                Optional<Usuario> user = usuarioRepository.findById(UUID.fromString(userId));

                if (user.isPresent()) {

                    List<EnderecoDTO> enderecos = new ArrayList<>();

                    enderecoRepository.findAllByUsuario(user.get()).forEach(endereco -> {
                        enderecos.add(EnderecoDTO.from(endereco));
                    });

                    return enderecos
                            ;
                } else {
                    throw new EntityNotFoundException();
                }

            }else{

                List<EnderecoDTO> enderecos = new ArrayList<>();

                enderecoRepository.findAll().forEach(endereco -> {
                    enderecos.add(EnderecoDTO.from(endereco));
                });

                return enderecos;
            }

        }catch (Exception e){
            throw e;
        }

    }

    public String deletarEndereco(UUID id) {

        try {

            Optional<Endereco> endereco = enderecoRepository.findById(id);

            if(endereco.isPresent()){
                enderecoRepository.delete(endereco.get());
                return "Endereco deletado!";
            }else{
                return "Endereco não encontrado!";
            }

        }catch (Exception e){
            throw e;
        }

    }

    public EnderecoDTO updateEndereco(UUID idEndereco, EnderecoDTO enderecoDTO) {

        try {

            Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);

            if(endereco.isPresent()){
                if(enderecoDTO.getLogradouro() != null) endereco.get().setLogradouro(enderecoDTO.getLogradouro());
                if(enderecoDTO.getEstado() != null) endereco.get().setEstado(enderecoDTO.getEstado());
                if(enderecoDTO.getCidade() != null) endereco.get().setCidade(enderecoDTO.getCidade());
                if(enderecoDTO.getBairro() != null) endereco.get().setBairro(enderecoDTO.getBairro());
                if(enderecoDTO.getCEP() != null) endereco.get().setCEP(enderecoDTO.getCEP());
                endereco = Optional.of(enderecoRepository.save(endereco.get()));
                return EnderecoDTO.from(endereco.get());
            }else{
                throw new EntityNotFoundException();
            }

        }catch (Exception e){
            throw e;
        }

    }

}
