package huidao;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhuchunliu on 2017/8/31.
 */
public class HuidaoTest {


    public static void main(String[] args) {
        String app_ID = "f2993b86-bcf9-4e92-baaa-14e057903523";
        String app_KEY = "b8dcb4eb-c8f4-4c21-9bcd-1d70d1ba4614";
        String app_SIGNATURE = "2360ab7f-ec5a-41d7-9660-ca3dc332d383";

        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time).substring(0, 10);
        System.err.println(time + "  " + timestamp);
        StringBuffer buffer = new StringBuffer();
        buffer.append(app_ID).append(app_KEY).append("1504257925").append(app_SIGNATURE);

        System.err.println(buffer.toString());
        System.err.println(getSha1(buffer.toString()));
        System.err.println(DigestUtils.sha1Hex(buffer.toString()));

    }

    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
