package br.ufmg.coltec.dao;

import java.util.List;
import br.ufmg.coltec.cinema.Cinema;
import br.ufmg.coltec.excecoes.IdExistenteException;

public interface CinemaDAO {

    /** Salva ou atualiza um cinema. Deve lançar a exceção IdExistenteException
     * se um cinema com o mesmo ID já existir
     */
    void salvar(Cinema cinema) throws IdExistenteException;

    Cinema buscarPorId(int id);

    List<Cinema> listarTodos();

    void excluir(int id);

    void carregarDados();

    void salvarDados();
}