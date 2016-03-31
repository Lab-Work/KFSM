package targetGroundTruth;

import org.jblas.DoubleMatrix;
import doubleMatrix.GaussianGenerator;
import java.util.Random;

public class GroundTruth extends TargetGroundTruth{
	
	public GroundTruth(int _cells){
		initial(_cells);
	}

	public void propagateGround() {
		modelGenerator = new GaussianGenerator(QMatrix.mul(1));
		DoubleMatrix noiseModel = modelGenerator.sample();
		DoubleMatrix _densitynext=DoubleMatrix.zeros(trueStatesGround.getRows(), 1);
		trueStatesGroundPrior=trueStatesGround.dup();
		
		Random rnda = new Random();
		double sw=rnda.nextDouble();
		
		if (sw<=0.5){
			_densitynext=AMatrix.mmul(trueStatesGroundPrior);
			trueStatesGround=_densitynext.dup();
		}
		else{
			if (AMatrix.equals(AMatrix1)){
				AMatrix=AMatrix2.dup();
			}
			else{
				AMatrix=AMatrix1.dup();
			}
			_densitynext=AMatrix.mmul(trueStatesGroundPrior);
			trueStatesGround=_densitynext.dup();
		}

	}
	
}
