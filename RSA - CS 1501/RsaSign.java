import java.io.IOException;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.math.BigInteger;


public class RsaSign {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		char flag = 0;
		String file = null;
		
		try{
			flag = args[0].charAt(0);
			file = args[1];
		}catch(ArrayIndexOutOfBoundsException exception){
			System.err.println("Please run the file as 'java RsaSign <s/v> <filename.txt>'");
			System.exit(0);
		}
		
		
		//signed
		if (flag == 's') { 
			
			String fileName = args[1] + ".sig"; 	
			
			FileInputStream fileInput;
			
			try {
				fileInput = new FileInputStream("privkey.rsa");
			} catch (FileNotFoundException e) {
				System.err.println("ERROR: \"privkey.rsa\" was not found. Please generate a keypair by running MyKeyGen.");
				return;
			}
			
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			
			BigInteger d = (BigInteger)objectInput.readObject();
			BigInteger n = (BigInteger)objectInput.readObject();
			
			objectInput.close();
			
			try {
				
				Path path = Paths.get(file);
				byte[] data = Files.readAllBytes(path);

				MessageDigest md = MessageDigest.getInstance("SHA-256");

				md.update(data);
				byte[] digest = md.digest();

				BigInteger result = new BigInteger(1, digest);
				BigInteger decrypt = result.modPow(d, n); 

				FileReader reader = new FileReader(file);
				BufferedReader bReader = new BufferedReader(reader);
				FileOutputStream fileOutput = new FileOutputStream(fileName);
				ObjectOutputStream output = new ObjectOutputStream(fileOutput);
				String text;
				String content = "";
				
				while ((text = bReader.readLine()) != null) {
					content = text + '\n';
				}
				
				output.writeObject(content);
				output.writeObject(decrypt);
				
				bReader.close();
				output.close();
				
			} catch(Exception exception_s) {
				System.out.println(exception_s.toString());
			}
		}
		
		//verified
		else if (flag == 'v') {
			
			if (!file.contains(".sig")) {
				System.err.println("ERROR: File given has not been signed yet. Please run with file '<filename>.rsa.sig'");
				return;
			}
			FileInputStream fileInput;
			
			try {
				fileInput = new FileInputStream("pubkey.rsa");
			} catch (FileNotFoundException e) {
				System.err.println("ERROR: \"pubkey.rsa\" was not found. Please generate a keypair by running MyKeyGen.");
				return;
			}
			
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			BigInteger e = (BigInteger)objectInput.readObject();
			BigInteger n = (BigInteger)objectInput.readObject();
			objectInput.close();
			
			fileInput = new FileInputStream(file);
			objectInput = new ObjectInputStream(fileInput);
			String content = (String)objectInput.readObject();
			BigInteger hash = (BigInteger)objectInput.readObject();
			String originalFile = file.replace(".sig", "");
			
			try {
				
				Path path = Paths.get(originalFile);
				byte[] data = Files.readAllBytes(path);

				MessageDigest md = MessageDigest.getInstance("SHA-256");

				md.update(data);
				byte[] digest = md.digest();

				BigInteger result = new BigInteger(1, digest);
				BigInteger encrypt = hash.modPow(e, n);
				
				if (encrypt.compareTo(result) == 0) 
					System.out.println("Signature is valid.");
				else 
					System.out.println("Invalid signature.");
				
			} catch(Exception exception_v) {
				System.out.println(exception_v.toString());
			}
			
		}
		
		else {
			System.err.println("ERROR: Invalid command line argument.");
			return;
		}
		
	}
}