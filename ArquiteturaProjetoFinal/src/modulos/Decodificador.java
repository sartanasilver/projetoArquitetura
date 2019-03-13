package modulos;

public class Decodificador {
	
	public int aux, aux1;
	public int idReg1, idReg2, flagReg, opCode;
	public int palavra;
	public int mascara8 = 0b11111111;
	public int mascara4 = 0b1111;
	public int masca10 = 0b1111111111;
	int flagMemoria1, flagRegistrador1,flagMemoria2, flagRegistrador2;
			
	
		
	public void decodificar(int palavra) {
		
		this.palavra = palavra;
	
	String bin0 = Integer.toBinaryString(palavra);// essa string só server para imprimir o valor em binario.
			 
	idReg2 = (palavra & masca10);
	 String bin1 = Integer.toBinaryString(idReg2); 
			
	aux = palavra >> 10;		 
	idReg1 = (aux &  masca10);
	 
	 String bin2 = Integer.toBinaryString(idReg1); 
			   
	aux = aux >> 10;		 
	this.flagReg = (aux &  mascara4);
	aux1 = this.flagReg;
	decodificarFlag(aux1);
	 
	// String bin3 = Integer.toBinaryString(flagReg); 
	 //System.out.println("Flag: "+bin3);
			   
	aux = aux >> 4;
	opCode = (aux & mascara8);
	 
	 String bin4 = Integer.toBinaryString(opCode); 
	  
	
	System.out.println("");
	System.out.println("                 Palavra: "+bin0);
	System.out.println("----------------------------------------------------------------");
	System.out.println("| OPCODE: "+bin4+" | "+"idReg1: "+bin2+" | "+"idReg2: "+bin1+" |");
	System.out.println("----------------------------------------------------------------");
	}
	
	public void decodificarFlag(int flagReg) {
		
		this.flagMemoria1 = (flagReg & 1);
		flagReg = flagReg >> 1;
		this.flagMemoria2 =  (flagReg & 1);
		flagReg = flagReg >> 1;
		this.flagRegistrador1 = (flagReg & 1);
		flagReg = flagReg >> 1;
		this.flagRegistrador2 = (flagReg & 1);
		
		 String bin3 = Integer.toBinaryString(this.flagReg); 
		
		//System.out.println("FLAGS = "+bin3+" Flag Registador 2 = "+this.flagRegistrador2+" Flag Registrador1 = "+this.flagRegistrador1+" Flag Memoria 2 = "+this.flagMemoria2+" Flag Memoria 1 = "+this.flagMemoria1);
	}
	
	
}
