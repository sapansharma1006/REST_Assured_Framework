package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {


   public static RequestSpecification requestSpecification;

    public RequestSpecification getRequestSpecification() throws IOException {

        if (requestSpecification==null) {

            PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));


            requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecification;
        } return requestSpecification;

    }


    public static String getGlobalValue(String key) throws IOException {

        Properties properties = new Properties();

        FileInputStream fileInputStream = new FileInputStream("/Users/sapan.sharma/Desktop/Test/API_Framework/src/test/java/Resources/global.properties");

        properties.load(fileInputStream);

      return  properties.getProperty(key);

    }

    public String getJsonPathValue(Response response, String key){

        String res = response.asString();
        JsonPath jsonPath = new JsonPath(res);
        return jsonPath.get(key).toString();


    }

}
