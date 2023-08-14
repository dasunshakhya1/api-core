package core.http;


import lombok.extern.slf4j.Slf4j;
import core.model.HeaderData;
import core.model.ResponseData;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

@Slf4j
public final class RequestHandlerUtil {

    private static ResponseData sendRequest(ClassicHttpRequest request) {
        ResponseData responseData = ResponseData.builder().build();
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()) {
            CustomResponseHandler<ResponseData> responseHandler = new CustomResponseHandler<>();
            responseData = httpclient.execute(request, responseHandler);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return responseData;
    }


    public static ResponseData httpGET(String url, HeaderData headerData) {
        HttpGet httpGet = new HttpGet(url);
        setHeaders(httpGet, headerData);
        return sendRequest(httpGet);

    }

    public static ResponseData httpPOST(String url, String payload, HeaderData headerData) {
        HttpPost request = new HttpPost(url);
        setHeaders(request, headerData);
        request.setEntity(new StringEntity(payload));
        return sendRequest(request);
    }

    public static ResponseData httpPUT(String url, String payload, HeaderData headerData) {
        HttpPut request = new HttpPut(url);
        setHeaders(request, headerData);
        request.setEntity(new StringEntity(payload));
        return sendRequest(request);

    }

    public static ResponseData httpDELETE(String url, HeaderData headerData) {
        HttpDelete request = new HttpDelete(url);
        setHeaders(request, headerData);
        return sendRequest(request);

    }


    private static void setHeaders(ClassicHttpRequest request, HeaderData headerData) {
        if (headerData != null && headerData.getRequestHeaders().size() > 0) {
            headerData.getRequestHeaders().keySet().forEach(k -> request.setHeader(k, headerData.getRequestHeaders().get(k)));
        }
    }

}
