package br.ufmg.coltec.cinema;

import java.util.List;
import java.util.ArrayList;
import br.ufmg.coltec.entidades.Sala;
import br.ufmg.coltec.excecoes.IdExistenteException;
import br.ufmg.coltec.excecoes.NomeDuplicadoException;
import java.io.*;

public class CineCentral extends Cinema {

    private static CineCentral instancia;
    private List<Sala> salas = new ArrayList<>();

    //Construtor privado
    private CineCentral(int id, String local) throws IdExistenteException {
        super(id,"Cine Central",local);
    }

    //Metodo estático para garantir unica instancia
    public static CineCentral getInstance(int id, String local) throws IdExistenteException {
        if (instancia == null) {
            //A exceção IdExistenteException é tratada no ponto de chamada ou propagada
            instancia = new CineCentral(id, local);
        }
        return instancia;
    }

    /**
     *Implementacao dos MÉTODOS ABSTRATOS (Polimorfismo)
     */
    @Override
    public void criarSala(String nomeSala, int capacidade) throws NomeDuplicadoException {
        //Verifica se o nome da sala já existe no CineCentral
        for (Sala salaExistente : this.salas) {
            if (salaExistente.getNome().equalsIgnoreCase(nomeSala)) {
                throw new NomeDuplicadoException("Sala com nome '" + nomeSala + "' já existe no Cine Central.");
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