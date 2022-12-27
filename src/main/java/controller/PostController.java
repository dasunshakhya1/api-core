package controller;

import configs.ApplicationConfig;
import core.util.ObjectMapperUtil;
import enums.HeaderValues;
import lombok.extern.slf4j.Slf4j;
import model.HeaderData;
import model.Response;
import model.ResponseData;
import models.Post;

import util.RequestHandlerUtil;

@Slf4j
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


    public static Response<Post> getPostById(int id) {
        String url = BASE_URL + "/posts/" + id;
        HeaderData headerData = new HeaderData();
        headerData.addHeaderValues(HeaderValues.CONTENT_TYPE_JSON.getKey(), HeaderValues.CONTENT_TYPE_JSON.getValue());

        ResponseData responseData = RequestHandlerUtil.httpGET(url, headerData);
        Post posts = ObjectMapperUtil.mapStringToObject(Post.class, responseData.getData());
        return Response.<Post>builder().data(posts).statusCode(responseData.getStatusCode()).build();
    }
}
