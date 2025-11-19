package br.ufmg.coltec.excecoes;

/**
 * Exceção lançada quando se tenta vender um ingresso
 * em um sessão cuja capacidade máxima foi atingida
 */
public class CapacidadeExcedidaException extends Exception {

    /**
     * Construtor que aceita mensagem detalhada do erro
     */
    public CapacidadeExcedidaException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor padrão
     */
    public CapacidadeExcedidaException() {
        super("A capacidade máxima da sala foi atingida. Não é possível vender mais ingressos.");
    }
}
