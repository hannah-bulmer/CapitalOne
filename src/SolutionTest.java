import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException; 

import org.junit.Test;

public class SolutionTest {
	
	@Test
	public void testRetrieveComments() throws FileNotFoundException {
		Solution s = new Solution();
		File f = new File("./src/TestFiles/test1.java");
		String file = s.retrieveFile(f);
		List <String> comments = s.retrieveComments(file, false);
		assertEquals(comments.get(0), "/*\n" + 
				"* Copyright (c) 2017 Capital One Financial Corporation All Rights Reserved.\n" + 
				"*\n" + 
				"* This software contains valuable trade secrets and proprietary information of\n" + 
				"* Capital One and is protected by law. It may not be copied or distributed in\n" + 
				"* any form or medium, disclosed to third parties, reverse engineered or used in\n" + 
				"* any manner without prior written authorization from Capital One.\n" + 
				"*\n" + 
				"*\n" + 
				"*/");
		assertEquals(comments.get(2), "// @SpringBootApplication is a convenience annotation that adds all of the above.");
		
		f = new File("./src/TestFiles/test2.py");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, true);
		
		assertEquals(comments.get(1), "# A test comment\n");
		assertEquals(comments.get(2), "# Another test comment\n" + 
				"# But this one is two lines\n");

	}

	@Test
	public void testLineCount() throws FileNotFoundException {
		Solution s = new Solution();
		File f = new File("./src/TestFiles/test1.java");
		String file = s.retrieveFile(f);
		assertEquals(s.countLines(file), 53);
		
		f = new File("./src/TestFiles/test2.py");
		file = s.retrieveFile(f);
		assertEquals(s.countLines(file), 52);
		
		f = new File("./src/TestFiles/test3.c");
		file = s.retrieveFile(f);
		assertEquals(s.countLines(file), 72);

		assertEquals(s.countLines("\n\n\n"), 4);
	}
	
	@Test
	public void testCommentCount() throws FileNotFoundException {
		Solution s = new Solution();
		File f = new File("./src/TestFiles/test1.java");

		String file = s.retrieveFile(f);
		List <String> comments = s.retrieveComments(file, false);
		assertEquals(s.countCommentLines(comments, false), 27);
		
		f = new File("./src/TestFiles/test2.py");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, true);
		assertEquals(s.countCommentLines(comments, true), 22);
		
		f = new File("./src/TestFiles/test3.c");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, false);
		assertEquals(s.countCommentLines(comments, false), 12);
	}

	@Test
	public void testSingleComments() throws FileNotFoundException {
		Solution s = new Solution();
		File f = new File("./src/TestFiles/test1.java");
		
		String file = s.retrieveFile(f);
		List <String> comments = s.retrieveComments(file, false);
		assertEquals(s.countSingleComments(comments, false), 6);
		
		f = new File("./src/TestFiles/test2.py");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, true);
		assertEquals(s.countSingleComments(comments, true), 10);
		
		f = new File("./src/TestFiles/test3.c");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, false);
		assertEquals(s.countSingleComments(comments, false), 12);
	}
	
	@Test
	public void testNumBlocks() throws FileNotFoundException {
		Solution s = new Solution();
		File f = new File("./src/TestFiles/test1.java");
		
		String file = s.retrieveFile(f);
		List <String> comments = s.retrieveComments(file, false);
		assertEquals(s.countBlocks(comments, false), 2);
		
		f = new File("./src/TestFiles/test2.py");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, true);
		assertEquals(s.countBlocks(comments, true), 4);
		
		f = new File("./src/TestFiles/test3.c");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, false);
		assertEquals(s.countBlocks(comments, false), 0);
	}
	
	@Test
	public void testNumTODO() throws FileNotFoundException {
		Solution s = new Solution();
		File f = new File("./src/TestFiles/test1.java");
		
		String file = s.retrieveFile(f);
		List <String> comments = s.retrieveComments(file, false);
		assertEquals(s.countToDos(comments), 1);
		
		f = new File("./src/TestFiles/test2.py");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, true);
		assertEquals(s.countToDos(comments), 3);
		
		f = new File("./src/TestFiles/test3.c");
		file = s.retrieveFile(f);
		comments = s.retrieveComments(file, false);
		assertEquals(s.countToDos(comments), 2);
	}
}
