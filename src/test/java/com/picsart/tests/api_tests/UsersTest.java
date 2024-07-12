package com.picsart.tests.api_tests;

import com.picsart.constants.Endpoints;
import com.picsart.dtos.Posts;
import com.picsart.dtos.User;
import com.picsart.helpers.CommonHelper;
import com.picsart.requests.Users;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.picsart.helpers.ApiHelper.getRandomElement;

public class UsersTest extends BaseApiTest {

    @Test
    public void verifyUserIsAbleToPost() {
        List<User> users = Users.get(apiUrl, Endpoints.USERS, User.class);
        Assert.assertNotNull(users);
        User randomUser = getRandomElement(users);
        List<Posts> posts = Users.get(apiUrl, Endpoints.POSTS, Posts.class);
        Assert.assertNotNull(posts);
        List<Posts> randomUsersPosts = posts.stream()
                .filter(t -> t.getUserId().equals(randomUser.getId()))
                .toList();

        randomUsersPosts.forEach(post -> {
            Assert.assertNotNull(post.getBody());
            Assert.assertNotNull(post.getTitle());
        });

        Posts newPost = new Posts();
        newPost.setUserId(randomUser.getId());
        newPost.setTitle(CommonHelper.getRandomAlphabeticString(10));
        newPost.setBody(CommonHelper.getRandomAlphabeticString(20));

        Posts newUserPost = Users.post(apiUrl, Endpoints.POSTS, newPost, Posts.class);
        Assert.assertNotNull(newUserPost);
        Assert.assertEquals(newUserPost.getBody(), newPost.getBody());
        Assert.assertEquals(newUserPost.getTitle(), newPost.getTitle());
    }
}