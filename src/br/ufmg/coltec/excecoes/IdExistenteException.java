package br.ufmg.coltec.excecoes;

public class IdExistenteException extends Exception {

    /**
     * Construtor que aceita uma mensagem detalhada do erro.
     * Usado, por exemplo, em br.ufmg.coltec.DAO.CinemaDAOImpl.
     */
    public IdExistenteException(String mensagem) {
        super (mensagem);
    }

    /**
     * Construtor padrão sem mensagem, caso o ID seja simplesmente duplicado.
     */
    public IdExistenteException() {
        super("ID de Cinema já existente. Não é permitido duplicidade de identificadores.");
    }

}