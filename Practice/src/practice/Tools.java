package practice;
import java.io.*;

public class Tools {
	
//	static TreeNode[] tree;
	
	public static void printFile2Buf(File fobj) throws IOException{	
		
		byte[] buf = file2buf(fobj);
		for (int i=0; i<buf.length; i++)
			System.out.print(buf[i]+" ");
		
	}
	
	public static byte[] file2buf(File fobj) throws IOException{
		
    	BufferedReader input = new BufferedReader(new FileReader(fobj));
    	String str = "";
    	StringBuilder context = new StringBuilder();
    	/**
    	 * 利用StringBuilder将文本转换成字符串context，设置条件避免文本为空
    	 * */
    	while (str!=null){
    		str = input.readLine();
    		if (str==null) break;
    		context.append(str);
    	}    
    	
    	int size = context.length();
    	byte[] buf = new byte[size];    	
    	/**
    	 * 依次将字符串转换为byte数组
    	 * */
    	for (int i=0; i<size; i++){
    		buf[i] = (byte)context.charAt(i);
    	}
    	System.out.println(context);
    	return buf;
    }
	  
    public static StringBuilder intToHex(int intTo) {
    	StringBuilder str=new StringBuilder("");
    	int j=1;
    	char a;
        boolean isInt;
        if (intTo<0){
        	isInt = true;
        	intTo = -intTo;
        }
        else isInt = false;
    	do{
    		j = intTo%16;
    		if (isInt)	j = -j;
    		if (j<10)	a = (char)(48+j);
    		else a = (char)(65+j-10);
    		str.append(a);
    		intTo = intTo/16;
    	}	while (intTo!=0);
    	if (isInt)  str.append('-');
    	str.reverse();
    	return str;
    } 
    	
    public static String treeLevel(TreeNode tree,int n){
    	String leftString,rightString;
    	if (n==1) return tree.value;
    	
    	leftString = (tree.left!=null?treeLevel(tree.left, n-1):"");
    	rightString = (tree.right!=null?treeLevel(tree.right, n-1):"");
    	return leftString+rightString;
    }
    
    public static TreeNode[] buildTree(File fobj) throws IOException{
    	FileReader is = new FileReader (fobj);
    	BufferedReader br = new BufferedReader(is);
    	int num = Integer.parseInt(br.readLine());
    //	System.out.println(num);
    	TreeNode[] tree = new TreeNode[num];
    	int temp;
    	for (int i=0; i<num; i++){
    		char a = (char)br.read();        
  //  		System.out.println(a);
    		tree[i] = new TreeNode(a);
    	}
    	for (int i=0; i<num; i++){
    		temp = 2*(i+1);
    		if (temp<=num) tree[i].left = tree[temp-1];
    		if (temp+1<=num) tree[i].right = tree[temp];
    	}
    	is.close();
    	return tree;
    }
    
    public static void printTreeLevel(TreeNode tree,int n) throws IOException{   		
    	System.out.println(treeLevel(tree,n));
    }
} 

class TreeNode{
	String value="";
	TreeNode left,right;
	
	public TreeNode(char a){
		value += a;
	}
}