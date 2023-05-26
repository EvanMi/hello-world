package hello.world;

import org.junit.jupiter.api.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignTest {

    @Test
    public void testAb() throws Exception {
        String jsonParam = "{\"jsCode\":\"0b1447Ga1cBDmF0Z4lIa1fYFJV2447G3\"}";//body参数，接口只支持RESTFUL风格，所以入参一定是一个json。
        //对应于Headr中的timestamp
        Long timeStamp = System.currentTimeMillis();
        System.out.println(timeStamp);
        //秘钥，从白泽系统获取
        String secret = "674614af500e459ca83b481d093e94b2";
        //将secret作为秘钥
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        //创建HmacSHA256的算法示例
        Mac sha256HMAC = Mac.getInstance(secretKey.getAlgorithm());
        //初始化
        sha256HMAC.init(secretKey);
        byte[] in = sha256HMAC.doFinal((jsonParam + timeStamp).getBytes());

        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < in.length; n++) {
            stmp = (Integer.toHexString(in[n] & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        //签名结果，请发在header中的
        String sign = hs.toString().toUpperCase();
        System.out.println(sign);
    }


    private void ti() {

    }
}
