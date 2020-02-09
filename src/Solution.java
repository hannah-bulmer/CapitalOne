import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import org.apache.commons.io.FilenameUtils;

public class Solution {
	  static String JAVA_PATTERN = "(//.*\n)+|/\\*((.|\\n)(?!=*/))+\\*/\n";
	  static String PYTHON_PATTERN = "(#.*\\n)+";
	  static String PY = "py";
	  static String NEWLINE = "\n";

	  public static void main(String[] args) throws FileNotFoundException { 
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Please provide a relative file path: ");
	    String fileName = scanner.next();
	    scanner.close();

	    File file = new File(fileName);
	    String ext = FilenameUtils.getExtension(file.getAbsolutePath());
	    boolean python = (ext.contentEquals(PY));
	     

	    String s = retrieveFile(file);
	    List <String> comments = retrieveComments(s, python);

	    countLines(s);
	    int numComments = countCommentLines(comments, python);
	    getCommentTypes(comments, python, numComments);
	    countBlocks(comments, python);
	    countToDos(comments);
	  }
	  
	  /**
	   * Retrieves a file and parses it into a string
	   * @param file
	   * @return String
	   * @throws FileNotFoundException
	   */
	  static String retrieveFile(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
	    sc.useDelimiter("\\Z");
	    String s = sc.next();
	    sc.close();
	    return s;
	  }
	  
	  /**
	   * Retrieves all comments from file and turns them into an array list
	   * @param s - file string
	   * @param python - if the file was python or not
	   * @return list of comments
	   */
	  static List <String> retrieveComments(String s, boolean python) {
		String patternType = (python ? PYTHON_PATTERN : JAVA_PATTERN);
	  	List <String> comments = new ArrayList<>();
	    Pattern pattern = Pattern.compile(patternType);
	    Matcher matcher = pattern.matcher(s);
	    while (matcher.find()) {
	      String match = matcher.group();
	      comments.add(match);
	    }
	    return comments;
	  }

	  /**
	   * 
	   * @param file
	   * @return Num lines in the file
	   */
	  static int countLines(String file) {
	    int length = file.length() - file.replace(NEWLINE, "").length() + 1;
	    System.out.format("Total # of lines: %d\n", length);
	    return length;
	  }

	  /**
	   * 
	   * @param comments
	   * @param isPython
	   * @return Num comment lines in the file
	   */
	  static int countCommentLines(List <String> comments, boolean isPython) {
	    int count = 0;
	    for (String c: comments) {
	      int numLines = c.length() - c.replace(NEWLINE, "").length();
	     // if (!isPython && numLines > 1) numLines ++; // account
	      count += numLines;
	    }
	    System.out.format("Total # of comment lines: %d\n", count);
	    return count;
	  }

	  static void getCommentTypes(List <String> comments, boolean isPython, int numComments) {
	    int singleComments = countSingleComments(comments, isPython);
	    int blockComments = numComments - singleComments;
	    System.out.format("Total # of comment lines within block comments: %d\n", blockComments);
	  }

	  /**
	   * Counts single comments - check README for details
	   * @param comments
	   * @param isPython
	   * @return num of single comments
	   */
	  static int countSingleComments(List <String> comments, boolean isPython) {
	    int count = 0;
	    if (!isPython) {
	      for (String c: comments) {
	    	  int numLines = c.length() - c.replace("\n", "").length();
		      if (numLines == 1) count ++;
	      }
	    } else {
	      // get the single line comments
	      for (String c: comments) {
	        int numLines = c.length() - c.replace("\n", "").length();
	        if (numLines == 1) count ++;
	      }
	    }
	    System.out.format("Total # of single line comments: %d\n", count);
	    return count;
	  }

	  /**
	   * Counts number of comment blocks
	   * @param comments
	   * @param isPython
	   * @return num comment blocks
	   */
	  static int countBlocks(List <String> comments, boolean isPython) {
	    int count = 0;
	    if (!isPython) {
	      for (String c: comments) {
	        if (c.charAt(0) == '/' && c.charAt(1) == '*') count ++;
	      }
	    } else {
	      // get the single line comments
	      for (String c: comments) {
	        int numLines = c.length() - c.replace(NEWLINE, "").length();
	        if (numLines > 1) count ++;
	      }
	    }
	    System.out.format("Total # of block line comments: %d\n", count);
	    return count;
	  }

	  /**
	   * Counts number of TODOS
	   * Makes the assumption only one TODO appears in each comment
	   * @param comments
	   * @return num TODO
	   */
	  static int countToDos(List <String> comments) {
	    int count = 0;
	    for (String c: comments) {
	      int match = c.length() - c.replace("TODO", "").length();
	      if (match > 0) count ++;
	    }

	    System.out.format("Total # of TODO’s: %d\n", count);
	    return count;
	  }
}
