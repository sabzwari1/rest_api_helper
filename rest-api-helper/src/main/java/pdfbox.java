/**
 * 
 */

/**
 * @author sss033
 *
 */
public class pdfbox {

		   this.pdfStripper = null;
		       this.pdDoc = null;
		       this.cosDoc = null;
		       
		       file = new File(filePath);
		       parser = new PDFParser(new RandomAccessFile(file,"r")); // update for PDFBox V 2.0
		       
		       parser.parse();
		       cosDoc = parser.getDocument();
		       pdfStripper = new PDFTextStripper();
		       pdfStripper.setSortByPosition(true);
		       pdDoc = new PDDocument(cosDoc);
		       pdDoc.getNumberOfPages();
		       pdfStripper.setStartPage(1);
		       pdfStripper.setEndPage(pdDoc.getNumberOfPages());
		        
		// reading text from page 1 to 10
		       // if you want to get text from full pdf file use this code
		       // pdfStripper.setEndPage(pdDoc.getNumberOfPages());
		       
		       Text = pdfStripper.getText(pdDoc);
		       pdDoc.close();
		       return Text;
}
}
		  public int noOfPages()throws IOException
		   {
			   this.pdfStripper = null;
		       this.pdDoc = null;
		       this.cosDoc = null;
			   file = new File(filePath);
		       parser = new PDFParser(new RandomAccessFile(file,"r"));
		       parser.parse();
		       cosDoc = parser.getDocument();
		       pdfStripper = new PDFTextStripper();
		       pdDoc = new PDDocument(cosDoc);
		       int number=pdDoc.getNumberOfPages();
		       return number;
			   
		   } 


