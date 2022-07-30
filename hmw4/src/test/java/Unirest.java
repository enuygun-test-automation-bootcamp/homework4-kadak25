import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
public class UniRest {
    Store store = new Store();
    ObjectMapper objectMapper;
    public UniRest(){
        objectMapper = new ObjectMapper();
    }
    @Test
    public void postStore() throws JsonProcessingException, UnirestException {
        store.setId(1);
        store.setPetId(2);
        store.setQuantity(5);
        store.setShipDate("2022-07-30T18:36:23.057+0000");
        store.setStatus("placed");
        store.setComplete(true);
        String jsonString =objectMapper.writeValueAsString(store);
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.post("https://petstore.swagger.io/v2/store/order")
                .header("Content-Type", "application/json")
                .body(jsonString)
                .asJson();
    }
    @Test
    public void getStore() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get("https://petstore.swagger.io/v2/store/order/1")
                .header("accept", "application/json")
                .asJson();
    }
    @Test
    public void deleteStore() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.delete("https://petstore.swagger.io/v2/store/order/1")
                .header("accept", "application/json")
                .asJson();
    }
    @Test
    public void getInventory() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get("https://petstore.swagger.io/v2/store/inventory").asJson();
    }
}