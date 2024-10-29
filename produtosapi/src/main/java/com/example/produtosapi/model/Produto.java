package com.example.produtosapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Produto {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String descricao;

    private Double preco;
}
