package controller;

import configs.ApplicationConfig;
import enums.HeaderValues;
import model.core.HeaderData;
import model.core.Response;
import model.core.ResponseData;
import model.data.Post;
import util.ObjectMapperUtil;
import util.RequestHandlerUtil;

public class PostController {

    private static final String BASE_URL = ApplicationConfig.BASE_URL;

    public static Response<Post[]> getPosts() {
        String url = BASE_URL + "/posts";

        HeaderData headerData = new HeaderData();
        headerData.addHeaderValues(HeaderValues.CONTENT_TYPE_JSON.getKey(), HeaderValues.CONTENT_TYPE_JSON.getValue());

        ResponseData responseData = RequestHandlerUtil.httpGET(url, headerData);
        Post[] posts = ObjectMapperUtil.mapStringArrayToObjectArray(Post[].class, responseData.getData());
        return Response.<Post[]>builder().data(posts).statusCode(responseData.getStatusCode()).build();

    }
}
