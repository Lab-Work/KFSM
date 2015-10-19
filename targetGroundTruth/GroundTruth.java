package targetGroundTruth;

import org.jblas.DoubleMatrix;

import doubleMatrix.GaussianGenerator;

public class GroundTruth extends TargetGroundTruth{
	
	public GroundTruth(int _cells){
		initial(_cells);
	}

	public void propagateGround() {
		modelGenerator = new GaussianGenerator(QMatrix.mul(1));
		DoubleMatrix noiseModel = modelGenerator.sample();
		DoubleMatrix _densitynext=DoubleMatrix.zeros(trueStatesGround.getRows(), 1);
		trueStatesGroundPrior=trueStatesGround.dup();
		_densitynext=AMatrix.mmul(trueStatesGroundPrior);
		trueStatesGround=_densitynext.dup();
	}
	
}
