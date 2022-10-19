package root;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class RSA {
	private KeyPair keyPair;
	private PublicKey publicKey;
	private PrivateKey privateKey;

	public RSA() {
		// TODO Auto-generated constructor stub
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public void getKey() {
		KeyPairGenerator keyGenerator = null;
		try {
			keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(2048);
			keyPair = keyGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public byte[] encrypt(String text) throws Exception {
		if (publicKey == null) {
			getKey();
		}

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] plainText = text.getBytes(StandardCharsets.UTF_8);
		return cipher.doFinal(plainText);
	}

	public String decrypt(byte[] text, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plainText = cipher.doFinal(text);
		return new String(plainText, StandardCharsets.UTF_8);
	}

	public static void main(String[] args) throws Exception {
		RSA rsa = new RSA();
		Key key = rsa.getPrivateKey();
		System.out.println(rsa.decrypt(rsa.encrypt("Xin chào"), key));
	}
}
