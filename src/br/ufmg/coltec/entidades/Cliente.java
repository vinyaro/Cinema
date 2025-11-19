package br.ufmg.coltec.entidades;

import java.io.*;

/**
 * Representa um cliente que compra ingressos
 * Segue o padrão de atributos privados e metodos GET e SET
 */
public class Cliente implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String email;

    public Cliente(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    //MÉTODOS GET
    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getEmail() {return email;}

    //MÉTODOS SET
    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(String email) {this.email = email;}
    //public void setId(int id) {this.id = id;}
}