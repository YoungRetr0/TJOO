package com.tjoo.tjoo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("BEBIDA")
public class Bebida extends Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    protected boolean gelada;
    protected float tamanho;
    protected String marca;

    public Bebida(String nome, float valor, int codigo, boolean gelada, float tamanho, String marca) {
        this.nome = nome;
        this.valor = valor;
        this.codigo = codigo;
        this.gelada = gelada;
        this.tamanho = tamanho;
        this.marca = marca;
    }

    public Bebida() {

    }

}
