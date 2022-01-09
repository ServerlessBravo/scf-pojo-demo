package example;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.PojoRequestHandler;

public class FcPoJo implements PojoRequestHandler<RequestClass, ResponseClass> {

    @Override
    public ResponseClass handleRequest(RequestClass request, Context context) {
        String greetingString = String.format("Hello %s %s.You are from %s", request.person.firstName, request.person.lastName, request.city.name);
        return new ResponseClass(greetingString);
    }
}  