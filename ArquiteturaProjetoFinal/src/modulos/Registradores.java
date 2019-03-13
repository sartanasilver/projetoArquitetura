package modulos;

public class Registradores {
	
	public int reg1, reg2, reg3, reg4;
	public int[] regX = new int[4];
	public int pc;
	public int flagCompare = -1;
	
	public int getValorReg(int indice) {
		return regX[indice];
	}

}
