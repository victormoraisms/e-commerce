package com.victormorais.ecommerce.domain.entities;

import com.victormorais.ecommerce.domain.ItemProdutoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "carrinho_de_compras")
public class CarrinhoDeCompras {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<ItemProduto> itemProdutos;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "data_entrada")
    @CreatedDate
    private LocalDate dataEntrada;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

}
