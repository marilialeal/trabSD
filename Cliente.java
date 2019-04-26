<<<<<<< HEAD
import java.util.Scanner;

public class Cliente{
	
=======
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

public class Cliente{
	private static Socket server = null;
>>>>>>> 272dcbffaf7f9289485397da7f3664a247181802
	public Cliente(){
	}
	
	public static void main(String[] args){
<<<<<<< HEAD
		Thread menu =  new Thread(new Interface());
		Thread receptor = new Thread(new Receptor());
		menu.start();
=======
		try{
			server = new Socket("127.0.0.1",5082);
			if(server == null) System.out.println("Erro, socket = null");
			Thread menu =  new Thread(new Interface(server));
			Thread receptor = new Thread(new Receptor(server));
			menu.start();
			receptor.start();
			
		}catch(UnknownHostException a){
			System.out.println("Erro na conexao. Socket nao criado. Err."+a);		
		}
		catch(IOException b){
			System.out.println("Erro na conexao. Socket nao criado. Err."+b);
		}
>>>>>>> 272dcbffaf7f9289485397da7f3664a247181802
	}
}

class Interface implements Runnable{
<<<<<<< HEAD
=======
	private Socket socket_cliente = null;
>>>>>>> 272dcbffaf7f9289485397da7f3664a247181802
	private String menu = "Menu:\n"+
				"1 - Create\n"+
				"2 - Read\n"+
				"3 - Update\n"+
				"4 - Delete\n"+
				"5 - Quit\n";
	private int resposta;
<<<<<<< HEAD
	public void main(){
		Thread op = new Thread(new Interface());
=======
	public Interface(Socket a){
		socket_cliente = a;
		if(socket_cliente == null) System.out.println("Erro, saida = null");		
	}

	public void main(){
	
		Thread op = new Thread(this);
>>>>>>> 272dcbffaf7f9289485397da7f3664a247181802
		op.start();
		try{
			op.join();
		}catch(InterruptedException a){
		}
	}
	public void run(){
	Scanner leitor = new Scanner(System.in);
<<<<<<< HEAD
		while(!Thread.currentThread().isInterrupted()){
=======
		while(!(Thread.currentThread().isInterrupted())){
>>>>>>> 272dcbffaf7f9289485397da7f3664a247181802
			System.out.println(menu);
			resposta = leitor.nextInt();
			validaResposta(resposta);
		}
	}
	private void validaResposta(int a){
<<<<<<< HEAD
		if(a>0 && a<5){
		}//sendResposta
		else if (a==5){
			System.out.println("Tchau");
			Thread.currentThread().interrupt();
=======
		if(a>0 && a<6){
			try{
				PrintWriter saida = new PrintWriter(socket_cliente.getOutputStream(), true);
				if(saida == null) System.out.println("Erro, saida = null");
				else saida.println(a);
			}
			catch(IOException falha){}
			if (a==5){
				System.out.println("Tchau");
				Thread.currentThread().interrupt();
			}
>>>>>>> 272dcbffaf7f9289485397da7f3664a247181802
		}
		else System.out.println("Erro: Opção inválida");
	}
}

class Receptor implements Runnable{
<<<<<<< HEAD
	public void main(){
	//construtores
	}
	public void run(){
	//printa as respostas que vem do servidor
=======
	private Socket socket_cliente;
	public Receptor(Socket a){
		socket_cliente = a;
	}
	public void main(){
	Thread op = new Thread(this);
		op.start();
		try{
			op.join();
		}catch(InterruptedException a){
		}
	}
	public void run(){
	try {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));
		String linha;
		while (!(Thread.currentThread().isInterrupted())) {
		// pega o que o servidor enviou
			linha = entrada.readLine();
		// verifica se é uma linha válida. Pode ser que a conexão
		// foi interrompida. Neste caso, a linha é null. Se isso
		// ocorrer, termina-se a execução saindo com break
			if (linha == null) {
			try{	
				Thread.currentThread().sleep(5);
				System.out.println("Conexão encerrada!");
				break;
			}
			catch(InterruptedException a){
				break;
			}
			}else System.out.println(linha);
		}
	}catch(IOException a){}
>>>>>>> 272dcbffaf7f9289485397da7f3664a247181802
	}
}
