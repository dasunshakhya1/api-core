package util;

import lombok.extern.slf4j.Slf4j;
import model.core.HeaderData;
import model.core.ResponseData;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

@Slf4j
public class RequestHandlerUtil {

    private static ResponseData sendRequest(ClassicHttpRequest request) {
        int statusCode = 0;
        String responseBody = null;
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build(); CloseableHttpResponse response = httpclient.execute(request)) {
            HttpEntity entity = response.getEntity();
            statusCode = response.getCode();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity);
            }
        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
        }

        return ResponseData.builder().data(responseBody).statusCode(statusCode).build();
    }


    public static ResponseData httpGET(String url, HeaderData headerData) {
        log.info(url);
        HttpGet httpGet = new HttpGet(url);
        setHeaders(httpGet, headerData);
        return sendRequest(httpGet);

    }


    private static void setHeaders(ClassicHttpRequest request, HeaderData headerData) {
        if (headerData.getRequestHeaders().size() > 0) {
            headerData.getRequestHeaders().keySet().forEach(k -> request.setHeader(k, headerData.getRequestHeaders().get(k)));
        }
    }

}
