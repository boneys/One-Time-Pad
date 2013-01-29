import java.io.*;
import java.util.Scanner;
import java.util.Random;
public class OTPgen {
	
		int randnum,temp1;
		double i,temp2;
		String str="abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+-=|?><:;";
		OTPgen()
		{
			try
			{
				File file1 =new File("OTPkey.txt");
				FileWriter fw=new FileWriter(file1);
				Scanner scan =new Scanner(System.in);
				Random rand=new Random();
				StringBuilder strbuild=new StringBuilder();
				
		    
				if(!file1.exists())
					file1.createNewFile();
				else
				{
					System.out.println("Please Enter any number less than 500 (Whuich is a multiple of 8)");
					temp1=scan.nextInt();
												
					for(int var=0;var<temp1;var++)
						strbuild.append(Integer.toHexString(str.charAt(rand.nextInt(str.length()))));
													
					fw.write(strbuild.toString());
					fw.write("\n");		
					fw.flush();
				
					fw.close();
										
				}
			}
			catch(IOException e)
			{			
			}
		
		}	
}
