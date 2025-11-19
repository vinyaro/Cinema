package br.ufmg.coltec.dao;

import br.ufmg.coltec.entidades.Filme;

/**
 * Interface que representa o DAO para a entidade Filme
 */
public interface FilmeDAO {

    //Salva ou atualiza um filme
    void salvar(Filme filme);

    //Buscar um filme pelo ID
    Filme buscarPorId(int id);

    //Carrega dados de Filmes do Armazenamento Secundário
    void carregarDados();

    //Salva os dados de Filmes no Armazenamento Secundário
    void salvarDados();
}