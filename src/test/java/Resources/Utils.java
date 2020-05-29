package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Utils {

    List excellist = new ArrayList();


    public static RequestSpecification requestSpecification;

    public RequestSpecification getRequestSpecification() throws IOException {

        if (requestSpecification == null) {

            PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));


            requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecification;
        }
        return requestSpecification;

    }


    public static String getGlobalValue(String key) throws IOException {

        Properties properties = new Properties();

        FileInputStream fileInputStream = new FileInputStream("/Users/sapan.sharma/Desktop/Test/API_Framework/src/test/java/Resources/global.properties");

        properties.load(fileInputStream);

        return properties.getProperty(key);

    }

    public String getJsonPathValue(Response response, String key) {

        String res = response.asString();
        JsonPath jsonPath = new JsonPath(res);
        
        return jsonPath.get(key).toString();


    }

    public List getDataFromSheet() throws IOException, InvalidFormatException {

        File file = new File("/Users/sapan.sharma/Desktop/Test_Sheet.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        int sheets = workbook.getNumberOfSheets();
        System.out.println("Sheets are : " +sheets);


        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) {

                XSSFSheet xssfSheet = workbook.getSheetAt(i);

                Iterator<Row> rowIterator = xssfSheet.iterator();

                System.out.println("Last Row : " + xssfSheet.getLastRowNum());


                for (int j = 0; j < xssfSheet.getLastRowNum(); j++) {

                    while (rowIterator.hasNext()) {

                        Row firstrow = rowIterator.next();

                        Iterator<Cell> cellIterator = firstrow.cellIterator();

                        System.out.println("Last column : " + firstrow.getLastCellNum());

                        for (int s = 0; s < firstrow.getLastCellNum(); s++) {
                            while (cellIterator.hasNext()) {

                                Cell value = cellIterator.next();

                                excellist.add(value.getStringCellValue());
//                         Cell value2=cellIterator.next();
//                         excellist.add(value2.getStringCellValue());
//                         Cell value3=cellIterator.next();


                            }

                        }
                    }

                    workbook.close();

                }


            }

        }
        return excellist;
    }

}
