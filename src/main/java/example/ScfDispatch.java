package example;

import java.util.Properties;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.scf.v20180416.ScfClient;
import com.tencentcloudapi.scf.v20180416.models.*;

public class ScfDispatch {
    public String handle(RequestClass request) {

        Properties properties = System.getProperties();
        properties.list(System.out);

        // 通过环境变量获取运行角色，需要设置SecretId、SecretKey以及Token
        String secretId = System.getProperty("TENCENTCLOUD_SECRETID");
        String secretKey = System.getProperty("TENCENTCLOUD_SECRETKEY");
        String token = System.getProperty("TENCENTCLOUD_SESSIONTOKEN");


        try {
            Credential cred = new Credential(secretId, secretKey, token);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("scf.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            ScfClient client = new ScfClient(cred, "ap-beijing", clientProfile);

            InvokeRequest req = new InvokeRequest();
            req.setFunctionName("java-pojo-example");
            req.setInvocationType("RequestResponse");
            req.setNamespace("chris_demo");
            String requestString = "{\"person\":{\"firstName\":\"firstName\",\"lastName\":\"lastName\"},\"city\":{\"name\":\"Beijing\"}}";
            req.setClientContext(requestString);

            InvokeResponse resp = client.Invoke(req);
            return resp.getResult().getRetMsg();

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return e.toString();
        }
    }
}
