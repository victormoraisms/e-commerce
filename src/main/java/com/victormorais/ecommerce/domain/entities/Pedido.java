package com.victormorais.ecommerce.domain.entities;

import com.victormorais.ecommerce.domain.PedidoDTO;
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
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_id")
    private Usuario usuario;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_carrinho")
    private CarrinhoDeCompras carrinhoDeCompras;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "desconto")
    private Double desconto;

    @Column(name = "status")
    private String status;

    @Column(name = "tipoPagamento")
    private TipoPagamento tipoPagamento;

    @Column(name = "data_entrada")
    @CreatedDate
    private LocalDate dataEntrada;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    public enum TipoPagamento{
        CREDITO, DEBITO, PIX
    }

}
