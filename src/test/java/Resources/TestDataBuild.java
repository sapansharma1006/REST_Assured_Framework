package Resources;

import Pojo.AddPlace;
import Pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {


    public AddPlace getAddPlacePayload(String name,String language, String address){

        AddPlace place = new AddPlace();
        List<String> myList = new ArrayList<String>();
        Location l = new Location();

        myList.add("shoe park");
        myList.add("shop");
        l.setLat(-38.383494);
        l.setLng(33.427362);
        place.setAccuracy(50);

        place.setAddress(address);
        place.setLanguage(language);
        place.setName(name);

        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("https://www.rahulshettyacademy.com/");
        place.setTypes(myList);
        place.setLocation(l);
        return place;
    }

    public String getDeletePayload(String place_id){

        String payload = "{\r\n\"place_id\":\""+place_id+"\"\r\n}";

        return payload ;
    }
}
