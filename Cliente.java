import java.util.Scanner;

public class Cliente{
	
	public Cliente(){
	}
	
	public static void main(String[] args){
		Thread menu =  new Thread(new Interface());
		Thread receptor = new Thread(new Receptor());
		menu.start();
	}
}

class Interface implements Runnable{
	private String menu = "Menu:\n"+
				"1 - Create\n"+
				"2 - Read\n"+
				"3 - Update\n"+
				"4 - Delete\n"+
				"5 - Quit\n";
	private int resposta;
	public void main(){
		Thread op = new Thread(new Interface());
		op.start();
		try{
			op.join();
		}catch(InterruptedException a){
		}
	}
	public void run(){
	Scanner leitor = new Scanner(System.in);
		while(!Thread.currentThread().isInterrupted()){
			System.out.println(menu);
			resposta = leitor.nextInt();
			validaResposta(resposta);
		}
	}
	private void validaResposta(int a){
		if(a>0 && a<5){
		}//sendResposta
		else if (a==5){
			System.out.println("Tchau");
			Thread.currentThread().interrupt();
		}
		else System.out.println("Erro: Opção inválida");
	}
}

class Receptor implements Runnable{
	public void main(){
	//construtores
	}
	public void run(){
	//printa as respostas que vem do servidor
	}
}
