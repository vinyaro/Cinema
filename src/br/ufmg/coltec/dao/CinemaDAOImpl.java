package br.ufmg.coltec.dao;

import java.util.List;
import java.util.ArrayList;
import br.ufmg.coltec.cinema.Cinema;
import br.ufmg.coltec.excecoes.IdExistenteException;
import java.io.*;

public class CinemaDAOImpl implements CinemaDAO {

    private List<Cinema> cinemasEmMemoria = new ArrayList<>(); //Coleção interna para gestão

    public CinemaDAOImpl() {
        this.cinemasEmMemoria = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void salvar(Cinema novoCinema) throws IdExistenteException {
        //Verifica se o ID já existe
        if (buscarPorId(novoCinema.getId()) != null){
            throw new IdExistenteException("ID de cinema. Cinema já em uso: " + novoCinema.getId());
        }
        this.cinemasEmMemoria.add(novoCinema);
    }

    /**
     * Sobrescrita @override
     */
    @Override
    public Cinema buscarPorId(int id){
        //Implementação de busca
        for (Cinema c : cinemasEmMemoria){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Cinema> listarTodos() {
        return this.cinemasEmMemoria;
    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public void salvarDados(){
        final String ARQUIVO_CINEMAS = "cinemas.dat";
        try (FileOutputStream fos = new FileOutputStream(ARQUIVO_CINEMAS);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Escreve a coleção completa de cinemas no fluxo de bytes
            oos.writeObject(this.cinemasEmMemoria);
            System.out.println("LOG: Dados de Cinemas salvos com sucesso em " + ARQUIVO_CINEMAS);

        } catch (IOException e) {
            System.err.println("ERRO de I/O ao salvar dados dos Cinemas: " + e.getMessage());
        }
    }

    @Override
    public void carregarDados() {
        final String ARQUIVO_CINEMAS = "cinemas.dat";
        try (FileInputStream fis = new FileInputStream(ARQUIVO_CINEMAS);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            /**
             *Lê o objeto e faz o cast para a lista de Cinemas
             * Nota: O cast de Object para List é necessário e assume que as classes de Entidades implementam Serializable.
             */
            this.cinemasEmMemoria = (List) ois.readObject();
            System.out.println("LOG: Dados de Cinemas carregados com sucesso de " + ARQUIVO_CINEMAS);

        } catch (FileNotFoundException e) {
            // Se o arquivo não existir (primeira execução), a lista continua vazia (ArrayList<>() no construtor).
            System.out.println("LOG: Arquivo de Cinemas não encontrado. Iniciando com lista vazia.");
            // Não é necessário inicializar novamente this.cinemasEmMemoria, pois já foi feito no construtor.
        } catch (IOException e) {
            System.err.println("ERRO de I/O ao carregar dados dos Cinemas: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("ERRO: Uma classe necessária para desserialização não foi encontrada: " + e.getMessage());
        }
    }
}