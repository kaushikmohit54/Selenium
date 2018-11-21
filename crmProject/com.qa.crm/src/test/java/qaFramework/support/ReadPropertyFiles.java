package qaFramework.support;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class ReadPropertyFiles {


	/****************************************
	 Function to read the path
	*****************************************/
	public Properties Read_FilePath() throws Exception {
		Properties autoitProps = new Properties();
		InputStream is = this.getClass().getResourceAsStream(
				System.getProperty("path.file",
						"/propertiesFiles/path.properties"));
		autoitProps.load(is);
		return autoitProps;
	}

	/**********************************
	 Function to read the auto it path
	**********************************/
	public Properties ReadAutoit_FilePath() throws Exception {
		Properties autoitProps = new Properties();
		InputStream is = this.getClass().getResourceAsStream(
				System.getProperty("autoit.file",
						"/PropertiesFiles/autoItPath.properties"));
		autoitProps.load(is);
		return autoitProps;
	}
	public String pathToUploadFile (String fileType)throws Exception {
		Properties prop = new Properties();
		InputStream input = this.getClass().getResourceAsStream(
				System.getProperty("path.file",
						"/propertiesFiles/path.properties"));
		prop.load(input);
		
		String saveImagePath = prop.getProperty(fileType);
		File strFile = new File(System.getProperty("user.dir") + saveImagePath);
		String strFilePath = strFile.toString();
		return strFilePath;
	}
	public ArrayList<String> pathToUploadFile (ArrayList<String> fileType)throws Exception {
		
		ArrayList<String> list=new ArrayList<String>();
		for(String arr:fileType){
			Properties prop = new Properties();
			InputStream input = this.getClass().getResourceAsStream(
					System.getProperty("path.file",
							"/propertiesFiles/path.properties"));
			prop.load(input);
			String saveImagePath = prop.getProperty(arr);
			File strFile = new File(System.getProperty("user.dir") + saveImagePath);
			String  strFilePath = strFile.toString();
			list.add(strFilePath);
			
		}
		return list;
	}
	
	public Properties ReadEnvironment() throws Exception {
		Properties environmentProps = new Properties();
		String propertiesFilename = System.getProperty("environment.file", "/propertiesFiles/environment.properties");
		InputStream is = this.getClass().getResourceAsStream(propertiesFilename);
		environmentProps.load(is);
		return environmentProps;
	}
	
/*	public Properties Read_FilePath() throws Exception {
		Properties autoitProps = new Properties();
		InputStream is = this.getClass().getResourceAsStream(System.getProperty("path.file", "/PropertyFiles/path.properties"));
		autoitProps.load(is);
		return autoitProps;
}*/
	

}
