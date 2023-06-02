package com.victormorais.ecommerce.services;

import com.victormorais.ecommerce.domain.EnderecoDTO;
import com.victormorais.ecommerce.domain.FornecedorDTO;
import com.victormorais.ecommerce.domain.entities.Endereco;
import com.victormorais.ecommerce.domain.entities.Fornecedor;
import com.victormorais.ecommerce.domain.entities.Produto;
import com.victormorais.ecommerce.domain.entities.Usuario;
import com.victormorais.ecommerce.domain.repository.FornecedorRepository;
import com.victormorais.ecommerce.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public FornecedorDTO cadastrarFornecedor(FornecedorDTO fornecedorDTO, String produtoId){

        Fornecedor fornecedor = new Fornecedor();

        Optional<Produto> produto = produtoRepository.findById(UUID.fromString(produtoId));

        if (produto.isPresent()) {
            fornecedor.setProduto(produto.get());
        } else {
            throw new EntityNotFoundException();
        }

        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCNPJ(fornecedorDTO.getCNPJ());

        fornecedorRepository.save(fornecedor);

        return FornecedorDTO.from(fornecedor);
    }

    public List<FornecedorDTO> listarFornecedores(Map<String, String> params) {

        String produtoId = params.get("id_produto");

        try {

            if (produtoId != null) {

                Optional<Produto> produto = produtoRepository.findById(UUID.fromString(produtoId));

                if (produto.isPresent()) {

                    return List.of(FornecedorDTO.from(fornecedorRepository.findByProduto(produto.get())));

                } else {
                    throw new EntityNotFoundException();
                }

            }else{

                List<FornecedorDTO> fornecedores = new ArrayList<>();

                fornecedorRepository.findAll().forEach(fornecedor -> {
                    fornecedores.add(FornecedorDTO.from(fornecedor));
                });

                return fornecedores;
            }

        }catch (Exception e){
            throw e;
        }

    }

    public String deletarFornecedor(UUID id) {

        try {

            Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);

            if(fornecedor.isPresent()){
                fornecedorRepository.delete(fornecedor.get());
                return "Fornecedor deletado!";
            }else{
                return "Fornecedor não encontrado!";
            }

        }catch (Exception e){
            throw e;
        }

    }

    public FornecedorDTO updateFornecedor(UUID idFornecedor, FornecedorDTO fornecedorDTO) {

        try {

            Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

            if(fornecedor.isPresent()){
                if(fornecedorDTO.getNome() != null) fornecedor.get().setNome(fornecedorDTO.getNome());
                if(fornecedorDTO.getCNPJ() != null) fornecedor.get().setCNPJ(fornecedorDTO.getCNPJ());
                fornecedor = Optional.of(fornecedorRepository.save(fornecedor.get()));
                return FornecedorDTO.from(fornecedor.get());
            }else{
                throw new EntityNotFoundException();
            }

        }catch (Exception e){
            throw e;
        }

    }

}
