package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonReader {

	
	
	public List<HashMap<String, String>> readFromJsonFile(String filepath) throws IOException
	{
		String data=FileUtils.readFileToString(new File (filepath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		
		List<HashMap<String,String>> dataa=  mapper.readValue(data,new TypeReference<List<HashMap<String,String>>>(){
		}
		);
		
		return dataa;
	}
	
	
}
