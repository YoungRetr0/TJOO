package com.tjoo.tjoo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@ToString
@Table(name="pedido")
public abstract class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String descricao;
    protected LocalDateTime data;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    protected Solicitante solicitante;

    public abstract int contaProdutos();

}
