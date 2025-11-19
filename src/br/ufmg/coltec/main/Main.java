package br.ufmg.coltec.main;

import java.time.LocalDateTime;
import java.util.List;

import br.ufmg.coltec.cinema.*;
import br.ufmg.coltec.entidades.*;
import br.ufmg.coltec.excecoes.*;
import br.ufmg.coltec.dao.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("--- Teste Principal do Sistema de Cinema ---");

        /**
         * 1. Instanciação de Filmes
         */
        System.out.println("\n--- 1. Criação e Teste de Filmes ---");

        // Filme 1: Duração 2 horas = 120 minutos = 7200 segundos
        Filme filme1 = new Filme(1, "Polimorfismo Ambulante", 7200);
        Filme filme2 = new Filme(2, "O DAO Oculto", 5700);
        Filme filme3 = new Filme(3, "Herança Fatal", 6600);
        Filme filme4 = new Filme(4, "Aventuras de POO", 8700);
        Filme filme5 = new Filme(5, "Interfaces Secretas", 5100);
        Filme filme6 = new Filme(6, "A Coleção Perdida", 6000);
        Filme filme7 = new Filme(7, "O Mistério do Singleton", 7800);
        Filme filme8 = new Filme(8, "Além da Sobrecarga", 5400);
        Filme filme9 = new Filme(9, "O Enigma da Exceção", 6300);
        Filme filme10 = new Filme(10, "Persistência Profunda", 9000);
        Filme filme11 = new Filme(11, "Encapsulamento Total", 6900);
        Filme filme12 = new Filme(12, "O Ataque dos Getters", 5520);
        Filme filme13 = new Filme(13, "Construtor Silencioso", 5280);
        Filme filme14 = new Filme(14, "O Retorno do Void", 7500);
        Filme filme15 = new Filme(15, "Serialização de Inverno", 8100);
        Filme filme16 = new Filme(16, "O Caminho do Abstract", 6480);
        Filme filme17 = new Filme(17, "Memória Volátil", 5820);
        Filme filme18 = new Filme(18, "O Loop Infinito", 10800);
        Filme filme19 = new Filme(19, "O Novo Construtor", 4500);
        Filme filme20 = new Filme(20, "Teste Final", 7080);

        System.out.println("20 filmes criados (ID 1 a 20).");

        // Exemplo de teste (mantendo a saída original) COM MÉTODOS QUE SURPREENDEM
        System.out.println("\nFilme 1 criado: " + filme1.getNome() + " ");
        System.out.println("Duracao formatada: " + filme1.getDuracaoFormatada() + " ");
        System.out.println(filme1.buscarSinopse() + " ");
        System.out.println("\nFilme 4 Criado:  " + filme4.getNome() + " ");
        System.out.println("Duracao formatada: " + filme4.getDuracaoFormatada() + " ");
        System.out.println(filme4.buscarSinopse() + " ");
        System.out.println("\nFilme 15 criado: " + filme15.getNome() + " ");
        System.out.println("Duracao formatada: " + filme15.getDuracaoFormatada() + " ");
        System.out.println(filme15.buscarSinopse() + " ");

        // 2. Instanciação de Cinemas (Singleton) e tratamento de (IdExistenteException)
        System.out.println("\n--- 2. Instanciação de Cinemas (Singleton e Exceção) ---");

        // Inicializa o Gerenciador (que lida com o DAO)
        GerenciadorDeCinemas gerenciador = new GerenciadorDeCinemas();

        CineCentral central = null;
        CineUFMG ufmg = null;
        CineColtec coltec = null;
        CineCentral centralDuplicada = null;

        try {
            // Instancia Cinema 1 (CineCentral)
            central = CineCentral.getInstance(101, "Av. Contorno");
            //Registra a instância via DAO, onde a validação ID ocorre
            gerenciador.registrarNovoCinema(central);
            System.out.println("\nCinema instanciado: " + central.getNome() + " (ID " + central.getId() + ") ");

            // Instancia Cinema 2 (CineUFMG)
            ufmg = CineUFMG.getInstance(102, "Campus Pampulha");
            gerenciador.registrarNovoCinema(ufmg);
            System.out.println("Cinema instanciado: " + ufmg.getNome() + " (ID " + ufmg.getId() + ") ");

            // Instancia Cinema 3 (CineColtec)
            coltec = CineColtec.getInstance(103, "Coltec Campus");
            gerenciador.registrarNovoCinema(coltec);
            System.out.println("Cinema instanciado: " + coltec.getNome() + " (ID " + coltec.getId() + ") ");

            // --- TESTE FUNCIONAL DE ID EXISTENTE (VIA DAO) ---
            // Tentativa 1: Tentar registrar o CineUFMG novamente (usando a mesma instância, mas o DAO verifica o ID 102)
            System.out.println("\nTentando registrar CineUFMG com ID duplicado (102)...");
            gerenciador.registrarNovoCinema(ufmg);

            System.out.println("\nTentando registrar Cine Central com ID (999)...");
            centralDuplicada = CineCentral.getInstance(999, "Tentativa de Duplicidade");
            gerenciador.registrarNovoCinema(centralDuplicada);

        } catch (IdExistenteException e) {
            /** Esta exceção é capturada, pois o Gerenciador/DAO lançou-a
             * no metodo salvar(Cinema) ao tentar registrar uma duplicidade
             */
            System.out.println("Exceção capturada (IdExistenteException): " + e.getMessage() + " ");
        }

        // 2.1. TESTE FOCADO NO PADRÃO SINGLETON
        System.out.println("\n--- 2.1. Teste de Unicidade de Instância (Singleton) ---");

        CineColtec coltecDuplicado = null;

        try {
            // Tenta obter uma nova instância, mas deve retornar a original (ID 101)
            // ESTA LINHA OBRIGA O TRATAMENTO DA IdExistenteException
            coltecDuplicado = CineColtec.getInstance(888, "Tentativa de Duplicidade");

            System.out.println("\nATENÇÃO! Tentativa de duplicar CineColtec -> Retornou a instância original (ID: " + coltecDuplicado.getId() + ")");

            // O teste funcional do Singleton não deve gerar exceção.
            // Ele apenas verifica se a referência é a mesma.

            // 1. Verificar se as referências de memória são idênticas (prova o Singleton)
            CineColtec coltecOriginal = coltec; // Usando a referência original (ID 103)

            if (coltecOriginal == coltecDuplicado) {
                System.out.println("VERIFICAÇÃO: coltec e coltecDuplicado são a mesma instância (Referência OK).");
            } else {
                System.err.println("FALHA CRÍTICA: O padrão Singleton foi violado.");
            }

            // 2. Verificar se o ID da instância retornada é o ID original.
            if (coltecDuplicado.getId() == 103) {
                System.out.println("VERIFICAÇÃO: O ID da instância duplicada é o original (103).");
            } else {
                System.err.println("FALHA CRÍTICA: O Singleton retornou a instância, mas alterou seu ID.");
            }

        } catch (IdExistenteException e) {
            // Esta exceção seria lançada se a lógica do getInstance() permitisse a criação,
            // mas a validação dentro do construtor de Cinema disparasse a exceção.
            // Para o teste de Singleton, este catch é necessário devido à assinatura.
            System.err.println("Erro inesperado no teste de Singleton (Exceção capturada): " + e.getMessage());
        }

        // 3. Criação de Salas e tratamento de exceção NomeDuplicadoException
        System.out.println("\n--- 3. Criação de Salas e Tratamento de NomeDuplicadoException ---");

        Sala salaParaSessao = null;

        if (central != null) {
            try {
                // Criação de salas válidas
                central.criarSala("Sala Robert De Niro", 150);
                central.criarSala("Sala Anthony Hopkins", 90);
                central.criarSala("Sala Morgan Freeman", 80);
                central.criarSala("Sala Joaquim Phoenix", 70);
                central.criarSala("Sala Jamie Foxx", 120);
                //System.out.println("\nSalas 'Robert De Niro', 'Anthony Hopkins', 'Morgan Freeman', 'Joaquim Phoenix' e 'Jamie Foxx' criadas no Cine Central.");

                System.out.println(central.listarSalas());


                ufmg.criarSala("Sala Cate Blanchett", 35);
                ufmg.criarSala("Sala Viola Davis", 101);
                ufmg.criarSala("Sala Tilda Swinton", 101);
                System.out.println("Salas 'Cate Blanchett', 'Viola Davis' e 'Tilda Swinton' criadas no Cine UFMG.");

                coltec.criarSala("Sala Tim Burton", 120);
                coltec.criarSala("Sala Guillermo del Toro", 120);
                System.out.println("Salas 'Tim Burton' e 'Guillermo Del Toro' criadas no Cine Coltec.");

                /**
                 * TENTATIVA DE CRIAR SALAS COM NOME DUPLICADO NO CINE CENTRAL
                 */
                central.criarSala("Sala Anthony Hopkins", 120);
            } catch (NomeDuplicadoException e) {
                // Captura a exceção, pois 'Sala Anthony Hopkins' já existe
                System.out.println("\nATENÇÃO! Exceção capturada (NomeDuplicadoException): " + e.getMessage() + " ");
            }

            /**
             *  Listar salas do Cine Central, Cine UFMG e Cine Coltec
             */
            List salasCentral = central.listarSalas();
            System.out.println("\nNúmero de salas no Cine Central: " + salasCentral.size());

            List salasColtec = coltec.listarSalas();
            System.out.println("Número de salas no Cine Coltec: " + salasColtec.size());

            List salasUfmg = ufmg.listarSalas();
            System.out.println("Número de salas no Cine UFMG: " + salasUfmg.size());

            if (ufmg != null) {
                try {
                    // Criação de sala no CineUFMG
                    ufmg.criarSala("Sala Zhang Ziyi", 50);
                    System.out.println("\nSala 'Zhang Ziyi' criada no Cine UFMG.");

                    /**
                     * TENTATIVA DE CRIAR SALAS COM NOME DUPLICADO NO CINE UFMG
                     */
                    ufmg.criarSala("Sala Tilda Swinton", 50);
                } catch (NomeDuplicadoException e) {
                    System.out.println("\nATENÇÃO! Exceção capturada (NomeDuplicadoException) -> " + e.getMessage());
                }
            }

            List salasUfmgAtualizada = ufmg.listarSalas();
            System.out.println("\nAtualização do número de salas no Cine UFMG: " + salasUfmgAtualizada.size());

            /**
             * Pegar uma sala para uso nos testes de Sessão -> (Sala Robert De Niro - CineCentral)
             */
            if (!salasCentral.isEmpty()) {
                salaParaSessao = (Sala) salasCentral.get(0); //
            }
        }

        // 4. Teste de Criação de Sessão e Sobrecargas
        System.out.println("\n--- 4. Criação de Sessões e Tratamento de (SalaOcupadaException) ---");

        if (salaParaSessao != null) {

            // Preparar DataHora inicial
            LocalDateTime agora = LocalDateTime.now();
            DataHora dataHoraInicio = new DataHora(agora.plusHours(1));

            // Conflito: 10 minutos depois, mas o filme 1 dura 2 horas (7200s)
            DataHora dataHoraConflito = new DataHora(agora.plusHours(1).plusMinutes(10));

            try {
                // A. Teste do Metodo Básico: CriarSessao(Filme, DataHora)
                System.out.println("\nA. Criando Sessao 1 na Sala Robert De Niro...");

                // Sessão 1 dura 2h. Termina em DataHoraInicio + 2h.
                salaParaSessao.CriarSessao(filme1, dataHoraInicio); //
                System.out.println("Sessao 1 criada com sucesso.");

                // B. Teste de Colisão (SalaOcupadaException)
                System.out.println("\nB. Tentando criar Sessao 2 (Colisao)...");

                // COLISÃO -> dataHoraConflito colide com a Sessão 1
                salaParaSessao.CriarSessao(filme2, dataHoraConflito);
            } catch (SalaOcupadaException e) {
                // A exceção é lançada se o horário de início da nova sessão for anterior ao término da sessão existente
                System.out.println("Exceção capturada (Colisão): " + e.getMessage() + " ");
            }

            // C. Teste da Sobrecarga 1: CriarSessao(Filme) - 30 minutos após o término
            System.out.println("\nC. Criando Sessao 3 (Sobrecarga 1)...");

            // Calcula o término da Sessão 1 e adiciona 30 minutos
            salaParaSessao.CriarSessao(filme2);
            System.out.println("Sessao 3 (sobrecarga 1) criada automaticamente.");

            // D. Teste da Sobrecarga 2: criarSessao(Filme, Horario) - Semanal
            System.out.println("\nD. Criando Sessoes semanais (Sobrecarga 2)...");
            try {
                // Cria 7 sessões recorrentes no horário "18:30"
                salaParaSessao.criarSessao(filme1, "18:30");

                System.out.println("7 sessões semanais criadas (assumindo que nao houve colisao).");
            } catch (SalaOcupadaException e) {
                System.out.println("Exceção capturada (Semanal): Uma das sessoes semanais colidiu. " + e.getMessage());
            }

            // E. Teste de Listar Sessões
            System.out.println("\nE. Listando sessoes na sala: " + salaParaSessao.getNome() + " ");
            List sessoesAgendadas = salaParaSessao.listarSessoes(); //

            //Duas sessões agendadas quando há colisão; Nove sessões agendadas quando não há colisão
            System.out.println("Total de sessoes agendadas: " + sessoesAgendadas.size());
        } else {
            System.out.println("Não foi possível realizar o teste de sessões pois nenhuma sala foi instanciada.");
        }

        // --- 5. Implementação e Teste de Persistência (FilmeDAO) e Vendas (Cliente/Ingresso) ---

        System.out.println("\n--- 5. Teste de Vendas (Ingresso, Cliente) e Exceção de Capacidade ---");

        // 5.1. Inicialização e Teste do Novo DAO (FilmeDAOImpl)
        System.out.println("\nF. Testando injeção e persistência de Filmes via DAO.");

        // Instancia a implementação do DAO para Filmes
        FilmeDAO filmeDAO = new FilmeDAOImpl();

        // Salva os filmes que já foram criados (Simulando o Gerenciador populando o DAO)
        filme1 = new Filme(1, "Aventuras de POO", 7200);
        // Criando um filme que ainda não existia no Main original para testar a função salvar
        Filme filmeNovo = new Filme(30, "A arte da Engenharia", 6000);

        filmeDAO.salvar(filme1);
        filmeDAO.salvar(filmeNovo);

        Filme filmeBuscado = filmeDAO.buscarPorId(30);
        if (filmeBuscado != null) {
            System.out.println("Filme '" + filmeBuscado.getNome() + "' encontrado via FilmeDAO.");
        }

        // 5.2. Teste de Clientes e Venda de Ingresso
        // Criação de Clientes (Novas Entidades)
        Cliente cliente1 = new Cliente(100, "Alice Ruiz", "alice_ruiz@autentica.com");
        Cliente cliente2 = new Cliente(101, "Paulo Leminksy", "leminsky@companhia.com");
        Cliente cliente3 = new Cliente(102, "Augusto Monterroso", "monterroso@independencia.com");

        System.out.println("\nClientes 100, 101 e 102 criados.");

        if (salaParaSessao != null) {

            // Reobtendo a Sessão 1 criada no teste 4 (a primeira sessão agendada)
            List sessoesAgendadas = salaParaSessao.listarSessoes();
            Sessao sessaoAlvo = null;

            if (!sessoesAgendadas.isEmpty()) {
                sessaoAlvo = (Sessao) sessoesAgendadas.get(0);
            }

            if (sessaoAlvo != null) {
                System.out.println("\nG. Teste de Venda de Ingresso (Sessao " + sessaoAlvo.getId() + ")");

                try {
                    // Venda 1: Sucesso
                    Ingresso i1 = salaParaSessao.venderIngressoParaSessao(sessaoAlvo, cliente1, "A1", 45.00);
                    System.out.println("Venda 1: Ingresso #" + i1.getId() + " vendido para " + i1.getCliente().getNome() + ".");

                    // Venda 2: Sucesso
                    Ingresso i2 = salaParaSessao.venderIngressoParaSessao(sessaoAlvo, cliente2, "A2", 45.00);
                    System.out.println("Venda 2: Ingresso #" + i2.getId() + " vendido para " + i2.getCliente().getNome() + ".");

                    // Validação do metodo surpreendente do Ingresso
                    i1.validar();

                    // 5.3. Teste da Nova Exceção: CapacidadeExcedidaException

                    System.out.println("\nH. Tentando exceder a capacidade da Sala (Capacidade: " + salaParaSessao.getCapacidade() + ")");

                    // Simulação: assumindo que a capacidade é 2 para o teste
                    // Nota: A sala "Robert De Niro" tem capacidade 150,
                    // vamos simular vendas até o limite para um teste real:

                    int capacidadeSala = salaParaSessao.getCapacidade();

                    // Vende ingressos até faltar 1 para o limite
                    for (int i = sessaoAlvo.getIngressosVendidos(); i < capacidadeSala - 1; i++) {
                        // Vendas simuladas (i + 1 para ID do cliente)
                        salaParaSessao.venderIngressoParaSessao(sessaoAlvo, cliente3, "B" + (i + 3), 45.00);
                    }
                    System.out.println("Vendidos mais " + (capacidadeSala - 2 - 1) + " ingressos (Total vendido: " + (capacidadeSala - 1) + ")");

                    // Última venda, atingindo o limite
                    salaParaSessao.venderIngressoParaSessao(sessaoAlvo, cliente3, "B" + capacidadeSala, 45.00);
                    System.out.println("Venda final: Sala atingiu capacidade máxima de " + capacidadeSala + ".");

                    // Tentativa que DEVE FALHAR, lançando CapacidadeExcedidaException
                    System.out.println("\nTentando vender um ingresso além do limite...");
                    salaParaSessao.venderIngressoParaSessao(sessaoAlvo, cliente3, "Z99", 45.00);

                } catch (CapacidadeExcedidaException e) {
                    System.out.println("SUCESSO na Captura (CapacidadeExcedidaException): " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro inesperado durante a venda: " + e.getMessage());
                }

                // 5.4. Teste do Status de Ocupação Atualizado
                System.out.println("\nI. Teste de Status de Ocupação (Método que SURPREENDE)");

                // getStatusOcupacao calcula (Vendidos / Capacidade) * 100
                int statusOcupacao = salaParaSessao.getStatusOcupacao();

                System.out.println("Ingressos Vendidos na Sessão: " + sessaoAlvo.getIngressosVendidos());
                System.out.println("Capacidade da Sala: " + salaParaSessao.getCapacidade());
                System.out.println("Status de Ocupação da Sala: " + statusOcupacao + "%");
            }
        } else {
            System.out.println("\nTeste de venda não pôde ser executado: Sala principal não encontrada.");
        }

        // 6. Finalização do Sistema (Garantindo persistência)
        System.out.println("\n--- 6. Finalização do Sistema (Armazenamento Secundário) ---");

        // Simula o gerenciador salvando os dados dos cinemas existentes
        GerenciadorDeCinemas gerenciadorSalvar = new GerenciadorDeCinemas(); // Instancia com CinemaDAOImpl
        gerenciadorSalvar.finalizarSistema(); // Salva dados dos cinemas

        // Salva os dados dos filmes (nova persistência)
        filmeDAO.salvarDados();

        System.out.println("--- Fim do Teste Principal ---");
    }
}