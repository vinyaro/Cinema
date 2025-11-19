package br.ufmg.coltec.cinema;

import br.ufmg.coltec.dao.CinemaDAO;
import br.ufmg.coltec.dao.CinemaDAOImpl;
import br.ufmg.coltec.excecoes.IdExistenteException;

public class GerenciadorDeCinemas {

    // Trabalha sempre com a Interface CinemaDAO
    private CinemaDAO dao;

    public GerenciadorDeCinemas() {
        // A implementação concreta é instanciada
        this.dao = new CinemaDAOImpl();
    }

    public void registrarNovoCinema(Cinema c) {
        try {
            dao.salvar(c);
        } catch (IdExistenteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void finalizarSistema() {
        dao.salvarDados(); // Garante o salvamento ao finalizar
    }
}