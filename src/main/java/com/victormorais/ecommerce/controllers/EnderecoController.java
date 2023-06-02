package com.victormorais.ecommerce.controllers;

import com.victormorais.ecommerce.domain.EnderecoDTO;
import com.victormorais.ecommerce.domain.UsuarioDTO;
import com.victormorais.ecommerce.domain.entities.Endereco;
import com.victormorais.ecommerce.services.EnderecoService;
import com.victormorais.ecommerce.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestParam("user_id") String userId){

        EnderecoDTO response = enderecoService.cadastrarEndereco(enderecoDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<EnderecoDTO>> listarEndereco(@RequestParam Map<String, String> params){

        List<EnderecoDTO> enderecos = enderecoService.listarEnderecos(params);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(enderecos);

    }

    @RequestMapping(value = "/{endereco_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarEndereco(@PathVariable("endereco_id") String idEndereco){

        String s = enderecoService.deletarEndereco(UUID.fromString(idEndereco));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(s);

    }

    @RequestMapping(value = "/{endereco_id}", method = RequestMethod.PUT)
    public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable("endereco_id") String idEndereco,
                                                    @RequestBody EnderecoDTO enderecoDTO){

        EnderecoDTO response = enderecoService.updateEndereco(UUID.fromString(idEndereco), enderecoDTO);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

}
