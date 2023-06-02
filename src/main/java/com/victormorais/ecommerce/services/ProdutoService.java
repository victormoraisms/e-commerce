package com.victormorais.ecommerce.services;

import com.victormorais.ecommerce.domain.EnderecoDTO;
import com.victormorais.ecommerce.domain.ItemProdutoDTO;
import com.victormorais.ecommerce.domain.ProdutoDTO;
import com.victormorais.ecommerce.domain.entities.*;
import com.victormorais.ecommerce.domain.repository.ItemProdutoRepository;
import com.victormorais.ecommerce.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemProdutoRepository itemProdutoRepository;

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO){

        Produto produto = new Produto();

        produto.setNome(produto.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQtdEstoque(produtoDTO.getQtdEstoque());

        if(produtoDTO.getFornecedor() != null){
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setCNPJ(produtoDTO.getFornecedor().getCNPJ());
            fornecedor.setNome(produtoDTO.getFornecedor().getNome());

            produto.setFornecedor(fornecedor);
        }

        produtoRepository.save(produto);

        return ProdutoDTO.from(produto);
    }

    public List<ProdutoDTO> listarProdutos(Map<String, String> params) {

        String produtoId = params.get("id_produto");

        try {

            if (produtoId != null) {

                Optional<Produto> produto = produtoRepository.findById(UUID.fromString(produtoId));

                if (produto.isPresent()) {
                    return List.of(ProdutoDTO.from(produto.get()));
                } else {
                    throw new EntityNotFoundException();
                }

            }else{

                List<ProdutoDTO> produtos = new ArrayList<>();

                produtoRepository.findAll().forEach(produto -> {
                    produtos.add(ProdutoDTO.from(produto));
                });

                return produtos;
            }

        }catch (Exception e){
            throw e;
        }

    }

    public String deletarProduto(UUID id) {

        try {

            Optional<Produto> produto = produtoRepository.findById(id);

            if(produto.isPresent()){
                produtoRepository.delete(produto.get());
                return "Produto deletado!";
            }else{
                return "Produto não encontrado!";
            }

        }catch (Exception e){
            throw e;
        }

    }

    public ProdutoDTO updateProduto(UUID idProduto, ProdutoDTO produtoDTO) {

        try {

            Optional<Produto> produto = produtoRepository.findById(idProduto);

            if(produto.isPresent()){
                if(produtoDTO.getPreco() != null) produto.get().setPreco(produtoDTO.getPreco());
                if(produtoDTO.getNome() != null) produto.get().setNome(produtoDTO.getNome());
                if(produtoDTO.getQtdEstoque() != null) produto.get().setQtdEstoque(produtoDTO.getQtdEstoque());
                produto = Optional.of(produtoRepository.save(produto.get()));
                return ProdutoDTO.from(produto.get());
            }else{
                throw new EntityNotFoundException();
            }

        }catch (Exception e){
            throw e;
        }

    }

    public ItemProdutoDTO cadastrarItemProduto(String idProduto, Integer qntd){

        ItemProduto itemProduto = new ItemProduto();

        Optional<Produto> produto = produtoRepository.findById(UUID.fromString(idProduto));

        if (produto.isPresent()) {
            itemProduto.setProduto(produto.get());
        } else {
            throw new EntityNotFoundException();
        }

        itemProduto.setQuantidade(qntd);

        itemProdutoRepository.save(itemProduto);

        return ItemProdutoDTO.from(itemProduto);
    }

    public List<ItemProdutoDTO> listarItemProdutos(Map<String, String> params) {

        String itemProdutoId = params.get("id_itemproduto");

        try {

            if (itemProdutoId != null) {

                Optional<ItemProduto> itemProduto = itemProdutoRepository.findById(UUID.fromString(itemProdutoId));

                if (itemProduto.isPresent()) {
                    return List.of(ItemProdutoDTO.from(itemProduto.get()));
                } else {
                    throw new EntityNotFoundException();
                }

            }else{

                List<ItemProdutoDTO> itemProdutos = new ArrayList<>();

                itemProdutoRepository.findAll().forEach(itemProduto -> {
                    itemProdutos.add(ItemProdutoDTO.from(itemProduto));
                });

                return itemProdutos;
            }

        }catch (Exception e){
            throw e;
        }

    }

    public String deletarItemProduto(UUID id) {

        try {

            Optional<ItemProduto> itemProduto = itemProdutoRepository.findById(id);

            if(itemProduto.isPresent()){
                itemProdutoRepository.delete(itemProduto.get());
                return "ItemProduto deletado!";
            }else{
                return "ItemProduto não encontrado!";
            }

        }catch (Exception e){
            throw e;
        }

    }

    public ItemProdutoDTO updateItemProduto(UUID idItemProduto, Integer qntd) {

        try {

            Optional<ItemProduto> itemProduto = itemProdutoRepository.findById(idItemProduto);

            if(itemProduto.isPresent()){

                itemProduto.get().setQuantidade(qntd);
                itemProduto = Optional.of(itemProdutoRepository.save(itemProduto.get()));

                return ItemProdutoDTO.from(itemProduto.get());

            }else{
                throw new EntityNotFoundException();
            }

        }catch (Exception e){
            throw e;
        }

    }

}
