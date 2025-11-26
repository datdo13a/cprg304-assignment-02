package appDomain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import implementations.MyArrayList;
import implementations.MyQueue;
import implementations.MyStack;

import exceptions.EmptyQueueException;

/**
 * this class is the XML parser
 * deals with finding the errors in an xml file
 * @author Airzy, Eric, Dat, Sohan
 * @version 1.0
 */
public class Parser {
	
	public Parser() {
		
	}
	
	/**
	 * private function to extract tags from a line, used in the parser
	 * @param line
	 * @return tags, arraylist of tagEntries
	 */
	static MyArrayList<String> extractTags(String line) {
		MyArrayList<String> tags = new MyArrayList<String>();
		int i = 0;
		while (i < line.length()) {
			int start = line.indexOf('<', i);
			if (start == -1)
				break;
			int end = line.indexOf('>', start);
			if (end == -1)
				break;
			tags.add(line.substring(start, end + 1));
			i = end + 1;
		}
		return tags;
	}
	
	/**
	 * reads from xml file and returns a list of the tags
	 * @param filepath file to read from
	 * @return allTags, arraylist of tagEntries
	 */
	private MyArrayList<TagEntry> readFilePath(String filepath) {
		MyArrayList<TagEntry> allTags = new MyArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			String line;
			int lineNum = 0;

			while ((line = reader.readLine()) != null) {
				lineNum++;
				line = line.trim();

				// extract all tags on this line (if any)
				MyArrayList<String> tags = extractTags(line);

				for (int i = 0; i < tags.size(); i++) {
					String tag = tags.get(i);
					allTags.add(new TagEntry(tag, lineNum));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allTags;
	}
	
	// build in insertion sort lol
	private TagEntry[] insertionSortByLine(TagEntry[] arr) {
	    if (arr == null) return null;
	    int n = arr.length;
	    for (int i = 1; i < n; i++) {
	        TagEntry key = arr[i];
	        int j = i - 1;
	        
	        //
	        while (j >= 0 && arr[j].line > key.line) {
	            arr[j + 1] = arr[j];
	            j--;
	        }
	        arr[j + 1] = key;
	    }
	    return arr;
	}

	
	/**
	 * reads tags and performs the kittyparser algo to find errors
	 * this basicly the part in the kittyparser example
	 * @param allTags
	 * @throws EmptyQueueException
	 */
	private void startParser(MyArrayList<TagEntry> allTags) throws EmptyQueueException {
		MyStack<TagEntry> readingStack = new MyStack<TagEntry>();
		MyQueue<TagEntry> errorQ = new MyQueue<TagEntry>();
		MyQueue<TagEntry> extrasQ = new MyQueue<TagEntry>();

		// basicly the kitty parser template
		// TODO needs bit more work
	
		MyStack<TagEntry> errorsList = new MyStack<TagEntry>();
		for (int i = 0; i < allTags.size(); i++) {
			TagEntry te = allTags.get(i);
			if (te.isSelfClosing()) { // if self closing tag
				continue; // ignore
			}

			if (te.isStartTag()) { // if start tag
				readingStack.push(te); // push on stack
				continue;
			}

			if (te.isEndTag()) { // if end tag
				String endName = te.getTagName();

				// Else if stack is empty, add to errorQ
				if (readingStack.isEmpty()) {
					extrasQ.enqueue(te);
					continue;
				}

				TagEntry top = readingStack.peek();
				String topName = top.getTagName();
				// if matches top of stack
				if (topName.equals(endName)) {
					readingStack.pop(); // pop stack
					continue; // all is well
				}

				// Else if matches head of errorQ, dequeue and ignore
				if (!errorQ.isEmpty() && errorQ.peek().getTagName().equals(endName)) {
					errorQ.dequeue();
					continue;
				}

				// Else
				boolean found = false;
				MyStack<TagEntry> temp = new MyStack<>();

				while (!readingStack.isEmpty()) {
					TagEntry popped = readingStack.pop();
					if (popped.getTagName().equals(endName)) {
						found = true;
						break;
					}
					temp.push(popped);
				}

				// stack has a match
				if (found) {
					while (!temp.isEmpty()) {
						errorQ.enqueue(temp.pop());
					}
				} else {
					// Add E to extrasQ
					extrasQ.enqueue(te);
					while (!temp.isEmpty()) {
						readingStack.push(temp.pop());
					}
				}
			}

			//

			// If stack is not empty, pop each E into errorQ
			while (!readingStack.isEmpty()) {
				errorQ.enqueue(readingStack.pop());
			}

			// uhh
			
			// If both queues are not empty, peek both queues
			while (!errorQ.isEmpty() && !extrasQ.isEmpty()) {

				TagEntry e = errorQ.peek();
				TagEntry x = extrasQ.peek();

				if (e.getTagName().equals(x.getTagName())) {
					errorQ.dequeue();
					extrasQ.dequeue();
				} else {
					TagEntry reported = errorQ.dequeue();
					errorsList.push(reported);
				}
			}

			// Repeat until both queues are empty
			while (!errorQ.isEmpty()) {
				//TagEntry reported = errorQ.dequeue();
				errorQ.dequeue();
			}

			while (!extrasQ.isEmpty()) {
				TagEntry reported = extrasQ.dequeue();
				errorsList.push(reported);
			}
		}
		
		if (errorsList.size() == 0) { // yay no errors
			System.out.println("XML document is constructed correctly.");
		} else {
			// this part sorts the errors by line number and displays...
			
		    Object[] objArr = errorsList.toArray();
		    int n = objArr.length;

		    // cast to TagEntry[]
		    TagEntry[] arr = new TagEntry[n];
		    for (int i = 0; i < n; i++) {
		        arr[i] = (TagEntry) objArr[i];
		    }
		    
		    insertionSortByLine(arr);

		    for (int i = 0; i < n; i++) {
		        TagEntry e = arr[i];
		        System.out.println("Error at line: " + e.line + " " + e.tag + " is not constructed correctly.");
		    }
		}
	}
	
	/**
	 * parses file into tags
	 * @param filepath
	 */
	public void parse(String filepath) {
		System.out.println("Filepath: " + filepath);
		MyArrayList<TagEntry> allTags = readFilePath(filepath);

		// for debugging
		/*
		 * for (int i = 0; i < allTags.size(); i++) { TagEntry te = allTags.get(i);
		 * System.out.println("LINE " + te.line + " = " + te.tag);
		 * 
		 * if (te.isStartTag()) { System.out.println("   isStartTag = true"); }
		 * 
		 * if (te.isEndTag()) { System.out.println("     isEndTag = true"); }
		 * 
		 * if (te.isSelfClosing()) { System.out.println(" isSelfClosing = true"); }
		 * 
		 * if (te.isStartTag() || te.isEndTag() || te.isSelfClosing()) {
		 * System.out.println("      tagName = " + te.getTagName()); }
		 * 
		 * System.out.println(); }
		 */

		//

		try {
			startParser(allTags);
		} catch (EmptyQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
