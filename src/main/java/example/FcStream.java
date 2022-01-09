package example;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.StreamRequestHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FcStream implements StreamRequestHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        String greetingString = "Here is greeting string";
        output.write(new String(greetingString).getBytes());
    }
}