package com.victormorais.ecommerce.controllers;

import com.victormorais.ecommerce.domain.CarrinhoDeComprasDTO;
import com.victormorais.ecommerce.domain.ItemProdutoDTO;
import com.victormorais.ecommerce.domain.PedidoDTO;
import com.victormorais.ecommerce.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<PedidoDTO> cadastrarPedido(@RequestBody PedidoDTO pedidoDTO){

        PedidoDTO response = pedidoService.cadastrarPedido(pedidoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<PedidoDTO>> listarPedidos(@RequestParam Map<String, String> params){

        List<PedidoDTO> users = pedidoService.listarPedidos(params);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(users);

    }

    @RequestMapping(value = "/{id_pedido}", method = RequestMethod.POST)
    public ResponseEntity<PedidoDTO> concluirPedido(@PathVariable("id_pedido") String idPedido){

        PedidoDTO response = pedidoService.concluirPedido(UUID.fromString(idPedido));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/{id_pedido}", method = RequestMethod.DELETE)
    public ResponseEntity<PedidoDTO> cancelarPedido(@PathVariable("id_pedido") String idPedido){

        PedidoDTO response = pedidoService.cancelarPedido(UUID.fromString(idPedido));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/{id_pedido}", method = RequestMethod.PATCH)
    public ResponseEntity<PedidoDTO> aplicarDesconto(@PathVariable("id_pedido") String idPedido,
                                                     @RequestParam Map<String, String> params){

        PedidoDTO response = pedidoService.aplicarDesconto(UUID.fromString(idPedido), params);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

}
