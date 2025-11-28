# 🎞️ Sistema de Gerenciamento de Cinema - Trabalho Final de POO

## 🎬 Visão Geral do Projeto

Este repositório contém o **Trabalho Prático Final** da disciplina de **Programação Orientada a Objetos (POO)**. O projeto simula um sistema de gerenciamento de cinemas em Java, focado na aplicação rigorosa dos pilares da POO, padrões de *design* e gestão de exceções customizadas.

O código-fonte, especialmente a classe `Main`, serve como um *driver* completo para testes, validando a arquitetura em camadas e o comportamento de exceções de domínio.

## 🎯 Objetivos de Aprendizagem e Requisitos POO

O objetivo central deste trabalho é demonstrar a aplicação prática dos seguintes conceitos e requisitos de software:

| Pilar/Requisito | Implementação no Projeto
| :--- | :---
| **Herança e Polimorfismo** | Uso da classe abstrata `Cinema` e herança nas classes concretas (`CineCentral`, `CineUFMG`, `CineColtec`). Métodos abstratos como `criarSala` garantem o polimorfismo.
| **Sobrecarga** (@Overloading) | Implementação de três versões do método `criarSessao` na classe `Sala`, incluindo agendamento automático (30 minutos após o término) e agendamento semanal recorrente.
| **Interfaces e Desacoplamento** | Utilização do padrão **DAO (Data Access Object)** com interfaces (`CinemaDAO`, `FilmeDAO`). O `GerenciadorDeCinemas` depende da interface `CinemaDAO`, seguindo o **Princípio da Inversão de Dependência (DIP)**, descrito no padrão de desenvolvimento SOLID, e garantindo baixo acoplamento.
| **Armazenamento Secundário** | Persistência do estado do sistema (`List` de Cinemas e Filmes) usando a interface `java.io.Serializable` e serialização de objetos (`ObjectOutputStream`/`ObjectInputStream`) em arquivos `.dat`.
| **Encapsulamento** | Uso rigoroso de modificadores de acesso `private` para atributos e métodos `public` (getters/setters) para controle de acesso.

## ⚙️ Arquitetura e Padrões de Design

A arquitetura do projeto foi desenhada para garantir robustez e manutenibilidade, priorizando o desacoplamento da lógica de negócio.

### 1. Padrão Singleton para Gestão de Instâncias

O requisito de não ser possível ter mais de uma instância de cada tipo de cinema concreto foi atendido pelo padrão **Singleton**.

*   **Implementação:** As classes `CineCentral`, `CineUFMG` e `CineColtec` possuem construtores privados e um método estático `getInstance(int id, String local)`.
*   **Comprovação (Teste Didático):** A classe `Main` realiza um teste explícito: se tentarmos chamar `getInstance()` uma segunda vez, mesmo com um ID diferente (ex: 999), o método retorna a referência original, ignorando o novo ID, comprovando a unicidade da instância.

### 2. Desacoplamento via DAO e DIP (Inversão de Dependência)

O design segue o **Princípio da Inversão de Dependência (DIP)**, padrão de desenvolvimento SOLID.

*   **Camada de Persistência:** A responsabilidade de salvar, buscar e persistir cinemas e filmes é segregada em `CinemaDAOImpl` e `FilmeDAOImpl`.
*   **Abstração:** O `GerenciadorDeCinemas` *não* depende da implementação concreta (`CinemaDAOImpl`), mas sim do contrato (`CinemaDAO`). Isso isola a lógica de negócio (Gerenciador) dos detalhes de infraestrutura (DAO que lida com arquivos `.dat`), tornando o sistema flexível a mudanças futuras na tecnologia de persistência.

## ⚠️ Gestão Robusta de Exceções Customizadas

A correta gestão de exceções é fundamental para a robustez do sistema, e neste projeto, as exceções customizadas são lançadas e capturadas na camada arquitetural apropriada.

| Exceção Customizada | Onde é Lançada | Validação Crítica
| :--- | :--- | :---
| **IdExistenteException** | **CinemaDAOImpl.salvar()** | Impede que dois cinemas sejam registrados com o mesmo ID. O teste ocorre na camada de persistência (DAO), e não na criação do Singleton, garantindo o desacoplamento.
| **NomeDuplicadoException** | **Cine[X].criarSala()** | Garante que não existam duas salas com o mesmo nome dentro do *mesmo cinema*, verificando a lista interna de salas.
| **SalaOcupadaException** | **Sala.CriarSessao()** | Lógica crucial para evitar colisões temporais. Verifica se o horário de início da nova sessão é anterior ao término da sessão existente **E** se o término da nova sessão é posterior ao início da sessão existente.
| **CapacidadeExcedidaException** | **Sessao.venderIngresso()** | Impede a venda de ingressos se o número atual de vendas atingir ou exceder a capacidade máxima da sala.


## ✨ Métodos que "Surpreendem" (Demonstração de Funcionalidades Extras)

Atendendo ao requisito do trabalho "Me surpreenda!!!", incluí métodos utilitários que adicionam valor e clareza ao modelo de domínio:

1.  **`Filme.getDuracaoFormatada()`:** Converte a duração (`long duracao_s`) de segundos para o formato `HH:MM:SS`, facilitando a visualização em logs ou interfaces.
2.  **`Filme.buscarSinopse()`:** Simula a busca de informações em uma API externa, retornando um *placeholder*.
3.  **`Sala.getStatusOcupacao()`:** Calcula a porcentagem de ocupação da *primeira sessão agendada* na sala, usando a fórmula `(vendidos / capacidadeTotal) * 100`.

## 🚀 Como Executar o Projeto

1.  **Compilação:** Certifique-se de que todas as classes estão compiladas corretamente.
2.  **Execução:** A execução deve começar pela classe principal: `br.ufmg.coltec.main.Main`.
3.  **Persistência:** Na primeira execução, o sistema criará os arquivos `cinemas.dat` e `filmes.dat`. O `Main.main()` finaliza o sistema chamando `gerenciadorSalvar.finalizarSistema()` e `filmeDAO.salvarDados()`, garantindo que os dados em memória sejam salvos antes do encerramento. Nas execuções subsequentes, o sistema carregará esses dados ao inicializar o DAO.

---
👽BUSQUEM CONHECIMENTO
