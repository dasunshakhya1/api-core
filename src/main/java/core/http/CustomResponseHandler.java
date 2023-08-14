package core.http;

import core.model.ResponseData;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class CustomResponseHandler<R> implements HttpClientResponseHandler<ResponseData> {
    @Override
    public ResponseData handleResponse(ClassicHttpResponse classicHttpResponse) throws HttpException, IOException {
        int status = classicHttpResponse.getCode();
        String responseBody = null;
        if (status >= 200 && status < 300) {
            HttpEntity entity = classicHttpResponse.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity);
            }
        }
        return ResponseData.builder().data(responseBody).statusCode(status).build();
    }
}
