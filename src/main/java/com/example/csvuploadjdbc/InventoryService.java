package com.example.csvuploadjdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;

@Service
public class InventoryService {

	private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);
    private final JdbcTemplate jdbcTemplate;

    public InventoryService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void importProductsFromCsv(String filePath) {
        Path path = Paths.get(filePath);
        List<Inventory> products = new ArrayList<>();

        logger.info("Reading CSV file");
        // Use try-with-resources to ensure the BufferedReader is closed, which also closes the stream
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            // Skip the header line
            products = reader.lines()
                .skip(1) 
                .map(this::mapCsvLineToProduct) // Map each line to a Product object
                .collect(Collectors.toList());
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }

        // Save the list of products to the database using batch update
        saveProducts(products);
    }

    private Inventory mapCsvLineToProduct(String line) {
        // A simple split might fail with complex CSV (e.g., commas in values).
        // For robust parsing, OpenCSV or Apache Commons CSV is recommended.
        // A basic example using String.split():
        String[] fields = line.split(","); 

        logger.info("Creating Obj..");
        // Basic validation and mapping
        if (fields.length == 9) {
            try {
            	Inventory inv = new Inventory( fields[0],
                		fields[1],
                		fields[2],
                		new Date(),
                		new Date(),
                		fields[3],
                		new BigDecimal(fields[4]),
                		fields[5],
                		fields[6],
                		fields[7],
                		fields[8] );
            	return inv;
            } catch (NumberFormatException e) {
                // Log or handle parsing errors for specific lines
                System.err.println("Error parsing line: " + line);
            }
        }
        return null; // or throw an exception
    }

    public void saveProducts(List<Inventory> invs) {
    	logger.info("Saving to DB...");
        String sql = "INSERT INTO inventory (name, category, sub_category, expiry_date, mod_date, specification, price, stock, model, seller, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object[]> batchArgs = invs.stream()
            .map(inv -> new Object[]{inv.getName(), inv.getCategory(), 
            						inv.getSubCategory(), inv.getExpiryDate(),
            						inv.getModDate(), inv.getSpecification(),
            						inv.getPrice(),inv.getStock(),
            						inv.getModel(), inv.getSeller(),
            						inv.getLocation()})
            .collect(Collectors.toList());

        // Use batchUpdate for efficient insertion of large datasets
        int[] result = jdbcTemplate.batchUpdate(sql, batchArgs);
        logger.info("Inserted {} records into the database.", result.length);
        logger.info("Inserted {} records into the database.", Arrays.toString(result));
    }
}
