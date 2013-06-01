import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Java class to parse the test file input data in order to 
public class Parser {
	static String dataDirectory = "data/";
	static String dataFile = "adel.test";
	static ArrayList<String> data = new ArrayList<String>();
	static ArrayList<Integer> indexes = new ArrayList<Integer>();
	public static void main(String[] args){
		
		data = readFile(dataDirectory+dataFile);
		if(data==null){
			System.out.println("No data found");
		}else{
			for(String line: data){
				String[] values = line.split(",");
				for(int i = 0; i<values.length;i++){
					if(values[i].equals("?")){
						//System.out.println(i);
						if(!indexes.contains(i)){
							indexes.add(i);
						}
					}
				}
			}
		}
		Collections.sort(indexes,new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        });
		for(int index: indexes){
			System.out.println(index);
		}
	}
	
	public static ArrayList<String> readFile(String filename){
		
		ArrayList<String> filedata = new ArrayList<String>();
		
		if (filename == ""){
			return filedata;
		}
	
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String inputLine;
			while((inputLine=br.readLine())!=null){
				filedata.add(inputLine);
			}
			return filedata;
		}
		catch (IOException e) {
			e.printStackTrace();
			return filedata;
		}
	}

	
}
