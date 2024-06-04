package resources;

//Special Class in Java which has collection of constants or methods
public enum APIResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceApi("/maps/api/place/get/json"),
    DeletePlaceApi("/maps/api/place/delete/json");
    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
