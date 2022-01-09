package example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ScfPojo {
    public String handle(RequestClass request) {
        String greetingString = String.format("Hello %s %s.You are from %s", request.person.firstName,
                request.person.lastName, request.city.name);
        System.out.println(greetingString);

        // SCF 需要手动序列化Pojo
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;
        try {
            ResponseClass response = new ResponseClass(greetingString);
            jsonString = mapper.writeValueAsString(response);
            System.out.println(jsonString);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
