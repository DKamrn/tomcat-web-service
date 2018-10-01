package com.javacodegeeks.ultimate.aws.eb;

import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;
public class TutorialIntegrationTest {

    @Test
    public void testListAllCourses() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080")
			.path("/tomcat-web-service/tomcat-web-service/tutorial/list-all-courses");
        Response response = target.request().get();
        assertThat(response.getStatus(), is(200));
        List<Tutorial> tutorials = response.readEntity(new GenericType<List<Tutorial>>(){});
        assertThat(tutorials.size(), is(3));
        assertThat(tutorials, hasItem(new Tutorial("Linus Meyer", "Linux")));
        assertThat(tutorials, hasItem(new Tutorial("Bill Famous", "Microsoft")));
        assertThat(tutorials, hasItem(new Tutorial("Josh Hotspot", "Java")));
    }
}