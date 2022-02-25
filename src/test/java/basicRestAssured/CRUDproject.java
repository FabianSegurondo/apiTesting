package basicRestAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDproject {

    @Test
    public void crudProject() {
        // Create
        JSONObject body = new JSONObject();
        body.put("Content", "FabianSegurondoJSON");
        body.put("Icon", 5);

        Response response = given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com", "12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("FabianSegurondoJSON"))
                .body("Icon", equalTo(5))
                .log().all();

        int idProject = response.then().extract().path("Id");


        // Read
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("FabianSegurondoJSON"))
                .body("Icon",equalTo(5))
                .log().all();
        // Update
        body.put("Content","FabianSegurondoUpdate");
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("FabianSegurondoUpdate"))
                .body("Icon",equalTo(5))
                .log().all();
        // Delete
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .log().all()
                .when()
                .delete("https://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("FabianSegurondoUpdate"))
                .body("Icon",equalTo(5))
                .body("Deleted",equalTo(true))
                .log().all();

    }
}
