package security;



public class Encryption {
	
	
	public static String encrypt(String data, int key) {
		StringBuilder cryptData = new StringBuilder();  //Store encrypted data temporarily
				
		for(int i = 0; i < data.length(); i++) {
			char character = data.charAt(i);
			cryptData.append((char) (character ^ key));
		}
		cryptData.reverse();
		
		return cryptData.toString();
	}
	
	
	public static String decrypt(String data, int key) {
		
			return encrypt(data, key);
	}
}
