import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class WekaTest {
	public static void main(String[] args){
		BufferedReader read = null;
		Instances train = null;
		String file = "data-arff/bris.train.arff";
		try {
			read = new BufferedReader(new FileReader(file));
			
			train = new Instances(read);
			train.setClassIndex(train.numAttributes() -1);
			
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found at : " + file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		NaiveBayes nb = new NaiveBayes();
		try {
			nb.buildClassifier(train);
			Evaluation eval = new Evaluation(train);
			eval.crossValidateModel(nb, train, 10, new Random(1));
			System.out.println(eval.toSummaryString("\nResults\n=======\n", true));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		

}
