package e2e;

import controller.PostController;
import model.core.Response;
import model.data.Post;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPost {

    @Test
    public void testGETPosts() {
        Response<Post[]> response = PostController.getPosts();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getData().length, 100);
    }

    @Test
    public void testGETPostById() {
        Response<Post> response = PostController.getPostById(1);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getData().getId(), 1);
        Assert.assertEquals(response.getData().getUserId(), 1);
    }
}
