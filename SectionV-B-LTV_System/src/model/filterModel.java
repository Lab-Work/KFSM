package model;

import org.jblas.DoubleMatrix;

import doubleMatrix.GaussianGenerator;

public class filterModel extends ModelKF{

	public filterModel() {
	}	
	
	public void initialThis() {	
		measureVar=trueSolution.measureVarDensity;		
		sizeMeasurements = measureVar.getColumns();

		size=cells;
		//**Start setting output matrix
		measure = DoubleMatrix.zeros(sizeMeasurements,size);
		measure.put(0,0,0);
		measure.put(0,1,1);
		//**End setting output matrix
	}

	
	public DoubleMatrix propagate(DoubleMatrix _density) {
		modelGenerator = new GaussianGenerator(modelVarKF.mul(0.01));
		DoubleMatrix noiseModel = modelGenerator.sample();
		DoubleMatrix _densitynext=DoubleMatrix.zeros(_density.getRows(), 1);
		//here assuming the inflow and out flow of each section is completely known
		_densitynext=trueSolution.AMatrix.mmul(_density).add(noiseModel);
		return _densitynext;		
	}

    public DoubleMatrix getMeasureVector(){ 
    	return trueSolution.measurementsDensity;
    }
    
}
