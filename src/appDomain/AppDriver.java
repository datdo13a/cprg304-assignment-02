package appDomain;
import java.io.*;

//import javax.xml.parsers.*;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.xml.sax.SAXException;

import implementations.MyStack;
import implementations.MyQueue;

public class AppDriver {
	
	/**
	 * @author airzy, eric, dat, sohan
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length > 0) {
			String filepath = args[0];
			System.out.println("Filepath: " + filepath);//testing
			MyStack<String> readingStack = new MyStack<String>();
			MyQueue<String> errorQ = new MyQueue<String>();
			MyQueue<String> extrasQ = new MyQueue<String>();
			
			try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
				String line;
				while((line=reader.readLine().trim()) != null) {
					//checks if the line contains a starting tag, and adds it to the stack if it is
					if(line.startsWith("<") && line.endsWith(">") && !line.endsWith("/>") && !line.startsWith("</") && !line.startsWith("<?")) {
						readingStack.push(line);
						System.out.println("Head: " + readingStack.peek() + ", Size: " + readingStack.size());
						
					}
					//checks if the line contains an ending tag, and if it is and matches the stack's head closing tag then pop the head
					if(line.startsWith("</") && line.endsWith(">")) {
						
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else {
			System.err.println("please enter the file you want to read");
		}
	}
}


//failed attempt at using built in parsers. only use again later if struggling<------------------
//DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
//
//try {
//	DocumentBuilder b = f.newDocumentBuilder();
//	
//	Document d = b.parse(new File(filepath));
//	
//	Element rootElement = d.getDocumentElement();
//	
//	System.out.println("RootElement = " + rootElement.getNodeName());//for testing 
//	
//	for(int i = 0; i < rootElement.) {
//		
//	}
//} catch (ParserConfigurationException |SAXException | IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}