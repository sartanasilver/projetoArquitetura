package modulos;

public class Memoria {
	
	public int[] memoria = new int[235];
	public int PC = 0;
	
	public void salvar(int palavra, int pc) {
		
		memoria[pc] = palavra;
		
	}
	
	public int exibir(int pc) {
		return memoria[pc];
	}
	
	

}
