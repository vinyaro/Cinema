package br.ufmg.coltec.excecoes;

public class NomeDuplicadoException extends Exception {

    /**
     * Construtor que aceita uma mensagem detalhada do erro
     * Usado p. ex. nas Classes CineUFMG, CineCentral e Cinema e CineColtec
     */
    public NomeDuplicadoException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor padrão.
     */
    public NomeDuplicadoException() {
        super("Nome de sala já em uso. Não é permitido que duas salas tenham o mesmo nome no cinema.");
    }
}