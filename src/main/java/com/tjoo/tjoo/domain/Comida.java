package com.tjoo.tjoo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("COMIDA")
public class Comida extends Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    protected float peso;
    protected boolean vegano;
    protected String ingredientes;

    public Comida(String nome, float valor, int codigo, float peso, boolean vegano, String ingredientes) {
        this.nome = nome;
        this.valor = valor;
        this.codigo = codigo;
        this.peso = peso;
        this.vegano = vegano;
        this.ingredientes = ingredientes;
    }

    public Comida() {

    }

}
