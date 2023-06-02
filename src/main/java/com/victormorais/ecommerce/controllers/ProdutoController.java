package com.victormorais.ecommerce.controllers;

import com.victormorais.ecommerce.domain.EnderecoDTO;
import com.victormorais.ecommerce.domain.ProdutoDTO;
import com.victormorais.ecommerce.services.EnderecoService;
import com.victormorais.ecommerce.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){

        ProdutoDTO response = produtoService.cadastrarProduto(produtoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoDTO>> listarProduto(@RequestParam Map<String, String> params){

        List<ProdutoDTO> produtos = produtoService.listarProdutos(params);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(produtos);

    }

    @RequestMapping(value = "/{produto_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarProduto(@PathVariable("produto_id") String idProduto){

        String s = produtoService.deletarProduto(UUID.fromString(idProduto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(s);

    }

    @RequestMapping(value = "/{produto_id}", method = RequestMethod.PUT)
    public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable("produto_id") String idProduto,
                                                      @RequestBody ProdutoDTO produtoDTO){

        ProdutoDTO response = produtoService.updateProduto(UUID.fromString(idProduto), produtoDTO);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

}
