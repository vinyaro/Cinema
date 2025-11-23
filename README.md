# Sistema de Gerenciamento de Cinema em Java

Este projeto implementa um sistema de gerenciamento de cinema robusto, servindo como referência didática para a aplicação rigorosa dos pilares da Programação Orientada a Objetos (POO).

## ⚙️ Arquitetura e Padrões de Design

O design do sistema foi guiado por princípios de desacoplamento e coesão, utilizando os seguintes padrões:

*   **Padrão Singleton:** Implementado nas classes concretas de cinema (`CineCentral`, `CineUFMG`, `CineColtec`) para garantir que não haja mais de uma instância de cada tipo de cinema.
*   **Padrão DAO (Data Access Object):** Utilizado para isolar a lógica de persistência e garantir o **Princípio da Inversão de Dependência (DIP)**. A classe `GerenciadorDeCinemas` trabalha exclusivamente com a interface `CinemaDAO`.
*   **Polimorfismo e Herança:** Modelagem através da classe abstrata `Cinema`, que define o contrato de métodos como `criarSala` e `listarSalas`.
*   **Sobrecarga (Overloading):** Aplicação de sobrecarga didática no método `criarSessao`, incluindo agendamento automático (30 minutos após o término) e agendamento semanal recorrente.

## 🛡️ Gestão de Exceções

O sistema implementa gestão rigorosa de exceções customizadas, garantindo que as regras de negócio sejam validadas nas camadas corretas:

| Exceção Customizada | Finalidade e Verificação |
| :--- | :--- |
| `IdExistenteException` | Impede cinemas com IDs duplicados. Verificada na camada **DAO** (`CinemaDAOImpl.salvar`). |
| `NomeDuplicadoException` | Impede salas com o mesmo nome dentro do mesmo cinema. Lançada no método `criarSala`. |
| `SalaOcupadaException` | Garante que o horário de uma nova sessão não colida temporalmente com uma sessão existente. A lógica de colisão está em `Sala.CriarSessao`. |
| `CapacidadeExcedidaException` | Lançada ao tentar vender ingresso se a capacidade máxima da sala for atingida. |

## 💾 Persistência e Funcionalidades Adicionais

*   **Armazenamento Secundário (Serialização):** O sistema salva e carrega automaticamente as coleções de objetos (Cinemas e Filmes) em arquivos binários (`.dat`) ao iniciar e finalizar, utilizando `ObjectOutputStream` e `ObjectInputStream`.
*   **Métodos "Surpreendentes":** Demonstração de utilidade com métodos como `Filme.getDuracaoFormatada()` (conversão de segundos para HH:MM:SS) e `Sala.getStatusOcupacao()` (cálculo percentual de lotação).
