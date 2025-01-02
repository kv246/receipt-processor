package org.openapitools.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.openapitools.model.Item;
import org.openapitools.model.Receipt;
import org.springframework.stereotype.Service;

@Service
public class ReceiptsService {
    // In-memory data store for receipts
    private final Map<String, Receipt> receipts = new HashMap<>();

    public Receipt processReceipt(Receipt receipt) {
        // Create an ID for the processed receipt (in reality, you'd save it to a DB)
        String uuid = UUID.randomUUID().toString();
        receipt.setId(uuid);

        // Process the receipt (e.g., calculate points, etc.)
        long p = 0;
        double total = Double.parseDouble(receipt.getTotal());
        List<Item> items = receipt.getItems();
        int day = receipt.getPurchaseDate().getDayOfMonth();
        
        String[] timeArr = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int min = Integer.parseInt(timeArr[1]);

        // One point for every alphanumeric character in the retailer name.
        for (char c : receipt.getRetailer().toCharArray()) {
            p += Character.isLetterOrDigit(c) ? 1 : 0;
        }

        // 50 points if the total is a round dollar amount with no cents.
        if (total % 1 == 0) {
            p += 50;
        }

        // 25 points if the total is a multiple of 0.25.
        if (total % 0.25 == 0) {
            p += 25;
        }

        // 5 points for every two items on the receipt.
        int itemsLength = receipt.getItems().size();
        p += (itemsLength / 2) * 5;

        // If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        for (Item i : items) {
            String desc = i.getShortDescription();
            double price = Double.parseDouble(i.getPrice());
            if (desc.trim().length() % 3 == 0) {
                p += Math.ceil(price * 0.2);
            }
        }

        // 6 points if the day in the purchase date is odd.
        if (day % 2 == 1) {
            p += 6;
        }

        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
        if ((hour == 14 && min > 0) || (hour > 14 && hour < 16)) {
            p += 10;
        }
        
        receipt.setAmount(p);
        receipts.put(uuid, receipt);
        return receipt;
    }

    public Long getPointsForReceipt(Receipt receipt) {
        return receipt.getAmount();
    }

    public Receipt getReceipt(String id) {
        return receipts.get(id);
    }
}