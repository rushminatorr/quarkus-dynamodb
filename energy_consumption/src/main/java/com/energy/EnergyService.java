package com.energy;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class EnergyService {

    @Inject
    DynamoDbClient dynamoDB;

    public final static String METER_COL = "meter";
    public final static String DATE_COL = "date";

    @ConfigProperty(name = "dynamodb_table_name")
    private String tableName;

    public String customerUsage(String customer, String date) {
        HashMap<String,AttributeValue> keyToGet = new HashMap<String,AttributeValue>();
        int total;

        keyToGet.put(METER_COL, AttributeValue.builder()
                .s(customer).build());
        keyToGet.put(DATE_COL, AttributeValue.builder()
                .s(date).build());

        try{
            Map<String,AttributeValue> returnedItem = dynamoDB.getItem(GetItemRequest.builder()
                                                        .key(keyToGet)
                                                        .tableName(tableName)
                                                        .build()).item();
            if (returnedItem != null) {
                Set<String> keys = returnedItem.keySet();
                System.out.println("Table Attributes: \n");
                total = 0;
                for (String key : keys) {
                    System.out.format("%s\n", key);
                    System.out.format("%s: %s\n", key, returnedItem.get(key).s());
                    if(key== METER_COL || key == DATE_COL)
                        continue;
                    total += Integer.parseInt(returnedItem.get(key).s());
                }
            } else {
                return "No usage found for "+ customer + "on " + date;
            }
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
        return String.format("Usage for customer %s on %s: %s", customer, date, Integer.toString(total));
    }

    public String dateUsage(String date) {
        return "To Do for collective  date usage " + date;
    }
}
