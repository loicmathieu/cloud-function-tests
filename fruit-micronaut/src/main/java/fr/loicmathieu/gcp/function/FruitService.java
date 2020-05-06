package fr.loicmathieu.gcp.function;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class FruitService {
    private Map<String, Fruit> fruits = new HashMap<>();

    public Collection<Fruit> list(){
        return fruits.values();
    }

    public void add(Fruit fruit){
        fruits.put(fruit.name, fruit);
    }

    public Fruit get(String name){
        return fruits.get(name);
    }
}
