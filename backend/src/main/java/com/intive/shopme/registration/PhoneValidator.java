package com.intive.shopme.registration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
UKE provided public catalog of premium rate service numbers in Poland:
https://archiwum.uke.gov.pl/tablice/NumerUpo-list.do?execution=e1s1
https://archiwum.uke.gov.pl/tablice/xml/PRM.xml.zip

scripts/update_premium_numbers.sh - script to download current list from UKE and update local list used by backend service
*/

@Component
public class PhoneValidator implements Validator {

    private static Logger logger = LogManager.getLogger(PhoneValidator.class);

    private final static List<Integer> premiumNumbers = new ArrayList<>();

    public PhoneValidator() {
        try {
            for (String line : Files.readAllLines(Paths.get("backend/src/main/resources/premium_numbers.txt"))) {
                if (line.contains("-")) {
                    var startNumber = Integer.parseInt(line.split("-")[0]);
                    var endNumber = Integer.parseInt(line.split("-")[1]);
                    for (var number = startNumber; number <= endNumber; number++) {
                        premiumNumbers.add(number);
                    }
                } else {
                    premiumNumbers.add(Integer.parseInt(line));
                }
            }
        } catch (IOException e) {
            logger.error("Error parsing premium rate phone numbers!");
        }
        if (premiumNumbers.size() > 0) {
            logger.info("Loaded " + premiumNumbers.size() + " blacklisted premium rate phone numbers");
        } else {
            logger.error("Failed to load list of premium rate phone numbers!");
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type == String.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        var number = Integer.parseInt((String) target);
        if (premiumNumbers.contains(number)) {
            errors.rejectValue("phoneNumber","",
                    "Premium rate phone number " + number + " is not acceptable!");
        }
    }
}
