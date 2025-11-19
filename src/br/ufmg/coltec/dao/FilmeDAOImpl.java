package br.ufmg.coltec.dao;

import java.util.ArrayList;
import java.util.List;
import br.ufmg.coltec.entidades.Filme;
import java.io.*;

public class FilmeDAOImpl implements FilmeDAO {

    private List<Filme> filmesEmMemoria = new ArrayList<>();

    public FilmeDAOImpl() {
        this.filmesEmMemoria = new ArrayList<>();
        carregarDados(); //Carrega dados ao inicializar
    }

    /**
     * Sobrescrita @override
     */
    @Override
    public void salvar(Filme novofilme) {
        //Implementação simplificada: se não existe, adiciona
        if (buscarPorId(novofilme.getId()) == null) {
            this.filmesEmMemoria.add(novofilme);
        }
    }

    @Override
    public Filme buscarPorId(int id) {
        //Implementação de busca
        for (Filme f : filmesEmMemoria) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public List<Filme> listarTodos(){
        return this.filmesEmMemoria;
    }

    @Override
    public void salvarDados(){
        final String ARQUIVO_FILMES = "filmes.dat";
        try (FileOutputStream fos = new FileOutputStream(ARQUIVO_FILMES);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Escreve a coleção completa de filmes
            oos.writeObject(this.filmesEmMemoria);
            System.out.println("LOG: Dados de Filmes salvos com sucesso em " + ARQUIVO_FILMES);

        } catch (IOException e) {
            System.err.println("ERRO de I/O ao salvar dados de Filmes: " + e.getMessage());
        }
    }

    @Override
    public void carregarDados() {
        final String ARQUIVO_FILMES = "filmes.dat";
        try (FileInputStream fis = new FileInputStream(ARQUIVO_FILMES);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Lê o objeto e faz o cast para a lista de Filmes
            this.filmesEmMemoria = (List) ois.readObject();
            System.out.println("LOG: Dados de Filmes carregados com sucesso de " + ARQUIVO_FILMES);

        } catch (FileNotFoundException e) {
            System.out.println("LOG: Arquivo de Filmes não encontrado. Iniciando com lista vazia.");
            // A lista já está inicializada como ArrayList<> no construtor.
        } catch (IOException e) {
            System.err.println("ERRO de I/O ao carregar dados de Filmes: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("ERRO: Uma classe necessária para desserialização (Filme, por exemplo) não foi encontrada: " + e.getMessage());
        }
    }
}