package Resources;

public enum APIResource {


    AddPlaceAPI("/maps/api/place/add/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    GetPlaceAPI("/maps/api/place/get/json");

    private  String endpoint;

//    This constructor is been called from step definition to get the endpoint
//    this method is used to set the value of endpoint coming from step definition to current class variable
    APIResource(String resource){

        this.endpoint=resource;

    }

// this method is simply used to return  the value of endpoint
    public String getResource(){

        return endpoint;
    }



}
