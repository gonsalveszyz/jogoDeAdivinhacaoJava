# 🎯 Jogo da Adivinhação — Guessing Game em Java

Um jogo de terminal simples em Java onde o jogador tenta adivinhar um número secreto gerado aleatoriamente, com até **5 tentativas**.

---

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Como Funciona o Código](#como-funciona-o-código)
- [Pré-requisitos](#pré-requisitos)
- [Como Compilar e Executar](#como-compilar-e-executar)
- [Como Jogar](#como-jogar)
- [Exemplo de Partida](#exemplo-de-partida)
- [Bugs Conhecidos](#bugs-conhecidos)

---

## Sobre o Projeto

O **Jogo da Adivinhação** é um programa de linha de comando escrito em Java. Ao iniciar, o programa sorteia um número aleatório entre **0 e 100** e desafia o jogador a descobri-lo em no máximo **5 tentativas**. A cada palpite errado, o jogo informa se o número secreto é maior ou menor do que o valor digitado, ajudando o jogador a se aproximar da resposta.

---

## Como Funciona o Código

### Estrutura Geral

O projeto é composto por um único arquivo: `Main.java`, com a classe `Main` e o método `main`.

### Importações

```java
import java.util.Random;
import java.util.Scanner;
```

- **`Random`** — usada para gerar o número secreto aleatório.
- **`Scanner`** — usada para capturar o palpite digitado pelo jogador via terminal.

---

### Variáveis Principais

| Variável       | Tipo  | Descrição                                              |
|----------------|-------|--------------------------------------------------------|
| `numAleatorio` | `int` | Número secreto sorteado entre 0 e 100 (inclusive).    |
| `tentativas`   | `int` | Contador de tentativas realizadas. Começa em 0.        |
| `acertos`      | `int` | Inicializado com 1 (ver [Bugs Conhecidos](#bugs-conhecidos)). |
| `palpite`      | `int` | Número digitado pelo jogador a cada rodada.            |

---

### Geração do Número Secreto

```java
int numAleatorio = new Random().nextInt(101);
```

`nextInt(101)` gera um inteiro no intervalo **[0, 100]** — ou seja, 0 e 100 são ambos possíveis.

---

### Loop Principal — As 5 Tentativas

```java
while (tentativas < 5) {
    System.out.println("Digite um palpite: ");
    int palpite = sc.nextInt();

    tentativas++;

    if (palpite == numAleatorio) {
        System.out.println("Voce acertou em " + tentativas + " tentativas.");
        break;
    } else if (palpite < numAleatorio) {
        System.out.println("O numero e maior!");
    } else if (palpite > numAleatorio) {
        System.out.println("O numero e menor!");
    }
}
```

O `while` executa enquanto o número de tentativas for menor que 5. A cada iteração:

1. O jogador digita um número.
2. `tentativas` é incrementado.
3. O palpite é comparado ao número secreto:
   - **Igual** → exibe a mensagem de vitória e encerra o loop com `break`.
   - **Menor** → avisa que o número secreto é **maior**.
   - **Maior** → avisa que o número secreto é **menor**.

---

### Mensagem de Fim de Jogo

```java
if (numAleatorio != numAleatorio) {
    System.out.println("Voce perdeu!");
} else {
    System.out.println("Voce esgotou suas tentativas e perdeu!");
    System.out.println(tentativas + " tentativas utilizadas");
}
```

> ⚠️ Este trecho possui um bug (ver [Bugs Conhecidos](#bugs-conhecidos)). Na prática, a mensagem `"Voce esgotou suas tentativas e perdeu!"` é exibida **sempre** ao final do loop, mesmo quando o jogador acerta.

---

## Pré-requisitos

- **Java JDK 8 ou superior** instalado.
- Terminal (Prompt de Comando, PowerShell, Bash, etc.).

Verifique sua instalação do Java:

```bash
java -version
javac -version
```

---

## Como Compilar e Executar

### 1. Salve o arquivo

Salve o código em um arquivo chamado exatamente `Main.java`.

### 2. Compile

No terminal, navegue até a pasta do arquivo e execute:

```bash
javac Main.java
```

Isso gerará o arquivo `Main.class` na mesma pasta.

### 3. Execute

```bash
java Main
```

---

## Como Jogar

1. Ao iniciar, o programa exibirá:
   ```
   Jogo da Adivinhacao
   Adivinhe o numero secreto
   ```
2. O jogo sorteará um número entre **0 e 100** (você não saberá qual é).
3. Digite um número inteiro quando solicitado e pressione **Enter**.
4. O jogo indicará se o número secreto é **maior** ou **menor** que seu palpite.
5. Você tem até **5 tentativas** para acertar.
6. Se acertar, o jogo mostra em quantas tentativas você conseguiu.

---

## Exemplo de Partida

```
Jogo da Adivinhacao
Adivinhe o numero secreto
Digite um palpite:
50
O numero e maior!
Digite um palpite:
75
O numero e menor!
Digite um palpite:
62
O numero e maior!
Digite um palpite:
68
Voce acertou em 4 tentativas.
```

---

## Bugs Conhecidos

O código original possui dois problemas lógicos:

### 🐛 Bug 1 — Condição impossível na derrota

```java
// ERRADO: uma variável nunca é diferente de si mesma
if (numAleatorio != numAleatorio) { ... }
```

Essa condição é **sempre falsa**, então a mensagem `"Voce perdeu!"` **jamais é exibida**. O correto seria comparar o palpite com o número ou checar se o loop esgotou sem acerto. Uma correção possível é usar uma flag:

```java
// CORRETO
boolean acertou = false;

while (tentativas < 5) {
    // ...
    if (palpite == numAleatorio) {
        acertou = true;
        System.out.println("Voce acertou em " + tentativas + " tentativas.");
        break;
    }
    // ...
}

if (!acertou) {
    System.out.println("Voce perdeu! O numero era: " + numAleatorio);
}
```

### 🐛 Bug 2 — Mensagem de fim sempre exibida

Por causa do Bug 1, o bloco `else` do fim do jogo é executado **independentemente** de o jogador ter acertado ou não, exibindo `"Voce esgotou suas tentativas e perdeu!"` mesmo após uma vitória. A correção do Bug 1 (com a flag `acertou`) resolve este problema também.

### 🐛 Bug 3 — Variável `acertos` sem uso real

A variável `acertos` é inicializada com `1` e o `else if (acertos > 0)` é **inatingível**, pois os blocos anteriores já cobrem todos os casos possíveis (`==`, `<` e `>`). Essa variável pode ser removida com segurança.

---

## Licença

Projeto de uso livre para fins educacionais.
