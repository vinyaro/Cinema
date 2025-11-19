package br.ufmg.coltec.entidades;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.*;

public class DataHora {

    private LocalDateTime dataHora;
    // private LocalTime horaInicio;
    // private LocalTime horaFim;

    /**
     * Construtor que inicializa o objeto DataHora.
     */
    public DataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    //MÉTODOS GET
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    //MÉTODOS SET
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    //-----------------------------------------------------
    //Métodos utilitários ESSENCIAIS para as outras classes
    //-----------------------------------------------------

    /**
     * Verifica se esta DataHora é anterior à DataHora fornecida
     * Usado na lógica dos métodos Sala.limparSessoesAntigas e Sessao.CriarSessao
     */
    public boolean isBefore(DataHora outraDataHora) {
        return this.dataHora.isBefore(outraDataHora.dataHora);
    }

    public boolean isAfter(DataHora outraDataHora) {
        return this.dataHora.isAfter(outraDataHora.dataHora);
    }

    /**
     * Adiciona uma duração (em segundos) à DataHora, retornando um novo objeto
     */
    public DataHora adicionarDuracao(long duracaoSegundos) {
        LocalDateTime novoTempo = this.dataHora.plusSeconds(duracaoSegundos);
        return new DataHora(novoTempo);
    }

    /**
     *Adiciona a quantidade de minutos à DataHora, usado na sobrecarga de CriarSessao
     */
    public DataHora adicionarMinutos(long minutos) {
        LocalDateTime novoTempo = this.dataHora.plusMinutes(minutos);
        return new DataHora(novoTempo);
    }
    // Outros métodos como toString, parse etc... podem ser adicionados conforme a necessidade
}