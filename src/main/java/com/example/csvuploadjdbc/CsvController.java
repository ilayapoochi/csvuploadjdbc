package com.example.csvuploadjdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CsvController {

	private static final Logger logger = LoggerFactory.getLogger(CsvController.class);
    @Autowired
    private InventoryService invService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("filepath") String filepath) {
        try {
        	logger.info("fileName- {}",filepath);
        	invService.importProductsFromCsv("D:\\inoutput\\"+filepath+".csv");
            return ResponseEntity.ok("CSV data saved successfully with streaming!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/myget")
    public ResponseEntity<String> myget(){
    	return ResponseEntity.ok("CSV data saved successfully with streaming!");
    }
}

