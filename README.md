

# Trabalho 1 : Sistemas Distribuidos : (UFU 2019.1)



Grupo:

+ Guilherme Fagotti
+ João Marcos Gomes
+ Marilia Leal
+ Rafael Morais de Assis

Links:

+ Repositório do Clone: [<https://github.com/rafanthx13/facomsd>](https://github.com/rafanthx13/facomsd)

+ BitBucket: [<https://bitbucket.org/facom_ufu/gbc074gsi028/src/master/>](https://bitbucket.org/facom_ufu/gbc074gsi028/src/master/)

  

## Descrição do Trabalho (Desc do Slide)

### Objetivo

​	O objetivo deste projeto é desenvolver um sistema de bancos de dados genérico/multiuso, a ser usado como bloco de construção em outros projetos.
​	Com este objetivo, replicaremos abordagens bem conhecidas e funcionais, aplicando diversas técnicas de desenvolvimento de sistemas distribuídos.

### Entrega 1

​	Para a primeira entrega vocês desenvolverão a “cara” do banco de dados, permitindo que clientes se conectem e realizem operações de acordo com a especificação da API. Desenvolverão também um cliente em linha de comando para que se possa manipular o banco de dados, bem como um cliente de testes, que estressará o banco para verificar sua corretude e funcionalidades.

**Cliente Interativo**

Leitura de comandos

+ 1 thread em loop infinito apresentando menu de comandos e lendo comandos do teclado
+ uma vez digitado um comando, o mesmo é validado localmente
+ se válido, comando é enviado ao servidor
+ se inválido, mensagem de erro é apresentada
+ o comando sair termina a execução deste thread

Apresentação de respostas

+ 1 thread em loop infinito recebendo mensagens do servidor
+ uma vez recebida uma mensagem, a mesma é apresentada na tela
+ uma vez terminado o thread de leitura de comandos, o thread de apresentação espera pelo menos 5 segundos por novas mensagens do servidor e então se termina, terminando o processo.

**O Servidor**

A base de dados

+ é um mapa de BigInteger (inteiro de precisão infinita) para um vetor de bytes (ou algo que o valha)
+ mantido em memória apenas (por enquanto) e manipulado por 4 operações (CRUD)
+ observando a semântica de cada operação (create 6 = update)
+ todos os comandos retornam um código de erro/sucesso mais resposta, se for adequado.

Apesar do banco ser em memória, toda operação será logada emdisco.

Terá arquitetura em estágios, tendo

+ 1 ou mais threads recebendo comandos e colocando em uma fila F1
+ 1 thread consumindo comandos de F1 e colocando cópias do comando em uma fila F2 e em outra fila F3
+ 1 thread consumindo comandos de F2 e gravando-os em
  disco.
+ 1 thread consumindo de F3 aplicando o comando no banco
  de dados (mapa)

**O thread de log**

grava comandos em um arquivo de log

+ mantendo o arquivo aberto durante a execução do programa
+ adicionando comandos sempre ao fim do arquivo
+ somente se o comando altera a base de dados (Reads são descartados)

**O thread de Processamento**

executa os comandos

+ contra o mapa
+ emitindo mensages de sucesso (create/update/delete)
+ respondendo com informação solicitada (read)
+ emitindo erros quando adequado (create/update/delete/read)
+ na ordem em que os comandos foram enfileirados em F3

**Tolerância a Falhas**

Como o mapa é mantido em memória, no caso de falhas, todo o banco apagado. Para recuperá-lo

+ Na reinicialização do processo
+ abra o arquivo de log
+ e processe-o na sequência em que foi escrito
+ reexecutando todas as operações gravadas
+ antes de aceitar novas requisições de clientes.

**Acesso Concorrente**

Diversos clientes podem ser iniciados em paralelo e contactando o mesmo servidor.

**Comunicação**

+ Toda comunicação é feita via TCP.
+ E o canal de comuniação com o cliente é mantido aberto
  enquanto o mesmo estiver executando.
+ Todas as portas usadas na comunicação são especificadas
  via arquivos de configuração.

## Git

Comandos

`git status`: Verifica os arquivos do git, se tem alguma modificação.

Como Subir modificações

1. Dê `git status` pra ve se tem aluma modificação para subir
2. Dê `git add --all` para subir todos as modificações que foram listadas no `git status`
   + As modificações estarão no *Stagin Area local*
3. Dê `git commit -m "msg"`, em `msg` troque pela mensagem que explica o seu commit
   + Exemplo: `git commit -m "First Commit"`
   + Com isso, você mudou sua branch
4. Dê `git push` para subir suas modificações para o repositório, ou seja, para o site do git (assim todos poderão baixar e atualizar as suas branch)

**Como atualizar sua branch**

+ Dê `git pull`, se houver alguma moficação na branch no remote (no site) entâo será baixada na sua máquina localmente

  **Caso der problema no git pull**

+ **CASO EDR ERRO E APRACEÇA `CONFLIT`** **ENTAO QUER DIZER QUE DEU CONFLITO ENTRE SUA BRANCH E A REMOTE, PROCURE ENTÃO RESOLVER ISSO. UM EXEMPLO É O `kdiff3`** **UMA FERRAMENTA PARA RESOLVER CONFLITO DE GIT**

+ OBS: Alguns editored e IDE mais modernos possuem mecanismos para tentar resolver, ou, pelo menos mostrar onde deu conflito

**Branch**

+ Caso queira uma branch (ramo) de desenvolvimento só para você, utilize `git checkout -b nome_da_branch` , ela será local e até você subila, assim você pode fazer seus push sem se preocupar em dar conflito com outras pessoas
+ `git merge branch_name` : Junta sua branch, a que você está com a `branch_name`. SUa branch receberá de  `branch_name`
+ `git branch`: lista as branchs locias e em qual você está
+ `git checkout branch_name`: troca sua branch para `branch_name`,
  + **ISSO MUDA TODOS OS SEUS ARQUIVOS LOCAL, MAS AS COISAS DE SUA BRANCH FICARÃO GUARDADAS, BASTA VOLTAR A BRANCH**

## Exemplo de Código usando Fila em Java `Queue`

Busque pela variável da fila: `fila_borda`

<https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html>

<https://docs.oracle.com/javase/tutorial/collections/interfaces/queue.html>

<https://www.geeksforgeeks.org/queue-interface-java/>

````java
// NodeL.java

package vaccumcleaner.buscaLargura;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import vaccumcleaner.Estado;
import vaccumcleaner.No;

public class NodeL extends No{
    
    // Atributos static e ED
    public static Stack stack_output = new Stack();
    public static Queue fila_borda = new LinkedList<NodeL>(); //
    public static ArrayList<NodeL> nos_explorados = new ArrayList<NodeL>();
    public static LinkedList<String> states_explorados = new LinkedList<String>(); 

    public NodeL(Estado estado, NodeL pai, String action_do_pai, int custo) {
        this.estado = estado;
        this.pai = pai;
        this.action_do_pai = action_do_pai;
        this.custo = custo;
    }    
    
    @Override
    public NodeL action_aspirar(){
        if(getEstado().getMeuBloco() == 'a'){
            //Aspira no a
            return new NodeL(
                    new Estado(true, getEstado().isLimpoB(), 'a'),
                    this,
                    "aspirar",
                    getCusto() + 1
            );
        }else{
            //Aspira no b
            return new NodeL(
                    new Estado(getEstado().isLimpoA(), true, 'b'),
                    this,
                    "aspirar",
                    getCusto() + 1
            );
        }
    }
    
    @Override
    public NodeL action_direita(){
        if(getEstado().getMeuBloco() == 'a'){
            //vai pra esquerda, pro b
            return new NodeL(
                    new Estado(getEstado().isLimpoA(),getEstado().isLimpoB(),'b'),
                    this,
                    "direita",
                    getCusto() + 1
            );
        }else{
            //esta no b e nao move
            return new NodeL(
                    getEstado(),
                    this,
                    "direita",
                    getCusto() + 1
            );
        }
        
    }
    
    @Override
    public NodeL action_esquerda(){
        if(getEstado().getMeuBloco() == 'b'){
            //vai pra direita, pra a
            return new NodeL(
                    new Estado(getEstado().isLimpoA(),getEstado().isLimpoB(),'a'),
                    this,
                    "esquerda",
                    getCusto() + 1
            );
        }else{
            //esta no a e nao move
            return new NodeL(
                    getEstado(),
                    this,
                    "esquerda",
                    getCusto() + 1
            );
        }
        
    }
    
    @Override
    public NodeL action(int num){
        switch (num) {
            case 1:
                return action_direita();
            case 2:
                return action_esquerda();
            default:
                return action_aspirar();
        }
    }
    
}

````

````java
// BuscaLargura.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccumcleaner.buscaLargura;

import vaccumcleaner.Estado;

public class BuscaLargura {
    
    public void buscaLargura(Estado estado){
        int num_no = 1;
        //System.out.println("\n\t Executando Busca em Largura\n");
        boolean saida = false;
        NodeL filho;
        NodeL no = estado.geraNodeLInicial();
        NodeL.nos_explorados.add(no);
        NodeL.states_explorados.add(no.getEstado().getState());
        //verifica estado/no inicial
        /*
        System.out.println("NodeL inicial:");
        no.output_node();
        System.out.println("\nIniciar...");
        */
        if(no.teste_objetivo() == true){
            //terminou, ja começou no estado de soluçao, fim, volta algo, return algo
        }
        NodeL.fila_borda.offer(no);
        while(true){
            if(NodeL.fila_borda.peek() == null){
                //FAIL
                System.out.println("Resposta nao encontrada Falha ao verificar na FILA");
                break;
            }
            no = (NodeL) NodeL.fila_borda.poll();
            NodeL.nos_explorados.add(no);
            NodeL.states_explorados.add(no.getEstado().getState());
            for(int count = 1; count <= 3; count++){
                filho = no.action(count);
                num_no++;
                //filho.output_node(); // verifica qual no esta sendo criado
                if(NodeL.fila_borda.contains(filho) == false 
                        //&& NodeL.nos_explorados.contains(filho) == false
                        && NodeL.states_explorados.contains(filho.getEstado().getState()) == false ) //verificar essa ultima parte, ela faz diferença
                {
                    if(filho.teste_objetivo() == true){
                        //SOLUÇAO
                        filho.print_solucao();
                        System.out.println("Numero de Nos:" + num_no);
                        saida = true;
                        break;
                    }else{
                        NodeL.fila_borda.offer(filho);
                    }
                }
            }
            if(saida == true) break;
        }
        System.out.println("\nEND! Busca em Largura");

        
        
        
    }
    
}

````

