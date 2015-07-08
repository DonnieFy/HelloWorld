package practice;

import java.io.File;
import java.io.IOException;



public class PracticeMain {
	
	public static void main(String args[]) throws IOException{
		
		File fobj = new File("D:/succezIDE/workspace/date/File2Buf.in");
		Tools.printFile2Buf(fobj);
		
		System.out.println();
		System.out.println(Tools.intToHex(Integer.MIN_VALUE));
	//	System.out.println((-33)/16);
		
		fobj = new File("D:/succezIDE/workspace/date/Tree.in");
	    TreeNode[] tree	= Tools.buildTree(fobj);
	    if (tree!=null)
	    	Tools.printTreeLevel(tree[0],4);
	}
	
}