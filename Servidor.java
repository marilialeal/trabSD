import java.io.*;
import java.net.*;
import java.util.*;
public class Servidor extends Thread {

	// Parte que controla as conexões por meio de threads.
	// Note que a instanciação está no main.
	private static Vector <PrintStream> Fila_F1;
	private Socket conexao;
	private String opcao;

	public static void main(String args[]) {
		// instancia o vetor de clientes conectados
		Fila_F1 = new Vector <PrintStream> ();
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
				opcao = entrada.readLine();
				// agora, verifica se string recebida é valida, pois
				// sem a conexão foi interrompida, a string é null.
				// Se isso ocorrer, deve-se terminar a execução.
				if (opcao == null) {return;}
				else if (opcao == "1"){
				}
				else if (opcao == "2"){
				}
				else if (opcao == "3"){
				}
				else if (opcao == "4"){
				}
				else if (opcao == "5"){
					break;
				}
				System.out.println(conexao+" Opcao selecionada = "+opcao);
				saida.println("Opcao selecionada = "+opcao);
				// Uma vez que se tem um cliente conectado
				// coloca-se fluxo de saída para esse cliente no vetor de
				// clientes conectados.
				Fila_F1.add(saida);
				// Fila_F1 é objeto compartilhado por várias threads!
				// De acordo com o manual da API, os métodos são
				// sincronizados. Portanto, não há problemas de acessos
				// simultâneos.
				// Verificar se linha é null (conexão interrompida)
				// Se não for nula, pode-se compará-la com métodos string
			}
			// Uma vez que o cliente enviou linha em branco, retira-se
			// fluxo de saída do vetor de clientes e fecha-se conexão.
			Fila_F1.remove(saida);
			conexao.close();
		}
		catch (IOException e) {
			// Caso ocorra alguma excessão de E/S, mostre qual foi.
			System.out.println("IOException: " + e);
		}
	}
}
