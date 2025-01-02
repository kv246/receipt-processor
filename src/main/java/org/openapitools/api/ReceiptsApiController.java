package org.openapitools.api;

import java.util.Optional;

import javax.annotation.Generated;

import org.openapitools.model.Receipt;
import org.openapitools.model.ReceiptsIdPointsGet200Response;
import org.openapitools.model.ReceiptsIdPointsGet404Response;
import org.openapitools.model.ReceiptsProcessPost200Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-30T18:02:59.938206-06:00[America/Chicago]", comments = "Generator version: 7.10.0")
@Controller
@RequestMapping("${openapi.receiptProcessor.base-path:}")
public class ReceiptsApiController implements ReceiptsApi {

    private final ReceiptsService receiptsService;
    private final NativeWebRequest request;

    @Autowired
    public ReceiptsApiController(ReceiptsService receiptService, NativeWebRequest request) {
        this.receiptsService = receiptService;
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    @GetMapping("/receipts/{id}/points")
    public ResponseEntity<?> receiptsIdPointsGet(@PathVariable("id") String id) {
        // Logic to fetch points for the receipt
        Receipt r = receiptsService.getReceipt(id);
        if (r == null) {
            // Return 404 Not Found response if receipt is not found
            // ErrorResponse er = new ErrorResponse("Receipt not found", "The receipt with the given ID does not exist.");
            ReceiptsIdPointsGet404Response errorResponse = new ReceiptsIdPointsGet404Response("Not Found", "No receipt found for that ID.");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        long points = receiptsService.getPointsForReceipt(r);

        // Prepare the response
        ReceiptsIdPointsGet200Response response = new ReceiptsIdPointsGet200Response();
        response.setPoints(points);

        // Return the points with a 200 OK status
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PostMapping("/receipts/process")
    public ResponseEntity<ReceiptsProcessPost200Response> receiptsProcessPost(
        @RequestBody Receipt receipt
    ) {
        // Call service method to process the receipt
        Receipt processedReceipt = receiptsService.processReceipt(receipt);

        // Assuming the response returns the processed receipt's ID
        ReceiptsProcessPost200Response response = new ReceiptsProcessPost200Response();
        response.setId(processedReceipt.getId());

        // Return the response with a 200 OK status
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
