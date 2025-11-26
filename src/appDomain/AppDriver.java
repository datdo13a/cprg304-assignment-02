package appDomain;

/**
 * the main app
 * @author Airzy, Eric, Dat, Sohan
 * @version 1.0
 */
public class AppDriver {

	/**
	 * this is the main method
	 * 
	 * sample args:
	 *./res/sample1.xml
	 *./res/sample2.xml
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length <= 0) {
			System.err.println("please enter the file you want to read");
			return;
		}

		String filepath = args[0];
		Parser xmlParser = new Parser();
		xmlParser.parse(filepath);
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