package br.ufmg.coltec.entidades;

import java.util.ArrayList;
import java.util.List;
import br.ufmg.coltec.excecoes.CapacidadeExcedidaException;
import java.io.*;

public class Sessao {

    private int id;
    private Sala sala;
    private Filme filme;
    private DataHora dataHora;

    //atributos para controle de venda
    private int ingressosVendidos;
    private List<Ingresso> listaIngressos = new ArrayList<>();

    //Construtor
    public Sessao(int id,  Sala sala, Filme filme, DataHora dataHora) {
        this.id = id;
        this.sala = sala;
        this.filme = filme;
        this.dataHora = dataHora;
        this.ingressosVendidos = 0;
        this.listaIngressos = new ArrayList<>();
    }

    /**
     * Realiza a venda de um ingresso, verificando a capacidade
     * @throws CapacidadeExcedidaException se a sala estiver cheia
     */
    public Ingresso venderIngresso(Cliente cliente, String assento, double precoIngresso)
            throws CapacidadeExcedidaException{
        //Verifica se a venda excederá a capacidade máxima da sala
        if (this.ingressosVendidos >= this.sala.getCapacidade()){
            throw new CapacidadeExcedidaException("Sessão cheia. Capacidade Máxima: " + this.sala.getCapacidade());
        }
        //Cria novo objeto Ingresso
        int novoIdIngresso = this.listaIngressos.size() + 1;
        Ingresso novoIngresso = new Ingresso(novoIdIngresso, this, precoIngresso, assento, cliente);

        this.listaIngressos.add(novoIngresso);
        this.ingressosVendidos++;

        return novoIngresso;
    }

    /**
     * MÉTODOS GET
     */
    public int getIngressosVendidos(){
        return ingressosVendidos;
    }

    public Filme getFilme() {
        return filme;
    }

    public int getId() {
        return id;
    }

    // Metodo que surpreende 1: Cálculo crucial para a exceção SalaOcupadaException
    public DataHora calcularHorarioTermino(){
        long duracaoEmSegundos = this.filme.getDuracao_s();
        return this.dataHora.adicionarDuracao(duracaoEmSegundos);
    }

    public DataHora getDataHora() {
        return dataHora;
    }

    public void imprimirIngressos(int quantidade){
        System.out.println("Imprimindo " + quantidade + " ingressos para: " + filme.getNome());
    }
}