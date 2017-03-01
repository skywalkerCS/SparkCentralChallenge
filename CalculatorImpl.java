package com.calculator;

import java.util.Scanner;

public class CalculatorImpl {

	public static void main(String[] args) {
		System.out.println( "Welcome to Calculator v1.0");
		System.out.println( "Enter your expressions one per line (q to quit)");
		
		Scanner sc = new Scanner(System.in);
		
		while( true ) {
			String expr = sc.nextLine();
			if( expr.equals("q"))
				break;
				
			// Compute the result of expression
			
			// Multiplication and division
			for( int i = 0; i < expr.length(); i++ ) {
				char curr = expr.charAt(i);
				if( curr == '*') {					
					BasicOperation multiply = new MultiplyIntegers();
					SingleUpdate upd = performOp(multiply, expr, i);
					
					expr = upd.newStr;
					
					i = i - (upd.index2 - upd.index1);
					if( i < 0 )
						i = 0;
				} else if ( curr == '/' ) {
					BasicOperation divide = new DivideIntegers();
					SingleUpdate upd = performOp(divide, expr, i);
					
					expr = upd.newStr;
					
					i = i - (upd.index2 - upd.index1);
					if( i < 0 )
						i = 0;
				}
			}
				
			//Addition and subtraction
			for( int i = 0; i < expr.length(); i++ ) {
				char curr = expr.charAt(i);
				if( curr == '+') {
					BasicOperation add = new AddIntegers();
					SingleUpdate upd = performOp(add, expr, i);
					
					expr = upd.newStr;
					
					i = i - (upd.index2 - upd.index1);
					if( i < 0 )
						i = 0;
				} else if ( curr == '-' ) {
					BasicOperation sub = new SubtractIntegers();
					SingleUpdate upd = performOp(sub, expr, i);
					
					expr = upd.newStr;
					
					i = i - (upd.index2 - upd.index1);
					if( i < 0 )
						i = 0;
				}
			}
			
			System.out.println( "Result of expression is " + expr);
			System.out.println();
		}
		
		sc.close();
	}
	
	public static SingleUpdate performOp( BasicOperation op, String expr, int i ) {
		int index1 = getOperand1( expr, i-1 );
		int index2 = getOperand2( expr, i+1 );
		
		int num1 = Integer.valueOf( expr.substring(index1, i ) );
		int num2 = Integer.valueOf( expr.substring(i+1, index2 ) );
		
		int res = op.resultOfOp( num1, num2);
		String temp = expr.substring(0, index1) + String.valueOf(res)
						+ expr.substring(index2);
		
		return new SingleUpdate( index1, index2, temp);
	}
	
	public static int getOperand1(String expr, int pos) {
		int tempPos = pos;
		while( tempPos >= 0 && 
				( expr.charAt(tempPos) -'0' ) >= 0 && ( expr.charAt(tempPos) -'0' ) < 10 ) {
			tempPos--;
		}
		
		return tempPos+1;
	}
	
	public static int getOperand2(String expr, int pos) {
		int tempPos = pos;
		while( tempPos < expr.length() && 
				( expr.charAt(tempPos) -'0' ) >= 0 && ( expr.charAt(tempPos) -'0' ) < 10) {
			tempPos++;
		}
		
		return tempPos;
	}
}
