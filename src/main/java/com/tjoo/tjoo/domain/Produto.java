package com.tjoo.tjoo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="produto")
@DiscriminatorColumn(name = "tipo_produto", discriminatorType = DiscriminatorType.STRING)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String nome;
    protected float valor;
    protected int codigo;

    public Produto() {

    }

}
