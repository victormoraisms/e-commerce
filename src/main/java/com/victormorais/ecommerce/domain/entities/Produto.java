package com.victormorais.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Float preco;

    @Column(name = "qtdEstoque")
    private Integer qtdEstoque;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
    private Fornecedor fornecedor;

}
