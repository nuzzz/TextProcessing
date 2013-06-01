import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;



public class Classifer {
	
	static final String trainFilename = "data-arff/bris.train.arff";
	static final String testFilename = "data-arff/bris.dev.arff";
	static final String outFilename = "data-arff/bris.out.arff";
	
	public enum classifierType { J48 , NaiveBayes};
	
	public static void main(String[] args) throws Exception{
		BufferedReader breader = null;
		BufferedWriter bwriter = null;
		
		breader = new BufferedReader(new FileReader(trainFilename));
		Instances train = new Instances(breader);
		//last attribute is the classified type
		train.setClassIndex(train.numAttributes() -1);
		
		breader = new BufferedReader(new FileReader(testFilename));
		Instances test = new Instances (breader);
		//last attribute is the classified type
		test.setClassIndex(train.numAttributes() -1);
		
		Instances labeled = new Instances(test);
		
		breader.close();
		
		classifierType classifier = classifierType.NaiveBayes;
		
		switch (classifier)
		{
			case J48:
				J48 tree = new J48();
				tree.buildClassifier(train);
				
				for(int i=0; i<test.numInstances();i++){
					double classLabel = tree.classifyInstance(test.instance(i));
					labeled.instance(i).setClassValue(classLabel);
				}
				
				bwriter = new BufferedWriter(
						new FileWriter(outFilename));
				bwriter.write(labeled.toString());
				
				break;
			case NaiveBayes:
				NaiveBayes nb = new NaiveBayes();
				nb.buildClassifier(train);
				
				for(int i=0; i<test.numInstances();i++){
					double classLabel = nb.classifyInstance(test.instance(i));
					labeled.instance(i).setClassValue(classLabel);
				}
				
				bwriter = new BufferedWriter(
						new FileWriter(outFilename));
				bwriter.write(labeled.toString());
			default:
				break;
		}
	}

}
