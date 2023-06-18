package com.example.DockerContainer2;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

@RestController
public class Container {

    @PostMapping("calculate")
    public ResponseEntity calculate(@RequestBody String data){
        //Read File
        try {
            JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            JsonObject response = new JsonObject();
            String fileName = jsonObject.get("file").toString().replaceAll("\"", "");
            String productName = jsonObject.get("product").toString().replaceAll("\"", "");

            int total = 0;
            File myObj = new File("/mnt/" + fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String text = myReader.nextLine();
                ArrayList<String> pList = new ArrayList<>(Arrays.asList(text.split(",")));
                if(pList.size() <= 2 && pList.contains(productName)){
                    total += Integer.parseInt(pList.get(1));
                }
                else if (pList.size() > 2){
                    response.addProperty("file", fileName);
                    response.addProperty("error", "Input file not in CSV format.");
                    return ResponseEntity.badRequest().body(response.toString());
                }
            }

            JsonObject myObject = new JsonObject();
            myObject.addProperty("file", fileName);
            myObject.addProperty("sum", total);

            myReader.close();

            return ResponseEntity.accepted().body(myObject.toString());

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}
