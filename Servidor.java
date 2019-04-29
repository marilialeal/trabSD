import java.io.*;
import java.net.*;
import java.util.*;
public class Servidor extends Thread {

	// Parte que controla as conexões por meio de threads.
	// Note que a instanciação está no main.
	private static Vector <PrintStream> Vetor_Clientes;
	private Socket conexao;
	private String opcao;
	private Queue Fila_F1 = new LinkedList(); 
	private Queue Fila_F2 = new LinkedList(); 
	private Queue Fila_F3 = new LinkedList(); 

	public static void main(String args[]) {
		// instancia o vetor de clientes conectados
		Vetor_Clientes = new Vector <PrintStream> ();
		try {
			// criando um socket que fica escutando a porta 2222.
			ServerSocket s = new ServerSocket(5082);
			// Loop principal.
			while (true) {
				// aguarda algum cliente se conectar. A execução do
				// servidor fica bloqueada na chamada do método accept da
				// classe ServerSocket. Quando algum cliente se conectar
				// ao servidor, o método desbloqueia e retorna com um
				// objeto da classe Socket, que é porta da comunicação.
				System.out.println("Esperando alguma requisição ao BD");
				Socket conexao = s.accept();
				// cria uma nova thread para tratar essa conexão
				System.out.println("Recebida conexao "+conexao);
				Thread t = new Servidor(conexao);
				t.start();
				// voltando ao loop, esperando mais alguém se conectar.
			}
		}catch (IOException e) {
			// caso ocorra alguma excessão de E/S, mostre qual foi.
			System.out.println("IOException: " + e);
		}
	}
	public Servidor(Socket s) {
		conexao = s;
	}
	// execução da thread
	public void run() {
		try {
			// objetos que permitem controlar fluxo de comunicação
			BufferedReader entrada = new BufferedReader(new	InputStreamReader(conexao.getInputStream()));
			PrintStream saida = new PrintStream(conexao.getOutputStream());
			// primeiramente, espera-se a opcao do servidor 
			while(true){
				entrada = entrada.readLine();
				// agora, verifica se string recebida é valida, pois
				// sem a conexão foi interrompida, a string é null.
				// Se isso ocorrer, deve-se terminar a execução. 
				if (entrada == null) {return;}
				else 
				{
                 // --------------------------------------------------------------------------------------------//
				// funcao para receber os comandos do cliente e adicionar a fila F1			
                try 
				{
                    Fila_F1.add(comando);
                } catch (IllegalStateException e) {
                e.printStackTrace();
                } 
				// adiciona o comando a Fila_F1 
				// --------------------------------------------------------------------------------------------//
				
			
    			System.out.println(conexao+" Opcao selecionada = "+opcao);
				saida.println("Opcao selecionada = "+opcao);
				// Uma vez que se tem um cliente conectado
				// coloca-se fluxo de saída para esse cliente no vetor de
				// clientes conectados.
				Vetor_Clientes.add(saida);
				// Vetor_Clientes é objeto compartilhado por várias threads!
				// De acordo com o manual da API, os métodos são
				// sincronizados. Portanto, não há problemas de acessos
				// simultâneos.
				// Verificar se linha é null (conexão interrompida)
				// Se não for nula, pode-se compará-la com métodos string
			}
			// Uma vez que o cliente enviou linha em branco, retira-se
			// fluxo de saída do vetor de clientes e fecha-se conexão.
			Vetor_Clientes.remove(saida);
			conexao.close();
		}
		catch (IOException e) {
			// Caso ocorra alguma excessão de E/S, mostre qual foi.
			System.out.println("IOException: " + e);
		}
	}
}




				
				// --------------------------------------------------------------------------------------------//
				// funcao para remover os comandos da Fila_F1 e adicionar nas Filas 2 e 3
				String Comeco_Fila = null;
                try {
                Comeco_Fila = Fila_F1.remove();
				} catch (NoSuchElementException e) 
				{
					e.printStackTrace();
			    }
				Fila_F2.add(Comeco_Fila);
				Fila_F2.add(Comeco_Fila);
				// --------------------------------------------------------------------------------------------//
				
				
				// --------------------------------------------------------------------------------------------//
				// funcao para remover os comandos da Fila_F2 e gravar no disco 
				String Operacao = null;
                try {
                Operacao = Fila_F2.remove();
				} catch (NoSuchElementException e) 
				{
					e.printStackTrace();
			    } 
				//implementar gravacao no disco usando a string Operacao
				// --------------------------------------------------------------------------------------------//
				
				
				// --------------------------------------------------------------------------------------------//
				// funcao para remover os comandos da Fila_F3 e aplicar no banco
				String Operacao = null;
                try {
                Operacao = Fila_F3.remove();
				} catch (NoSuchElementException e) 
				{
					e.printStackTrace();
			    }
				String[] comando = Operacao.split(';') //Divide o comando recebido em duas partes separadas por ;
				opcao = Operacao[0]  // primeira parte é a opcao CRUD				
				//"1 - Create"
				//"2 - Read"
				//"3 - Update"
				//"4 - Delete"
				funcao = Operacao[1]  // segunda parte é a operacao 
				if (opcao == "1")//"1 - Create"
				{	
			
				}
				else if (opcao == "2")//"2 - Read"
				{
					
				}
				else if (opcao == "3")//"3 - Update"
				{
					
				}
				else if (opcao == "4")//"4 - Delete"
				{
					
				}
				else if (opcao == "5")//"4 - SAIR"
				{					
					break;
				}
					
					
				}
				// --------------------------------------------------------------------------------------------//
				
				
