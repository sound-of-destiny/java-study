package gson;

import com.google.gson.Gson;

public class GsonTest {
    public static void main(String[] args) {
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println(json);
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(obj2.toString());
    }
}
