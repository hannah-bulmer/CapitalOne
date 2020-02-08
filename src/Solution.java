import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import org.apache.commons.io.FilenameUtils;

/*
 Handles Java
 Handles Python
 C? C++?
 Javascript?
*/

public class Solution {
	static String JAVA_PATTERN = "//.*|/\\*((.|\\n)(?!=*/))+\\*/";
	  static String PYTHON_PATTERN = "(#.*\\n)+";

	  // public Solution() {}

	  public static void main(String[] args) throws FileNotFoundException { 
	    Scanner scanner = new Scanner(System.in);
	    String fileName = scanner.next();
	    scanner.close();

	    File file = new File(fileName);
	    String ext = FilenameUtils.getExtension(file.getAbsolutePath());
	    Scanner sc = new Scanner(file);
	    boolean python = ext == "py";
	    String patternType = (python ? PYTHON_PATTERN : JAVA_PATTERN);
	  
	    sc.useDelimiter("\\Z"); 

	    String s = sc.next();
	    List <String> comments = new ArrayList<>();
	    Pattern pattern = Pattern.compile(patternType);
	    Matcher matcher = pattern.matcher(s);
	    while (matcher.find()) {
	      String match = matcher.group();
	      comments.add(match);
	    }

	    countLines(s);
	    int numComments = countCommentLines(comments, python);
	    getCommentTypes(comments, python, numComments);
	    countBlocks(comments, python);
	    countToDos(comments);
	    sc.close();
	  }

	  static int countLines(String file) {
	    int length = file.length() - file.replace("\n", "").length() + 1;
	    System.out.format("Total # of lines: %d\n", length);
	    return length;
	  }

	  static int countCommentLines(List <String> comments, boolean isPython) {
	    int count = 0;
	    for (String c: comments) {
	      int numLines = c.length() - c.replace("\n", "").length() + 1;
	      if (isPython) numLines --; // accounts for saving the extra line in python
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
	   * For python, assumes comments of the form
	        somecode() # comment
	        # comment
	   * counts as a block comment
	   */
	  static int countSingleComments(List <String> comments, boolean isPython) {
	    int count = 0;
	    if (!isPython) {
	      for (String c: comments) {
	        if (c.charAt(0) == '/' && c.charAt(1) == '/') count ++;
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

	  static int countBlocks(List <String> comments, boolean isPython) {
	    int count = 0;
	    if (!isPython) {
	      for (String c: comments) {
	        if (c.charAt(0) == '/' && c.charAt(1) == '*') count ++;
	      }
	    } else {
	      // get the single line comments
	      for (String c: comments) {
	        int numLines = c.length() - c.replace("\n", "").length();
	        if (numLines > 1) count ++;
	      }
	    }
	    System.out.format("Total # of block line comments: %d\n", count);
	    return count;
	  }

	  // Makes the assumption only one TODO appears in each comment
	  // because I thought about this a lot and realistically it makes most sense to do
	  // this way
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
