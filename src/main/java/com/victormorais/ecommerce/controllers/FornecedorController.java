package com.victormorais.ecommerce.controllers;

import com.victormorais.ecommerce.domain.EnderecoDTO;
import com.victormorais.ecommerce.domain.FornecedorDTO;
import com.victormorais.ecommerce.services.EnderecoService;
import com.victormorais.ecommerce.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<FornecedorDTO> cadastrarFornecedor(@RequestBody FornecedorDTO fornecedorDTO,
                                                             @RequestParam("user_id") String userId){

        FornecedorDTO response = fornecedorService.cadastrarFornecedor(fornecedorDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<FornecedorDTO>> listarFornecedor(@RequestParam Map<String, String> params){

        List<FornecedorDTO> fornecedores = fornecedorService.listarFornecedores(params);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(fornecedores);

    }

    @RequestMapping(value = "/{fornecedor_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarFornecedor(@PathVariable("fornecedor_id") String idFornecedor){

        String s = fornecedorService.deletarFornecedor(UUID.fromString(idFornecedor));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(s);

    }

    @RequestMapping(value = "/{fornecedor_id}", method = RequestMethod.PUT)
    public ResponseEntity<FornecedorDTO> updateFornecedor(@PathVariable("fornecedor_id") String idFornecedor,
                                                      @RequestBody FornecedorDTO fornecedorDTO){

        FornecedorDTO response = fornecedorService.updateFornecedor(UUID.fromString(idFornecedor), fornecedorDTO);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

}
