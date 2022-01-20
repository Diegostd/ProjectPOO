package chatSystem.model;

import java.net.InetAddress;
public class Adress
{
   public static void main(String args[]) throws Exception
   {
      InetAddress ip = InetAddress.getLocalHost();
 
      System.out.print("Mon adresse IP est: ");
      System.out.println(ip.getHostAddress());
  }
}