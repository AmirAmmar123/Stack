package il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


public class EvaluatePostfix {

	private static final StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static final Stack<Double> myStack = new DLinkedListStack<>();
	
	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');


		int v  = 0;
		v= tokenizer.nextToken();
		while(v != StreamTokenizer.TT_EOF){
			if( v == StreamTokenizer.TT_WORD ){
				if ("quit".equals(tokenizer.sval)) {
					break;
				}
			}else if(v == StreamTokenizer.TT_NUMBER){
				Double val = tokenizer.nval;
				myStack.push(val);
			}
			else{
				char c = (char)tokenizer.ttype;
				checkCases(c);
			}
			v= tokenizer.nextToken();
		}
		if(myStack.isEmpty()){
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(1);
		}else{
			Double value = myStack.pop();
			if(!myStack.isEmpty()){
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(1);
			}else{
				System.out.println(value);
			}

		}


	}


	private static void checkCases(char c){
		Double x = myStack.pop();
		if(myStack.isEmpty()){
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(1);
		}
		Double y = myStack.pop();

		switch (c){
			case '+':{
				Double result = y + x;
				myStack.push(result);
				break;
			}

			case '-':{
				Double result = y - x;
				myStack.push(result);
				break;
			}

			case '*':{
				Double result = x * y;
				myStack.push(result);
				break;
			}
			case '/':{
				Double result = y / x;
				myStack.push(result);
				break;
			}
			default:{
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(1);
				break;
			}

		}
	}
}
