package com.victormorais.ecommerce.controllers;

import com.victormorais.ecommerce.domain.ItemProdutoDTO;
import com.victormorais.ecommerce.domain.ProdutoDTO;
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
@RequestMapping("/api/item-produtos")
public class ItemProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<ItemProdutoDTO> cadastrarItemProduto(@RequestParam(name = "produto_id") String idProduto,
                                                               @RequestParam(name = "qntd") Integer quantidade){

        ItemProdutoDTO response = produtoService.cadastrarItemProduto(idProduto, quantidade);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<ItemProdutoDTO>> listarItemProduto(@RequestParam Map<String, String> params){

        List<ItemProdutoDTO> produtos = produtoService.listarItemProdutos(params);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(produtos);

    }

    @RequestMapping(value = "/{produto_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarItemProduto(@PathVariable("produto_id") String idItemProduto){

        String s = produtoService.deletarItemProduto(UUID.fromString(idItemProduto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(s);

    }

    @RequestMapping(value = "/{produto_id}", method = RequestMethod.PUT)
    public ResponseEntity<ItemProdutoDTO> updateItemProduto(@PathVariable("produto_id") String idItemProduto,
                                                            @RequestParam(name = "qntd") Integer quantidade){

        ItemProdutoDTO response = produtoService.updateItemProduto(UUID.fromString(idItemProduto), quantidade);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

}
