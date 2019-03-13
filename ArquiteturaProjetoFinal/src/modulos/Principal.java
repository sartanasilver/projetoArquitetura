package modulos;


public class Principal {

	public static void main(String[] args) {
		
		Memoria memoria = new Memoria();	
		Decodificador decodificador = new Decodificador();
		Controle testeControle = new Controle();
		
		
		//      memoria.salvar(PALAVRA, POSIÇÃO NA MEMORIA) ;  
		memoria.salvar(0b00001001011011011011010000101000,0);// MOV da memoria(indice 40) para o registrador reg2
		memoria.salvar(0b00001001110010101010101101101101,1);////MOV do reg2 para o reg1
		memoria.salvar(0b00000001011011011011010000101101, 2);// SOMA 1(indice 45) no reg2
		memoria.salvar(0b00001010011011011011010000101001,3);// COMPARE reg2 com memoria(indice 41 == 122)
		memoria.salvar(0b00000110111100000000010000000000, 4);//JUMP IF - só sai do loop quando o COMPARE for verdadeiro
		memoria.salvar(0b00001001011011101110100000100100,5);//MOV
		memoria.salvar(0b00001001011011110111100001101001,6);//MOV
		memoria.salvar(0b00000101110010101010100000000000, 7);// NOT
		memoria.salvar(0b00001000000000000000000000000000, 8);//HALT finaliza aplicação
		
		memoria.salvar(120, 40);
		memoria.salvar(122, 41);
		memoria.salvar(122, 42);
		memoria.salvar(1, 45);
		memoria.salvar(59, 36);
		memoria.salvar(31, 105);
		
		
		for( int i = 0; i <= 235 ; i++ ) {
			decodificador.decodificar(memoria.exibir(i));
			testeControle.iniciarControle(decodificador.flagMemoria1, decodificador.flagMemoria2, decodificador.opCode, decodificador.idReg1, decodificador.idReg2, memoria);
			testeControle.memoria.PC = i;
			System.out.println("PC: "+testeControle.memoria.PC);
			System.out.println((i+1)+"º rodada");
			testeControle.testereg();
			System.out.println("-");
			System.out.println("Compare: "+testeControle.regs.flagCompare);
			
		}
	    System.out.println("Resultado final");
		System.out.println("Compare: "+testeControle.regs.flagCompare);
		testeControle.testereg();
	}	
}


