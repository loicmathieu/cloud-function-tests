package fr.loicmathieu.gcp.function;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class FruitRestFunction implements HttpFunction {
    private Gson gson = new Gson();
    private Map<String, Fruit> fruits = new HashMap<>();

    @Override
    public void service(HttpRequest request, HttpResponse response)
            throws Exception {
        String data = null;
        if(request.getMethod().equalsIgnoreCase("POST")){
            //create a fruit
            Fruit fruit = gson.fromJson(request.getReader(), Fruit.class);
            fruits.put(fruit.name, fruit);
            data = "{\"status\" : \"created\"}";
        }
        else if (request.getMethod().equalsIgnoreCase("GET")){
            if(request.getQueryParameters().containsKey("name")){
                //get a fruit
                Fruit fruit = fruits.get(request.getQueryParameters().get("name").get(0));
                data = gson.toJson(fruit);
            }
            else {
                //get all fruits
                data = gson.toJson(fruits.values());
            }
        }

        //write to the response
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(data);
    }
}
