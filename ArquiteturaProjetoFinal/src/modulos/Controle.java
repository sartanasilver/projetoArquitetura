package modulos;

/* 
 * OPCODES                    REGISTRADORES
 * 00000001 soma              1010101010 reg1
 * 00000010 multi             1101101101 reg2 
 * 00000011 sub               1110111010 reg3
 * 00000100 and               1111011110 reg4  
 * 00000101 not
 * 00000110 jumpIf
 * 00000111 jumpFor
 * 00001000 halt
 * 00001001 mov
 * 00001010 compare
 */

public class Controle {
	
	public int idReg1, idReg2, flagReg, opCode;
	
	int flagMEMO1, flagMEMO2;
	ULA operacao = new ULA();
	Registradores regs = new Registradores();
	Memoria memoria = new Memoria();
	Decodificador decodificador = new Decodificador();
	int aux, aux2;
	
	public Controle() {
		
	}
	
    public void iniciarControle(int flagReg1, int flagReg2, int opcode, int op1, int op2, Memoria memo) {
    	
    	this.flagMEMO1 = flagReg1;
		this.flagMEMO2 = flagReg2;
		this.opCode = opcode;
		this.idReg1 = op1;
		this.idReg2 = op2;
		this.memoria = memo;
		
    	
		if(opcode == 0b1001) {
			
			mov(this.flagMEMO1, this.flagMEMO2, this.idReg1, this.idReg2);
			
			
		}else if(opcode == 0b1010 ) {//Controle Compare
			
			
			  if(this.flagMEMO1 == 0 && this.flagMEMO2 == 0) {
				 ativarCompare(regs.getValorReg(seletorDeReg(this.idReg1)), regs.getValorReg(seletorDeReg(this.idReg2)));
				
				
			  }else if(this.flagMEMO1 == 1 && this.flagMEMO2 == 1) {
				ativarCompare(this.memoria.exibir(this.idReg1),this.memoria.exibir(this.idReg2));
				
			  }else if(this.flagMEMO1 == 1 && this.flagMEMO2 == 0) {
				ativarCompare(this.memoria.exibir(this.idReg1),regs.getValorReg(seletorDeReg(this.idReg2)));
				
			  }else {
				ativarCompare(regs.getValorReg(seletorDeReg(this.idReg1)),this.memoria.exibir(this.idReg2));
				
			  }
			
		}else if(opcode == 0b1) {//Controle Soma
			
			  if( this.flagMEMO1 == 0 && this.flagMEMO2 == 0) {  
				  this.regs.regX[seletorDeReg(this.idReg1)] = operacao.somaReg(this.regs.regX[seletorDeReg(this.idReg1)], this.regs.regX[seletorDeReg(this.idReg2)]);
				 
				}else if(this.flagMEMO1 == 1 && this.flagMEMO2 == 1) {
					operacao.soma(memoria.exibir(this.idReg1), memoria.exibir(this.idReg2));
					
				}else if(this.flagMEMO1 == 0 && this.flagMEMO2 == 1) {
					 this.regs.regX[seletorDeReg(this.idReg1)] = operacao.somaReg( this.regs.regX[seletorDeReg(this.idReg1)], memoria.exibir(this.idReg2));
					
				}else {
					operacao.soma(memoria.exibir(this.idReg1), this.regs.regX[seletorDeReg(this.idReg2)]);
					
				}
			
		}else if(opcode == 0b10) {//Controle Multiplicação
			
			  if( this.flagMEMO1 == 0 && this.flagMEMO2 == 0) {
				  this.regs.regX[seletorDeReg(this.idReg1)] = operacao.multiReg(this.regs.regX[seletorDeReg(this.idReg1)], this.regs.regX[seletorDeReg(this.idReg2)]);
				 
			  }else if(this.flagMEMO1 == 1 && this.flagMEMO2 == 1) {
				  operacao.multi(memoria.exibir(this.idReg1), memoria.exibir(this.idReg2));
				 
			  }else if(this.flagMEMO1 == 0 && this.flagMEMO2 == 1) {
				  this.regs.regX[seletorDeReg(this.idReg1)] = operacao.multiReg(this.regs.regX[seletorDeReg(this.idReg1)], memoria.exibir(this.idReg2));
				  
			  }else {
				  operacao.multi(memoria.exibir(this.idReg1), this.regs.regX[seletorDeReg(this.idReg2)]);
				  
			  }
			
		}else if(opcode == 0b00000011) {//Controle subtração
			
			  if( this.flagMEMO1 == 0 && this.flagMEMO2 == 0) {
				  this.regs.regX[seletorDeReg(this.idReg1)] = operacao.subtReg( this.regs.regX[seletorDeReg(this.idReg1)],  this.regs.regX[seletorDeReg(this.idReg2)]);
			  }else if(this.flagMEMO1 == 1 && this.flagMEMO2 == 1) {
				  operacao.subt(memoria.exibir(this.idReg1), memoria.exibir(this.idReg2));
			  }else if(this.flagMEMO1 == 0&& this.flagMEMO2 == 1) {
				  this.regs.regX[seletorDeReg(this.idReg1)] = operacao.multiReg(this.regs.regX[seletorDeReg(this.idReg1)], memoria.exibir(this.idReg2));
				  
			  }else {
				  operacao.subt(memoria.exibir(this.idReg1), this.regs.regX[seletorDeReg(this.idReg2)]);
				  
			  }
			
			
			
		}else if(opcode == 0b100) {//Controle AND logico
			  
			  if( this.flagMEMO1 == 0 && this.flagMEMO2 == 0) {
				  this.regs.regX[seletorDeReg(this.idReg1)] = operacao.andLogicoReg(this.regs.regX[seletorDeReg(this.idReg1)], this.regs.regX[seletorDeReg(this.idReg2)]);
				  
			  }else if(this.flagMEMO1 == 1 && this.flagMEMO2 == 1) {
				  operacao.andLogicou(memoria.exibir(this.idReg1), memoria.exibir(this.idReg2));
				  
			  }else if(this.flagMEMO1 == 0 && this.flagMEMO2 == 1) {
				  this.regs.regX[seletorDeReg(this.idReg1)] = operacao.andLogicoReg(this.regs.regX[seletorDeReg(this.idReg1)], memoria.exibir(this.idReg2));
				  
			  }else {
				  operacao.andLogicou(memoria.exibir(this.idReg1), this.regs.regX[seletorDeReg(this.idReg2)]);
				  
			  }
			  
		}else if(opcode == 0b101) {//Controle do NOT
			
			  if(this.flagMEMO1 == 0) {
				  this.regs.regX[seletorDeReg(this.idReg1)] = operacao.notReg(this.regs.regX[seletorDeReg(this.idReg1)]);
				  
			  }else {
				  operacao.not(memoria.exibir(this.idReg1));
				  
			  }
			
			
		}else if(opcode == 0b110) {// Controle JUMP IF
			
			jumpifIt(this.idReg1);
			for(int i = aux; i <= aux2 ; i++ ) {
			decodificador.decodificar(memoria.exibir(aux));
			aux++;
			iniciarControle(decodificador.flagMemoria1, decodificador.flagMemoria2, decodificador.opCode, decodificador.idReg1, decodificador.idReg2, memoria);
			
			System.out.println("DEntro da recu");
			System.out.println("PC: "+memoria.PC);
			System.out.println((i+1)+"º rodada");
			testereg();
			System.out.println("-");
			System.out.println("Compare: "+regs.flagCompare);
			}
		}else if(opcode == 0b111) {
			jumpFor(this.idReg1);
			for(int i = aux; i <= aux2 ; i++ ) {
				decodificador.decodificar(memoria.exibir(aux));
				aux++;
				iniciarControle(decodificador.flagMemoria1, decodificador.flagMemoria2, decodificador.opCode, decodificador.idReg1, decodificador.idReg2, memoria);
				
				System.out.println("DEntro da recu");
				System.out.println("PC: "+memoria.PC);
				System.out.println((i+1)+"º rodada");
				testereg();
				System.out.println("-");
				System.out.println("Compare: "+regs.flagCompare);
				}
		}else if(opcode == 0b1000) {
			halt();
		}	
	}
	
	public void mov(int flag1, int flag2, int destino, int valor) {
		
		if (flag1 == 1 && flag2 == 1) {
			
			System.out.println("ERRO");
			
		}else if(flag1 == 1 && flag2 == 0) {
			
			System.out.println("ERRO");
			
		}else if(flag1 == 0 && flag2 == 1) {
			
			
			int aux1 = seletorDeReg(destino);
			int aux2 = memoria.exibir(valor);
			if(aux1 == -1) {
				System.out.println("ERRO");
			
			}
			
			this.regs.regX[aux1] = aux2;
					
			
		}else if(flag1 == 0 && flag2 == 0){
			int aux1 = seletorDeReg(destino);
			int aux2 = seletorDeReg(valor);
			this.regs.regX[aux1] = regs.regX[aux2];
		}
		
	}
	
	public int seletorDeReg(int idreg) {// Determinar qual é o registrador a partir do codigo expresso na plalavra
		switch (idreg) {
        		
		case 0b1010101010:
			return 0;
		case 0b1101101101:
		    return 1;
		case 0b1110111010:
		    return 2;
		case 0b1111011110:
			return 3;
		default:
			return -1;
		
		}
	}
	
	
	
	public void testereg() {
		//Exibir os valores dos registradores 
		for(int i =0; i<4; i++){
			System.out.println("Valores Nos REgistradores: "+regs.regX[i]);
		}
		
	}
	
	public void ativarCompare(int reg1, int reg2) {
		
		this.regs.flagCompare = operacao.compare(reg1, reg2); 
		
	}
	
	
	public void jumpFor(int indece) {
		this.aux2 = memoria.PC;
		this.aux = indece;
		
	}
public void jumpifIt(int indece) {
		if(regs.flagCompare == 0) {
	    this.aux2 = memoria.PC;
		this.aux = indece;
		}
	}
	
	public void halt() {
		
		
		System.out.println("Programa finalizado com sucesso! ");
		System.exit(0);

	}
}
