package automation.practice;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;




public class GenerateScreenShotPDF extends BaseTest  {
	
	
	public void pdfScreesnshot() throws Exception 
	
	{
		
		

		// Capture screenshot and store it in byte[] array format
		byte[] input = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		Document document = new Document();
		String output = "F:\\Screenshot" + "Image" + ".pdf";
		FileOutputStream fos = new FileOutputStream(output);

		// Instantiate the PDF writer
		PdfWriter writer = PdfWriter.getInstance(document, fos);

		// open the pdf for writing
		writer.open();
		document.open();

		// process content into image
		Image im = Image.getInstance(input);

		//set the size of the image
		im.scaleToFit(PageSize.A4.getWidth()/2, PageSize.A4.getHeight()/2);

		// add the captured image to PDF
		document.add(im);
		document.add(new Paragraph(" "));

		//close the files and write to local system
		document.close();
		writer.close();
	}
	
	

}
