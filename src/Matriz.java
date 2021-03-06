/**
 *
 * @author pargles
 * @version 3.0
 */
public class Matriz {
    public int linhas;
    public int colunas;
    public double matrizAmpliada[][];
    public double matriz[][];
    public double termosIndependentes[];
    private String solucao;//string utilizada para ver se sistema tem unica solucao, infinitas ou é impossível

    public Matriz(int linhas,int colunas, double[][] vetor)
    {
        this.linhas = linhas;
        this.colunas = colunas;
        matrizAmpliada = new double [linhas][colunas];
        termosIndependentes=new double[linhas];//coeficientes separados
        setMatrizAmpliada(vetor);
    }
    
    //metodo construtor
    public Matriz(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        matrizAmpliada = new double [linhas][colunas];//matriz e separa dos coeficientes pois necessario efetuar calculos apenas com ela
        termosIndependentes=new double[linhas];//coeficientes separados
    }

    public Matriz()
    {
        //construtor vazio
    }
    
    /* metodo que retorna a matrizAmpliada identidade da matrizAmpliada atual
     * @param void
     * @return Matriz identidade
     */
    public Matriz identidade()
    {
        Matriz identidade = new Matriz();
        return identidade;
    }

    /* metodo que seta a dimensao da matrizAmpliada
     * @param int linhas e colunas
     * @return void
     */
    public void setDimensao(int linhas, int colunas)
    {
        this.linhas = linhas;
        this.colunas = colunas;
        matriz = new double [linhas][colunas];
        matrizAmpliada = new double [linhas][colunas];
    }

    public void setMatrizAmpliada(double[][]m)
    {
        for(int i =0;i<linhas;i++)
        {
            System.arraycopy(m[i], 0, this.matrizAmpliada[i], 0, colunas);
        }
        setCoeficientes();//coloca os coeficientes em um vetor separado
        setMatriz();//separa a matriz sem os coeficientes
    }
    
    /* metodo privado que seta automaticamente a matriz sem coeficientes
     * @param void
     * @retur void
     */
    private void setMatriz()
    {
        matriz = new double[linhas][colunas-1];
        for(int i=0;i<linhas;i++)
        {
            System.arraycopy(matrizAmpliada[i], 0, matriz[i], 0, colunas-1);//nao coloca a ultima coluna, por isso colunas-1
        }
    }
    
     /* metodo privado que seta automaticamente os coeficientes
     * @param void
     * @return void
     */
    private void setCoeficientes()
    {
        termosIndependentes = new double[linhas];
        for(int i = 0;i<linhas;i++)
        {
            termosIndependentes[i]= matrizAmpliada[i][colunas-1];
        }
    }
    
    /* metodo que retorna o vetor de coeficientes
     * @param void
     * @return int[] matrizCoeficientes
     */
    public double[] getCoeficientes()
    {
        return termosIndependentes;
    }
    
    /* metodo que retorna a matriz sem os coeficientes
     * @param void
     * @return double[][] matriz
     */
    public double[][] getMatriz()
    {
        return matriz;
    }
    
    public void setSolucao() {
    	this.solucao = this.verificador();
    }
    
    public String getSolucao() {
    	return this.solucao;
    }
    
    /* metodo que retorna se matrizAmpliada tem solucao unica
     * infinita ou nao tem solucao
     * @param void
     * @return void
     */
    public String verificador() {
    	boolean flag;
    	double fatorMult = 0;
    	
    	for (int i = 0; i < (this.linhas - 1); i++) {
    		for (int j = i + 1; j < this.linhas; j++) {
    			if (this.matriz[i][0] != 0) {
    				fatorMult = (this.matriz[j][0] / this.matriz[i][0]);
	    			flag = true;
	    			for (int k = 1; k < (this.colunas - 1); k++) {
	    				if (this.matriz[i][k] != 0) {
	    					if (fatorMult != (this.matriz[j][k] / this.matriz[i][k])) {
	    						flag = false;
	    						break;
	    					}
	    				}
	    				else {
	    					flag = false;
	    					break;
	    				}
	    			}
    			}
    			else {
    				flag = false;
    			}
    			if (flag) {
    				if (fatorMult == (this.termosIndependentes[j] / this.termosIndependentes[i])) {
    					return "Indeterminado";
    				}
    				else {
    					return "Incompativel";
    				}
    			}
    		}
    	}
    	return "Determinado";
    }

}
