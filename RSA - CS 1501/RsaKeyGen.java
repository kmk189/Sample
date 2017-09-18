import java.math.BigInteger;
import java.util.Random;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class RsaKeyGen {
	public static void main(String[] args) throws IOException {
		
		Random rnd = new Random();
		
		BigInteger p = new BigInteger(512, 256, rnd); 
		BigInteger q = new BigInteger(512, 256, rnd); 
		
		BigInteger n = p.multiply(q); //n=p*q
		
		BigInteger p_minusOne = p.subtract(BigInteger.ONE); // p - 1 
		BigInteger q_minusOne = q.subtract(BigInteger.ONE); // q - 1 
		
		BigInteger phi = p_minusOne.multiply(q_minusOne); //phi(n) = (p-1) * (q-1)
		
		//1 < e < phi(n) and GCD(e, phi(n)) = 1
		BigInteger e = BigInteger.ZERO;
		
		BigInteger temp = BigInteger.ONE.add(BigInteger.ONE);
		
		while(!temp.gcd(phi).equals(BigInteger.ONE)) {
			temp = temp.add(BigInteger.ONE);
		}
		
		e = temp;
		
		BigInteger d = e.modInverse(phi); // d=e^-1(mod(phi(n)))
		
		//Saving the files
		FileOutputStream pubkey = new FileOutputStream("pubkey.rsa"); 
		FileOutputStream privkey = new FileOutputStream("privkey.rsa"); 
		
		ObjectOutputStream pub = new ObjectOutputStream(pubkey); 
		ObjectOutputStream priv = new ObjectOutputStream(privkey);
		
		pub.writeObject(e);	
		pub.writeObject(n);
		
		priv.writeObject(d);
		priv.writeObject(n);
		
		pub.close();
		priv.close();	
		
	}
}