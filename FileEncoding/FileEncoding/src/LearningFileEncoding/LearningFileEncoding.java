
package LearningFileEncoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LearningFileEncoding {
	
	static int[][] table = new int[65536][2];
	static double[] probability = new double[65536];
	final static int uniLen = 65536;
	final static String CP949 = "x-IBM949";
	final static String GB2312 = "GB2312";
	final static String SHIFTJIS = "Shift_JIS";
	static int count = 0;
	
	public static void main(String[] args) throws java.io.IOException {		
		
		//�����̸��� ���ڵ��� �Է����ش�.
		//IdentifyCodeFrequency("./cp949.txt",CP949);
		IdentifyCodeFrequency("./gb2312.txt",GB2312);
		//IdentifyCodeFrequency("./shiftJIS.txt",SHIFTJIS);
		
		System.out.println("    �� ��  | �����ڵ�    | ����   |  Ȯ��");
		
		for(int i = 0; i < uniLen; i++)
			if(table[i][1] > 0)
				System.out.printf("%5d | %#06X | %3d | %.4f\n", i, table[i][0], table[i][1], probability[i]);
		
		
		/*
		double k = 0;
		for(int i = 0; i < uniLen; i++)
			k += probability[i];
		
		System.out.println(k);
		*/
	}
	
	static void IdentifyCodeFrequency(String fname, String encoding) throws java.io.IOException {
		
		String inputStr = null;
		char[] inputChar;
		
		for(int i = 0; i < uniLen; i++)
			table[i][0] = i;
		
		File file = new File(fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		
		//���Ͽ��� ���ڸ� �о� ���ڿ� �����ϴ� �����ڵ��� ���� ������Ų��.
		while((inputStr = br.readLine()) != null ) {
			inputChar = inputStr.toCharArray();
			
			for(int i = 0; i < inputChar.length; i++) {
				table[inputChar[i]][1] += 1;
				count++;
			}
		}
		
		//�����ڵ��� �� Ȯ���� ���Ѵ�.
		for(int i = 0; i < uniLen; i++)
			probability[i] = (double)table[i][1] / count;
	}
}
