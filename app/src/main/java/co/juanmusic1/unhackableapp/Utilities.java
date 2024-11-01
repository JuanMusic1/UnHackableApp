package co.juanmusic1.unhackableapp;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Utilities {

    private Cipher cipher;
    private String pin_to_md5 = MD5(Global.getInstance().getPin());
    SecretKeySpec keySpec = new SecretKeySpec(this.pin_to_md5.getBytes(), "AES");

    public static String MD5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(b & 255);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public String xor(String pin, String uniqueid) {
        String uniqueid2 = uniqueid.substring(0, pin.length());
        BigInteger i1 = new BigInteger(pin, 16);
        BigInteger i2 = new BigInteger(uniqueid2, 16);
        BigInteger res = i1.xor(i2);
        String coded_pin = res.toString(16);
        return coded_pin;
    }

    public boolean check_valid_pin(String pin) {
        return pin.length() > 3 && pin.length() < 7;
    }

    public String encrypt(String message) {
        try {
            this.cipher = Cipher.getInstance("AES");
            this.cipher.init(1, this.keySpec);
            byte[] cipherText = this.cipher.doFinal(message.getBytes());
            return Base64.encodeToString(cipherText, 0);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (BadPaddingException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public String decrypt(String cipherText) {
        byte[] bkdec = Base64.decode(cipherText.getBytes(), 0);
        try {
            this.cipher = Cipher.getInstance("AES");
            this.cipher.init(2, this.keySpec);
            return new String(this.cipher.doFinal(bkdec), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        } catch (BadPaddingException e4) {
            e4.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
            return null;
        }
    }
}
