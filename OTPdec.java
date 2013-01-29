import java.util.*;
import java.io.*;
public class OTPdec {
	
	public static void main(String[] args) {
		try
		{
		File decryptfile=new File("OTPdecrypt.txt");
		if(!decryptfile.exists())
			decryptfile.createNewFile();
			
		FileReader cipher_fr=new FileReader("OTPctext.txt");		
		FileReader key_fr =new FileReader("OTPkey.txt");	
		FileWriter decrypt_fw=new FileWriter(decryptfile);
			
		BufferedReader buf_mes = new BufferedReader(cipher_fr);
		BufferedReader buf_key =new BufferedReader(key_fr);
		
		StringBuilder sb=new StringBuilder();
		StringBuilder sb1=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
				
		int decimal;
		String output,hex1,hex2;
		String str1=buf_mes.readLine();
		String str2=buf_key.readLine();
		
		System.out.println("cipher  "+str1);
		System.out.println("key     "+str2);

		for(int i=0;i<str1.length()-1;i+=2)
		{
			output=str1.substring(i, i+2);
			decimal=Integer.parseInt(output,16);
			sb1.append((char)decimal);
		}
		
		hex1=sb1.toString();
		System.out.println("ascii --cyt   >> "+hex1);
		output="";
		decimal=0;
		
		for(int i=0;i<str2.length()-1;i+=2)
		{
			output=str2.substring(i, i+2);
			decimal=Integer.parseInt(output,16);
			sb2.append((char)decimal);
		}
		
		hex2=sb2.toString();
		System.out.println("ascii --key  >> "+hex2);
		
		byte[] cipher=hex1.getBytes();
		byte[] key=hex2.getBytes();
		
		for(int i=0;i<cipher.length;i++)
			sb.append((char) (cipher[i] ^ key[i]));
		
		System.out.println("Message Is ---  :"+sb.toString());
		decrypt_fw.write(sb.toString());
		
		cipher_fr.close();
		key_fr.close();
		buf_mes.close();
		buf_key.close();
		decrypt_fw.close();
		

	}catch(Exception e){}
	}

}
