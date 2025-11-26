package appDomain;
import java.io.*;

import exceptions.EmptyQueueException;

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
	 * @throws EmptyQueueException 
	 */
	public static void main(String[] args) throws EmptyQueueException {
		if(args.length > 0) {
			String filepath = args[0];
			System.out.println("Filepath: " + filepath);//testing
			MyStack<String> readingStack = new MyStack<String>();
			MyQueue<String> errorQ = new MyQueue<String>();
			MyQueue<String> extrasQ = new MyQueue<String>();
			
			try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
				String line;
				int lineNum = 0;
				String errorMsg;
				while((line=reader.readLine().trim()) != null) {
					lineNum++;
					errorMsg = "Error at line " + lineNum + ": " + line + " is not constructed correctly.";
					//checks if the line contains a starting tag, and adds it to the stack if it is
					if(line.startsWith("<") && line.endsWith(">") && !line.endsWith("/>") && !line.startsWith("</") && !line.startsWith("<?")) {
						readingStack.push(line);
						
					}
					//checks if the line contains an ending tag, and if it is and matches the stack's head closing tag then pop the head
					if(line.startsWith("</") && line.endsWith(">")) {
						if(getTagName(line) == getTagName(readingStack.peek())) {
							readingStack.pop();
							//if the tag matches the tag of the top of errorQ, will dequeue it
						}else if(getTagName(line) == getTagName(errorQ.peek())) {
							errorQ.dequeue();
							//if the reading stack is already empty, enqueue it into the errorq
						}else if(readingStack.isEmpty()) {
							errorQ.enqueue(line);
						}else {
							//searches for a possible matching start tag 
							if(readingStack.contains(line)) {//TODO find out how to search the stack properly so it can actually work
								for(int i = 0; i < readingStack.size(); i++) {
									if(getTagName(readingStack.peek())==getTagName(line)) {
										extrasQ.enqueue(readingStack.peek());
										System.out.println(errorMsg);
										break;
									}else {
										errorQ.enqueue(readingStack.pop());
									}
								}
							}
						}
					}
				}
				
				if(!readingStack.isEmpty()) {
					for(int i = 0; i < readingStack.size(); i++) {
						errorQ.enqueue(readingStack.pop());
					}
				}
				if(errorQ.isEmpty() ^ extrasQ.isEmpty()) {
					while(!errorQ.isEmpty()) {
						System.out.println("Error: " + errorQ.dequeue() + "is not constructed correctly");//TODO find how to keep line numbers
					}
					while(!extrasQ.isEmpty()) {
						System.out.println("Error: " + errorQ.dequeue() + "is not constructed correctly");
					}
				}
				
				while(!errorQ.isEmpty() && !extrasQ.isEmpty()) {
					if(getTagName(errorQ.peek()) != getTagName(extrasQ.peek())) {
						System.out.println("Error: " + errorQ.dequeue() + " is not constructed correctly");
					}else {
						errorQ.dequeue();
						extrasQ.dequeue();
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
	
	public static String getTagName(String tag) {
	    String tagName = tag.trim();
	    // Remove leading '<' and any '/', '?', or '!' after it
	    tagName = tagName.replaceFirst("^<\\s*[!?/]*", "");
	    // Split at the first whitespace or '>', those indicate end of tag name
	    int end = tagName.indexOf(' ');
	    int gt = tagName.indexOf('>');
	    if (end < 0 || (gt >= 0 && gt < end)) end = gt;
	    if (end < 0) end = tagName.length();
	    return tagName.substring(0, end);
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