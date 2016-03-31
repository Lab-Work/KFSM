package targetGroundTruth;

import org.jblas.DoubleMatrix;

import doubleMatrix.GaussianGenerator;

public class GroundTruth extends TargetGroundTruth{
	
	public GroundTruth(int _cells, double _dt, double _dx){
		initial(_cells, _dt, _dx);
	}

	public void propagateGround(double _input) {
		DoubleMatrix input=DoubleMatrix.zeros(cells, 1);
		input.put(0,0, _input*((dt)/dx));
		modelGenerator = new GaussianGenerator(QMatrix.mul(1));
		DoubleMatrix noiseModel = modelGenerator.sample();
		DoubleMatrix _densitynext=DoubleMatrix.zeros(trueStatesGround.getRows(), 1);
		trueStatesGroundPrior=trueStatesGround.dup();
		_densitynext=AMatrix.mmul(trueStatesGroundPrior).add(input);
		trueStatesGround=_densitynext.dup();
	}
	
}
