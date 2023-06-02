package com.victormorais.ecommerce.services;

import com.victormorais.ecommerce.domain.CarrinhoDeComprasDTO;
import com.victormorais.ecommerce.domain.EnderecoDTO;
import com.victormorais.ecommerce.domain.ItemProdutoDTO;
import com.victormorais.ecommerce.domain.UsuarioDTO;
import com.victormorais.ecommerce.domain.entities.CarrinhoDeCompras;
import com.victormorais.ecommerce.domain.entities.ItemProduto;
import com.victormorais.ecommerce.domain.entities.Produto;
import com.victormorais.ecommerce.domain.entities.Usuario;
import com.victormorais.ecommerce.domain.repository.CarrinhoDeComprasRepository;
import com.victormorais.ecommerce.domain.repository.ItemProdutoRepository;
import com.victormorais.ecommerce.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarrinhoDeComprasService {

    @Autowired
    private ItemProdutoRepository itemProdutoRepository;

    @Autowired
    private CarrinhoDeComprasRepository carrinhoDeComprasRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public CarrinhoDeComprasDTO cadastrarCarrinho(List<ItemProdutoDTO> itemProdutos){

        List<ItemProduto> itemProdutosList = new ArrayList<>();

        itemProdutos.forEach(itemProdutoDTO -> {
            Optional<ItemProduto> itemProduto = itemProdutoRepository.findById(itemProdutoDTO.getId());

            if (!itemProduto.isPresent()) throw new EntityNotFoundException();

            itemProdutosList.add(itemProduto.get());

        });

        CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
        carrinhoDeCompras.setItemProdutos(itemProdutosList);

        Double valorTotal = null;

        for(ItemProduto itemProduto : itemProdutosList){
            valorTotal += Math.round(itemProduto.getProduto().getPreco() * itemProduto.getQuantidade());
        }

        carrinhoDeCompras.setValorTotal(valorTotal);

        carrinhoDeComprasRepository.save(carrinhoDeCompras);

        return CarrinhoDeComprasDTO.from(carrinhoDeCompras);
    }

    public List<CarrinhoDeComprasDTO> listarCarrinho(Map<String, String> params) {

        String idCarrinho = params.get("id_carrinho");

        try {

            if (idCarrinho != null) {

                Optional<CarrinhoDeCompras> carrinho = carrinhoDeComprasRepository.findById(UUID.fromString(idCarrinho));

                if (carrinho.isPresent()) {
                    return List.of(CarrinhoDeComprasDTO.from(carrinho.get()));
                } else {
                    throw new EntityNotFoundException();
                }

            }else{
                List<CarrinhoDeCompras> carrinho = carrinhoDeComprasRepository.findAll();

                return carrinho.stream().map(c -> CarrinhoDeComprasDTO.from(c)).collect(Collectors.toList());
            }

        }catch (Exception e){
            throw e;
        }

    }

    public String deletarCarrinho(UUID id) {

        try {

            Optional<CarrinhoDeCompras> carrinhoDeCompras = carrinhoDeComprasRepository.findById(id);

            if(carrinhoDeCompras.isPresent()){
                carrinhoDeComprasRepository.delete(carrinhoDeCompras.get());
                return "Carrinho de compras deletado!";
            }else{
                return "Carrinho de compras não encontrado!";
            }

        }catch (Exception e){
            throw e;
        }

    }

    public CarrinhoDeComprasDTO removerItem(UUID idCarrinho, UUID idItemProduto) {

        try {

            Optional<CarrinhoDeCompras> carrinhoDeCompras = carrinhoDeComprasRepository.findById(idCarrinho);

            if(carrinhoDeCompras.isPresent()){
                Optional<CarrinhoDeCompras> finalCarrinhoDeCompras = carrinhoDeCompras;
                carrinhoDeCompras.get().getItemProdutos().forEach(itemProduto -> {
                    if(itemProduto.getId().equals(idItemProduto)) finalCarrinhoDeCompras.get().getItemProdutos().remove(itemProduto);
                });
                carrinhoDeCompras = Optional.of(carrinhoDeComprasRepository.save(finalCarrinhoDeCompras.get()));
                return CarrinhoDeComprasDTO.from(carrinhoDeCompras.get());
            }else{
                throw new EntityNotFoundException();
            }

        }catch (Exception e){
            throw e;
        }

    }

    public CarrinhoDeComprasDTO addItem(UUID idCarrinho, ItemProdutoDTO itemProdutoDTO) {

        try {

            Optional<CarrinhoDeCompras> carrinhoDeCompras = carrinhoDeComprasRepository.findById(idCarrinho);

            if(carrinhoDeCompras.isPresent()){

                ItemProduto itemProduto = new ItemProduto();
                itemProduto.setProduto(produtoRepository.findById(itemProdutoDTO.getProduto().getId()).get());
                itemProduto.setQuantidade(itemProduto.getQuantidade());
                itemProduto.setCarrinho(carrinhoDeCompras.get());

                itemProdutoRepository.save(itemProduto);

                carrinhoDeCompras.get().getItemProdutos().add(itemProduto);

                carrinhoDeCompras = Optional.of(carrinhoDeComprasRepository.save(carrinhoDeCompras.get()));

                return CarrinhoDeComprasDTO.from(carrinhoDeCompras.get());

            }else{
                throw new EntityNotFoundException();
            }

        }catch (Exception e){
            throw e;
        }

    }

}
