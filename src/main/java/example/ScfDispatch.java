package example;

import java.util.Map;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.scf.v20180416.ScfClient;
import com.tencentcloudapi.scf.v20180416.models.*;

public class ScfDispatch {
    public String handle(RequestClass request) {

        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
        }

        // Java运行时暂时不支持通过环境变量获取运行角色，需要设置SecretId和SecretKey
        String secretId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";


        try {
            Credential cred = new Credential(secretId, secretKey);

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
