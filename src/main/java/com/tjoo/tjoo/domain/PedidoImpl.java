package com.tjoo.tjoo.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class PedidoImpl extends Pedido {

    protected float total;

    public <E> PedidoImpl(String descricao, LocalDateTime data, float total, Solicitante solicitante) {
        this.descricao = descricao;
        this.data = data;
        this.total = total;
        this.solicitante = solicitante;
    }

    public PedidoImpl() {

    }

    public int contaProdutos() {
        return getProdutos().size();
    }

}
