package br.ufmg.coltec.cinema;

import java.util.List;
import java.util.ArrayList;
import br.ufmg.coltec.entidades.Sala;
import br.ufmg.coltec.excecoes.IdExistenteException;
import br.ufmg.coltec.excecoes.NomeDuplicadoException;
import java.io.*;

public class CineUFMG extends Cinema {

    private static CineUFMG instancia;
    private List<Sala> salas = new ArrayList<>();

    //Construtor privado
    private CineUFMG(int id, String local) throws IdExistenteException {
        super(id, "Cine UFMG - Campus Pampulha", local);
    }

    //Metodo estático para garantir unica instancia
    public static CineUFMG getInstance(int id, String local) throws IdExistenteException {
        if (instancia == null) {
            //A exceção IdExistenteException é tratada no ponto de chamada ou propagada
            instancia = new CineUFMG(id, local);
        }
        return instancia;
    }

    /**
     *Implementacao dos MÉTODOS ABSTRATOS (Polimorfismo)
     */
    @Override
    public void criarSala(String nomeSala, int capacidade) throws NomeDuplicadoException {
        //Verifica se o nome da sala já existe no CineUFMG
        for (Sala salaExistente : this.salas) {
            if (salaExistente.getNome().equalsIgnoreCase(nomeSala)) {
                throw new NomeDuplicadoException("Sala com nome '" + nomeSala + "' já existe no CineUFMG.");
            }
        }
        // Cria e adiciona a nova sala
        Sala novaSala = new Sala(nomeSala, capacidade);
        this.salas.add(novaSala);
    }

    @Override
    public List<Sala> listarSalas(){
        //Retorna a lista de salas gerenciadas internamente
        return this.salas;
    }
}