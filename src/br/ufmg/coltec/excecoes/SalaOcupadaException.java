package br.ufmg.coltec.excecoes;

public class SalaOcupadaException extends Exception {

    /**
     * Construtor que aceita uma mensagem detalhada do erro.
     * Usado, por exemplo, na lógica de verificação de colisão da Sala.
     */
        public SalaOcupadaException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor padrão
     */
    public SalaOcupadaException() {
        super("Sala ocupada");
    }
}