package br.ufmg.coltec.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.coltec.excecoes.SalaOcupadaException;
import br.ufmg.coltec.excecoes.CapacidadeExcedidaException;
import java.io.*;

public class Sala {

    private String nome; // Nome da sala
    private int capacidade; // Capacidade da sala
    private List<Sessao> sessoes = new ArrayList<>(); // Lista de sessões agendadas na sala

    /**
     * Construtor padrão da classe Sala. Inicializa o nome e a capacidade,
     * e garante que a lista de sessões esteja pronta.
     * Atributos: nome (String) e capacidade (int).
     */

   public Sala(String nome, int capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.sessoes = new ArrayList<>();
    }

    //Métodos GET e SET conforme requisitos

    public String getNome() {return nome;}
    public int getCapacidade() {return capacidade;}
    public List<Sessao> getSessoes() {return sessoes;}

    public void setNome(String nome) {this.nome = nome;}
    public void setCapacidade(int capacidade) {this.capacidade = capacidade;}

    /**
     * Ponto de acesso para a venda de ingressos, delegando a responsabilidade para a Sessão
     * @throws CapacidadeExcedidaException se a sessão estiver cheia.
     */

    public Ingresso venderIngressoParaSessao(Sessao sessao, Cliente cliente, String assento, double preco) throws CapacidadeExcedidaException{
        //Verificação básica se a sessao pertence à sala
        if (!this.sessoes.contains(sessao)) {
            throw new IllegalArgumentException("Sessao não pertence a esta sala");
        }
        return sessao.venderIngresso(cliente, assento, preco);
    }

    /**
     * MÉTODOS que SURPREENDEM
     * Implementação calcula (Total de Assentos Vendidos / Capacidade) * 100
     * Retorna a ocupação (em %) da primeira sessão agendada (exemplo).
     */

    public int getStatusOcupacao(){
        if (sessoes.isEmpty()) {
            return 0;
        }
        // Pega a primeira sessão agendada para cálculo de ocupação
        Sessao proximaSessao = (Sessao) sessoes.get(0);
        int vendidos = proximaSessao.getIngressosVendidos();
        int capacidadeTotal = this.capacidade; // Capacidade da sala

        if (capacidadeTotal == 0) return 0;

        return (int) ((vendidos / (double) capacidadeTotal) * 100);
    }

    public void limparSessoesAntigas(DataHora dataAtual){
        //Lógica para remover sessões cuja DataHora seja anterior à dataAtual
    }

    //1. Metodo basico - Cria Sessao especificando filme e horario exato

    public void CriarSessao(Filme filme, DataHora dataHora) throws SalaOcupadaException {
        // Lógica de Verificação de Colisão (Obrigatório para a exceção SalaOcupadaException)
        for (Sessao sessaoExistente : sessoes) {
            DataHora terminoNova = dataHora.adicionarDuracao(filme.getDuracao_s());
            DataHora terminoExistente = sessaoExistente.calcularHorarioTermino();

            if (dataHora.isBefore(terminoExistente) && terminoNova.isAfter(sessaoExistente.getDataHora())) {
                throw new SalaOcupadaException("O horário de início da nova sessão colide com a sessão existente. ");
            }
        }
        //Criação e inicialização correta da classe Sessao
        int novoId = this.sessoes.size() + 1; //ID simulado

        //Passa o ID, a sala atual (this) e as classes Filme e DataHora
        Sessao novaSessao = new Sessao(novoId, this, filme, dataHora);
        this.sessoes.add(novaSessao);
    }

    /**
     * Lista as sessões agendadas para esta sala.
     * @return Lista de objetos da classe Sessao.
     */
    public List<Sessao> listarSessoes(){
        return this.sessoes;
    }

    /**
     * Sobrecarga 1: Cria 30 minutos depois do último termino do último filme
     * Cria uma sessao automaticamente, agendando após 30 minutos
     */

    public void CriarSessao(Filme filme){
        if (this.sessoes.isEmpty()) {
            return;
        }
        Sessao ultimaSessao = this.sessoes.get(this.sessoes.size() - 1);
        DataHora horarioTermino = ultimaSessao.calcularHorarioTermino();
        DataHora novoHorario = horarioTermino.adicionarMinutos(30);
        try {
            // Assume que DataHora e Filme são passados
            this.CriarSessao(filme, novoHorario); // Chama o metodo básico para validação
        } catch (SalaOcupadaException e) {
            // A sobrecarga lança a exceção se a nova sessão colidir com outra após o cálculo (improvável nesta lógica, mas possível)
            System.err.println("ERRO INTERNO: Falha ao agendar sessão automática: " + e.getMessage());
        }
    }

    /**
     *   Sobrecarga 2: Cria uma sessão para o filme em todos os dias da semana, no mesmo horario
     *   Cria sessoes semanais recorrentes no mesmo horario especifico
     */
    public void criarSessao(Filme filme, String horario) throws SalaOcupadaException {
        //Obter ponto de partida, supondo que é a data atual, por isso utilização de LocalDateTime
        LocalDateTime dataBase = LocalDateTime.now();

        //iteração sobre os próximos 7 dias (incluindo o dia de hoje)
        for (int i = 0; i < 7; i++){

            //Calcula a data para o dia da iteração
            LocalDateTime dataDoDia = dataBase.plusDays(i);

            /**
             * Combina a data do dia com o horario fornecido (String)
             * Assume-se que o 'horário' está no formato  HH:MM
             * NOTA: Para combinar a data (DataDoDia) com o horário (String),
             * é preciso usar métodos auxiliares de conversão e manipulação do tempo
             * Simularemos a conversão de String 'horário' para LocalTime e sua combinação
             * */

            //Simulação da extração de Hora e Minuto do String 'horário' (Ex: 14:30)
            String[] partesHorario = horario.split(":");
            int hora = Integer.parseInt(partesHorario[0]);
            int minuto =  Integer.parseInt(partesHorario[1]);

            //Cria a DataHora completa para o agendamento
            LocalDateTime ldtAgendamento = dataDoDia
                    .withHour(hora)
                    .withMinute(minuto)
                    .withSecond(0)
                    .withNano(0);

            //Cria objeto DataHora utilizando a classe DataHora implementada
            DataHora dataHoraAgendamento = new DataHora(ldtAgendamento);

            /**
             * Chama o metodo basico (CriarSessao(entidades.Filme, entidades.DataHora)) para cada um dos 7 agendamentos
             * garantindo a validação da excecoes.SalaOcupadaException
             * Se este metodo falhar (lançar excecoes.SalaOcupadaException), a exceção é propagada e a iteração é interrompida
             */

            //o METODO base lança a exceção se houver colisão
            this.CriarSessao(filme, dataHoraAgendamento);
        }
    }
}