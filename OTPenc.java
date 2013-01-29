import java.io.*;
public class OTPenc {

    public static void main(String[] args) {


        File filemessage =new File("OTPmsg.txt");
        File ctext =new File("OTPctext");

        if(!filemessage.exists())
            System.out.println("Message File OTPmsg does not exits!..");
        if(!ctext.exists())
            try {
                ctext.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        else
        {
            try
            {

                OTPgen OTPgenobj = new OTPgen();

                FileReader msgfilerdr=new FileReader(filemessage);			
                FileReader key =new FileReader("OTPkey.txt");		

                BufferedReader buf =new BufferedReader(key);
                BufferedReader mes = new BufferedReader(new InputStreamReader(System.in)); 	

                String output,str_mes,str_buf;
                int decimal;

                System.out.println("Input the Message  : ");

                str_mes=mes.readLine();
                str_buf=buf.readLine();

                while(true)
                {

                    if(str_mes.length() != OTPgenobj.temp1)
                    {
                        System.out.println("Message length and key length doesn't Mathc");
                        str_mes=mes.readLine();
                    }
                    else
                        break;

                }




                StringBuilder sb =new StringBuilder();
                StringBuilder sb2 =new StringBuilder();
                StringBuilder sb3 =new StringBuilder();
                StringBuilder sb4 =new StringBuilder();


                for(int i=0;i<str_buf.length()-1;i+=2)
                {
                    output=str_buf.substring(i, i+2);
                    decimal=Integer.parseInt(output,16);
                    sb.append((char)decimal);
                    System.out.print("-"+sb.toString());
                }

                String tt=sb.toString();

                byte[] message=str_mes.getBytes();		
                byte[] key2=tt.getBytes();

               // System.out.println("key-----"+sb.toString());
               // System.out.println(tt.length());



                for(int i=0;i<message.length;i++)			
                    sb2.append(Integer.toHexString((char) (message[i]^key2[i])));


                //--------------------------------------------------------------------


                String str3=sb2.toString();
                System.out.println("HEx format --$"+str3);

                FileWriter keyfilewrtr=new FileWriter("OTPctext.txt");
                keyfilewrtr.write(str3);
                keyfilewrtr.write("\n");
                keyfilewrtr.flush();

//                System.in.read();

                for(int i=0;i<str3.length()-1;i+=2)
                {
                    output=str3.substring(i, i+2);
                    decimal=Integer.parseInt(output,16);
                    sb3.append((char)decimal);
                    System.out.print("- "+sb3.toString());
                }

                System.out.println("hex to xor--$"+sb3.toString());

                str3=sb3.toString();

                System.out.println("hex to xor--sb3---$"+sb3);
                System.out.println("END");
                System.in.read();




                System.out.println("Encrypt--------    $"+str3);
                System.out.println("END");


                byte[] xor=str3.getBytes();



                for(int i=0;i<message.length;i++)
                {
                    sb4.append((char) ( xor[i] ^ key2[i] ));
                }

                System.out.println("Decrypt--------   "+sb4.toString());




                buf.close();
                msgfilerdr.close();
                keyfilewrtr.close();
                key.close();


            }catch(Exception e){
            }
        }
    }

}


