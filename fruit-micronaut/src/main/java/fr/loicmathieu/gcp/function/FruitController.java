package fr.loicmathieu.gcp.function;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Status;

import javax.inject.Inject;
import java.util.Collection;

@Controller("/")
public class FruitController {
    @Inject
    private FruitService fruitService;

    @Get(produces = MediaType.APPLICATION_JSON)
    public Collection<Fruit> list() {
        return fruitService.list();
    }

    @Get("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Fruit get(String name) {
        return fruitService.get(name);
    }

    @Post(processes = MediaType.APPLICATION_JSON)
    @Status(HttpStatus.CREATED)
    public ResponseStatus save(@Body Fruit fruit) {
        fruitService.add(fruit);
        return new ResponseStatus("created");
    }

   public static class ResponseStatus {
        public String status;

       public ResponseStatus(String status) {
           this.status = status;
       }
   }
}
