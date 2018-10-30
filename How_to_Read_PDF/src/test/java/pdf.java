import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class pdf {
	
	@Test
	public void pdfTest() throws IOException {
		
		URL url=new URL("http://www.africau.edu/images/default/sample.pdf");
		InputStream is=url.openStream();
		BufferedInputStream fileparse=new BufferedInputStream(is);
		PDDocument document=null;
		document =PDDocument.load(fileparse);
		String pdfContent=new PDFTextStripper().getText(document);
		//System.out.println(pdfContent);
		Assert.assertTrue(pdfContent.contains("demonstration"));
		
		
	}

}
