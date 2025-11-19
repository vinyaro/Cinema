package br.ufmg.coltec.cinema;

import java.util.List;
import br.ufmg.coltec.excecoes.IdExistenteException;
import br.ufmg.coltec.excecoes.NomeDuplicadoException;
import java.io.*;

public abstract class Cinema {

    //Atributos privados para encapsulamento
    private int id;
    private String nome;
    private String local;

    //Construtor
    public Cinema(int id, String nome, String local) throws IdExistenteException {
        //Validação do ID aqui (DAO) para (IdExistenteException)
        this.id = id;
        this.nome = nome;
        this.local = local;
    }

    //Métodos GET
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getLocal() { return local; }

    /**
     *  Métodos SET (para modificação de atributos privados, conforme requisito
     */

    /**
     //Permite alteração do nome do cinema.
     * @param nome Novo nome.
     */
    public void setNome(String nome) { this.nome = nome; }

    /**
     * Permite alteração do local.
     * @param local Novo local.
     */
    public void setLocal(String local) { this.local = local; }

    /**
     * 1. Metodo abstrato criarSala()
     *  Deve lançar excecoes.NomeDuplicadoException
     */
    public abstract void criarSala(String nomeSala, int capacidade) throws NomeDuplicadoException;

    /**
     * 2. Metodo abstrato listarSalas()
     * O tipo de retorno é List
     */
    public abstract List listarSalas();

    /**
     * Nota: O metodo setId geralmente não é fornecido para IDs únicos após a criação,
     * mas se fosse necessário, deveria ser:
     * public void setId(int id) {this.id = id;}
      */
}