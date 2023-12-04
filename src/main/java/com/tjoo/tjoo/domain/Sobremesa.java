package com.tjoo.tjoo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("SOBREMESA")
public class Sobremesa extends Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    protected float quantidade;
    protected boolean doce;
    protected String informacao;

    public Sobremesa(String nome, float valor, int codigo, float quantidade, boolean doce, String informacao) {
        super.nome = nome;
        super.valor = valor;
        super.codigo = codigo;
        this.quantidade = quantidade;
        this.doce = doce;
        this.informacao = informacao;
    }

    public Sobremesa() {

    }

}
