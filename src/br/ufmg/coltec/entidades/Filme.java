package br.ufmg.coltec.entidades;

import java.io.*;

public class Filme implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private int id; //Identificador do filmes
    private String nome; // Nome do filme
    long duracao_s; //Duração do filme em segundos

    //Construtor
    public Filme(int id, String nome, long duracao_s) {
        this.id = id;
        this.nome = nome;
        this.duracao_s = duracao_s;
    }

    //Métodos GET
    public int getId() {return id;}
    public String getNome() {return nome;}
    public long getDuracao_s() {return duracao_s;}

    //Métodos SET
    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}

    //Permitir alteração da duração
    public void setDuracao_s(long duracao_s) {this.duracao_s = duracao_s;}

    /**
     * Metodo que surpreende 1: Converte a duração em segundos para o formato HH:MM:SS
     */
    public String getDuracaoFormatada(){
        long segundosTotal = this.duracao_s;
        long horas = segundosTotal / 3600;
        long minutos = (segundosTotal % 3600) / 60;
        long segundos = segundosTotal % 60;

        //Retorna a duração no formato HH:MM:SS
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    /**
     * Metodo que surpreende 2: Simula a busca de sinopse online (placeholder)
     */
    public String buscarSinopse(){
        return "Sinopse do filme '" + this.nome + "': Esta é uma simulação de busca de sinopse em uma API externa";
    }
}