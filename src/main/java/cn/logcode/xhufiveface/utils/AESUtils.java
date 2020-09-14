package cn.logcode.xhufiveface.utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;

/**
 * Created by lsh
 * AES-128-CBC 加密方式
 * 注：
 * AES-128-CBC可以自己定义“密钥”和“偏移量“。
 * AES-128是jdk自动生成的“密钥”。
 */
public class AESUtils {
 
 
    static {
        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }
 
    /**
     * AES解密
     *
     * @param data           //密文，被加密的数据
     * @param key            //秘钥
     * @param iv             //偏移量
     * @param encodingFormat //解密后的结果需要进行的编码
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key, String iv, String encodingFormat) {
        //加密秘钥
        byte[] keyByte = Base64Utils.decodeFromString(key);
        //偏移量
        byte[] ivByte = Base64Utils.decodeFromString(iv);
        return decrypt(data,keyByte,ivByte,encodingFormat);
    }



    public static String decrypt(String data, byte[] keyByte, byte[] ivByte, String encodingFormat)  {

        //被加密的数据
        byte[] dataByte = Base64Utils.decodeFromString(data);

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));

            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 算法名称
    public static final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    public static final String algorithmStr = "AES/CBC/PKCS7Padding";
    public static final byte[] key = {0x48,0x29,0x56,0x5,0x9,0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35, 0x30, 0x36,0x30, 0x33};

    public static final  byte[]  iv = { 0x30, 0x31, 0x30, 0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35, 0x30, 0x36, 0x30, 0x37, 0x30, 0x38 };

    /**
     * AES加密
     * @param data
     * @param keyByte
     * @param ivByte
     * @return
     */
    public static String encrypt(String data, byte[] keyByte, byte[] ivByte) {

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));

            cipher.init(Cipher.ENCRYPT_MODE, spec, parameters);// 初始化

            byte[] resultByte = cipher.doFinal( data.getBytes());
            if (null != resultByte && resultByte.length > 0) {
                return Base64Utils.encodeToString(resultByte);
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;

    }



    public static void main(String[] args){

        String data = "{啊哈哈哈哈哈}";

        String content =  encrypt(data,key,iv);

        System.out.println(content);

        content = decrypt(content,key,iv,"utf-8");

        System.out.println(content);

    }

}
