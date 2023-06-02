package com.victormorais.ecommerce.services;

import com.victormorais.ecommerce.domain.CarrinhoDeComprasDTO;
import com.victormorais.ecommerce.domain.ItemProdutoDTO;
import com.victormorais.ecommerce.domain.PedidoDTO;
import com.victormorais.ecommerce.domain.entities.*;
import com.victormorais.ecommerce.domain.repository.CarrinhoDeComprasRepository;
import com.victormorais.ecommerce.domain.repository.EnderecoRepository;
import com.victormorais.ecommerce.domain.repository.PedidoRepository;
import com.victormorais.ecommerce.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CarrinhoDeComprasRepository carrinhoDeComprasRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDTO cadastrarPedido(PedidoDTO pedidoDTO){

       Pedido pedido = new Pedido();

       Optional<Usuario> usr = usuarioRepository.findById(pedidoDTO.getUsuario().getId());

        if (usr.isPresent()) pedido.setUsuario(usr.get());

        Optional<Endereco> endereco = enderecoRepository.findById(pedidoDTO.getEndereco().getId());

        if (endereco.isPresent()) pedido.setEndereco(endereco.get());

        Optional<CarrinhoDeCompras> carrinho = carrinhoDeComprasRepository.findById(pedidoDTO.getCarrinhoDeCompras().getId());

        if (carrinho.isPresent()) pedido.setCarrinhoDeCompras(carrinho.get());

        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setTipoPagamento(pedidoDTO.getTipoPagamento());
        pedido.setStatus("CADASTRADO");

        if(pedidoDTO.getDesconto() != null) pedido.setDesconto(pedidoDTO.getDesconto());

        pedido = pedidoRepository.save(pedido);

        return PedidoDTO.from(pedido);
    }

    public List<PedidoDTO> listarPedidos(Map<String, String> params) {

        String idPedido = params.get("id_pedido");

        try {

            if (idPedido != null) {

                Optional<Pedido> pedido = pedidoRepository.findById(UUID.fromString(idPedido));

                if (pedido.isPresent()) {
                    return List.of(PedidoDTO.from(pedido.get()));
                } else {
                    throw new EntityNotFoundException();
                }

            }else{
                List<Pedido> pedidos = pedidoRepository.findAll();

                return pedidos.stream().map(p -> PedidoDTO.from(p)).collect(Collectors.toList());
            }

        }catch (Exception e){
            throw e;
        }

    }

    public PedidoDTO concluirPedido(UUID idPedido){

        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);

        if (pedido.isPresent()) {

            pedido.get().setStatus("CONCLUÍDO");
            pedido.get().setDataEmissao(LocalDate.now());

            pedido.get().getCarrinhoDeCompras().setDataEmissao(LocalDate.now());

            pedido = Optional.of(pedidoRepository.save(pedido.get()));
            return PedidoDTO.from(pedido.get());

        } else {
            throw new EntityNotFoundException();
        }
    }

    public PedidoDTO cancelarPedido(UUID idPedido){

        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);

        if (pedido.isPresent()) {

            pedido.get().setStatus("CANCELADO");
            pedido.get().setDataEmissao(LocalDate.now());

            pedido.get().getCarrinhoDeCompras().setDataEmissao(LocalDate.now());

            pedido = Optional.of(pedidoRepository.save(pedido.get()));
            return PedidoDTO.from(pedido.get());

        } else {
            throw new EntityNotFoundException();
        }
    }

    public PedidoDTO aplicarDesconto(UUID idPedido, Map<String, String> params){

        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);

        if (pedido.isPresent()) {

            if(params.get("desconto") != null){

                pedido.get().setValorTotal(pedido.get().getValorTotal() - Double.valueOf(params.get("desconto")));

                pedido = Optional.of(pedidoRepository.save(pedido.get()));

            }else{

                pedido.get().setValorTotal(pedido.get().getValorTotal() - pedido.get().getDesconto());

                pedido = Optional.of(pedidoRepository.save(pedido.get()));

            }

        } else {
            throw new EntityNotFoundException();
        }


        return PedidoDTO.from(pedido.get());


    }

}
