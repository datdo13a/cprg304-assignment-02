package appDomain;

import exceptions.EmptyQueueException;

public class AppDriver {

	// example arg: ./res/sample1.xml or ./res/sample2.xml
	/**
	 * @author airzy, eric, dat, sohan
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length <= 0) {
			System.err.println("please enter the file you want to read");
			return;
		}

		String filepath = args[0];
		Parser xmlParser = new Parser();
		try { // todo maybe move this try catch inside the parser
			xmlParser.parse(filepath);
		} catch (EmptyQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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