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
	public DoubleMatrix QMatrix;
	
	double dt;
	double dx;
		
	abstract public void propagateGround(double _input);
	
	public void initial(int _cells, double _dt, double _dx) {
	
		cells=_cells;
		dt=_dt;
		dx=_dx;

		trueStatesGround=DoubleMatrix.zeros(cells,1);
		
		//**Start setting initial condition for the true state
		for (int i=0; i<(int)(cells*0.5);i++){
			 trueStatesGround.put(i,0,0.25);
		}
		for (int i=(int)(cells*0.5); i<cells;i++){
			 trueStatesGround.put(i,0,0.5);
		}

		//**End setting initial condition for the true state
	    trueStatesGroundPrior=trueStatesGround.dup();
	    
	    //**Start setting A Matrix
	    AMatrix=DoubleMatrix.zeros(cells,cells);
	    for (int i=0;i<cells;i++){
	    	AMatrix.put(i,i,1.0-(dt)/dx);
	    }
	    for (int i=0;i<cells-1;i++){
	    	AMatrix.put(i+1,i,(dt)/dx);
	    }
	    
	    //**End setting A Matrix
	    
	    QMatrix=DoubleMatrix.eye(cells).mul(0.0081);
	    double correlation=0.0009;
	    
	    for (int i=0; i<cells-1; i++){
	    	QMatrix.put(i,i+1,correlation);
	    	QMatrix.put(i+1,i,correlation);
	    }
	    		
		numUp = 0;
	}



	public void update(double _input) {		
		numUp++;
		propagateGround(_input);	
	}
	

}
