package targetGroundTruth;

import org.jblas.DoubleMatrix;

import doubleMatrix.GaussianGenerator;

public abstract class TargetGroundTruth {

	public int cells;
	
	public DoubleMatrix trueStatesGround;
	public DoubleMatrix trueStatesGroundPrior;
	GaussianGenerator modelGenerator;
	
	public int numUp;
	
	public DoubleMatrix AMatrix;
	public DoubleMatrix AMatrix1;
	public DoubleMatrix AMatrix2;
	public DoubleMatrix QMatrix;
		
	abstract public void propagateGround();
	
	public void initial(int _cells) {
	
		cells=_cells;

		trueStatesGround=DoubleMatrix.zeros(cells,1);
		
		//**Start setting initial condition for the true state
	    trueStatesGround.put(0,0,2);
	    trueStatesGround.put(1,0,1);
		//**End setting initial condition for the true state
	    trueStatesGroundPrior=trueStatesGround.dup();
	    
	    //**Start setting A Matrix
	    
	    AMatrix1=DoubleMatrix.zeros(cells,cells);
	    AMatrix1.put(0,0,1.05);
	    AMatrix1.put(1,0,0.1);
	    AMatrix1.put(1,1,0.9);
	    
	    AMatrix2=DoubleMatrix.zeros(cells,cells);
	    AMatrix2.put(0,0,1.05);
	    AMatrix2.put(1,0,0.2);
	    AMatrix2.put(1,1,0.8);
	    
	    AMatrix=AMatrix1.dup();
	    //**End setting A Matrix
	    
	    QMatrix=DoubleMatrix.zeros(cells,cells);
	    QMatrix.put(0,0,9);
	    QMatrix.put(1,0,16);
	    QMatrix.put(0,1,16);
	    QMatrix.put(1,1,36);
	    		
		numUp = 0;
	}



	public void update() {		
		numUp++;
		propagateGround();	
	}
	

}
