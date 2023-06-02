package com.victormorais.ecommerce.controllers;

import com.victormorais.ecommerce.domain.CarrinhoDeComprasDTO;
import com.victormorais.ecommerce.domain.ItemProdutoDTO;
import com.victormorais.ecommerce.domain.UsuarioDTO;
import com.victormorais.ecommerce.services.CarrinhoDeComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoDeComprasController {

    @Autowired
    private CarrinhoDeComprasService carrinhoDeComprasService;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<CarrinhoDeComprasDTO> cadastrarCarrinho(@RequestBody List<ItemProdutoDTO> itemProdutos){

        CarrinhoDeComprasDTO response = carrinhoDeComprasService.cadastrarCarrinho(itemProdutos);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<CarrinhoDeComprasDTO>> listarCarrinho(@RequestParam Map<String, String> params){

        List<CarrinhoDeComprasDTO> users = carrinhoDeComprasService.listarCarrinho(params);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(users);

    }

    @RequestMapping(value = "/{id_carrinho}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarCarrinho(@PathVariable("id_carrinho") String idCarrinho){

        String s = carrinhoDeComprasService.deletarCarrinho(UUID.fromString(idCarrinho));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(s);

    }

    @RequestMapping(value = "/{id_carrinho}", method = RequestMethod.PATCH)
    public ResponseEntity<CarrinhoDeComprasDTO> removerItemCarrinho(@PathVariable("id_carrinho") String idCarrinho,
                                                    @RequestParam(name = "id_itemproduto") String idItemProduto){

        CarrinhoDeComprasDTO response = carrinhoDeComprasService.removerItem(UUID.fromString(idCarrinho), UUID.fromString(idItemProduto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/{id_carrinho}", method = RequestMethod.PUT)
    public ResponseEntity<CarrinhoDeComprasDTO> addItemCarrinho(@PathVariable("id_carrinho") String idCarrinho,
                                                                    @RequestBody ItemProdutoDTO itemProdutoDTO){

        CarrinhoDeComprasDTO response = carrinhoDeComprasService.addItem(UUID.fromString(idCarrinho), itemProdutoDTO);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

}
