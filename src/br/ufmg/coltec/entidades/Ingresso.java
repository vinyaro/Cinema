package br.ufmg.coltec.entidades;

import java.io.*;

/**
 * Representa um bilhete vendido para uma Sessao específica
 */
public class Ingresso implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Sessao sessao;
    private Cliente cliente;
    private double preco;
    private String numeroAssento; //Apenas uma string para o assento

    public Ingresso(int id, Sessao sessao, double preco, String numeroAssento, Cliente cliente) {
        this.id = id;
        this.sessao = sessao;
        this.preco = preco;
        this.numeroAssento = numeroAssento;
        this.cliente = cliente;
    }

    /**
     * MÉTODOS GET
     */
    public int getId() {
        return id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getPreco() {
        return preco;
    }

    public String getNumeroAssento() {
        return numeroAssento;
    }

    /**
     * Metodo que surpreende: simula a validação do ingresso no ponto de controle
     * @retun true se o ingresso for válido
     */
    public boolean validar() {
        if (this.id <= 0 || this.sessao == null) {
            return false;
        }
        System.out.println("VALIDAÇÃO: Ingresso " + this.id + " para " + sessao.getFilme().getNome() + " verificado.");
        return true;
    }
}