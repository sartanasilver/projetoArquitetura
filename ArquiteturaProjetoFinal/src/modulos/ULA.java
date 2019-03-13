package modulos;

public class ULA {
	
		
	public int compare(int reg1, int reg2) {
		
		if(reg1 == reg2)
			return 1;
			else 
				return 0;	
	}
	
	public int notReg(int reg) {
		System.out.println("NOT: "+(reg*(-1)));
		return (reg*(-1));
		
	}
	
    public void not(int x) {
		
		System.out.println("NOT: "+(~x));
		
	}
	
	public int somaReg( int regX, int regY) {
		
		return (regX = (regX + regY));
		
	}
	public void soma(int x, int y) {
		System.out.println("Soma: "+(x+y));
	}
	
	 public int multiReg(int regX, int regY) {
		 
		return (regX = (regX * regY));
		 
	 }
	 public void multi(int x, int y) {
		 System.out.println("Multiplicação: "+(x*y));
	 }
	 
	 public int subtReg(int regX, int regY) {
		 
		 return (regX = ((regX) - (regY)));
		
		 
	 }
	 
	 public void subt(int x, int y) {
		 System.out.println("Subtração: "+(x-y));
		 
	 }
	 
     public int andLogicoReg(int regX, int regY) {
		 
		 return (regX = (regX & regY));
		 
	 }
     
     public void andLogicou(int x, int y) {
    	 System.out.println("AND logico: "+(x&y));
     }
	 
	 
	 
	 
	
	
	

}
