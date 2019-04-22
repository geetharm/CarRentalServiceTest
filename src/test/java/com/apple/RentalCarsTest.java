package com.apple;

import static io.restassured.RestAssured.given;

import com.apple.model.Car;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@TestPropertySource(locations = "classpath:dev.properties")
public class RentalCarsTest extends AbstractTestNGSpringContextTests {

    @Value("${baseURI}")
    private String baseUri;
    private static final Logger log = LoggerFactory.getLogger(RentalCarsTest.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private TypeFactory typeFactory = objectMapper.getTypeFactory();


    @Test(groups = "all")
    public void searchByColorAndMakeTest() {
        RestAssured.baseURI = baseUri;
        Response response = given().when().get().then().extract().response();

        try {
            List<Car> carsList =
                objectMapper.readValue((response).getBody().asInputStream(),
                    typeFactory.constructCollectionType(List.class, Car.class));

            log.info("Number of cars: " + carsList.size());

            for (Car car : carsList) {
                if (car.getMake().equalsIgnoreCase("Tesla")
                    && car.getMetadataObject().getColor().equalsIgnoreCase("Blue")) {
                    // print the car info
                    log.info("Details Of the Car " + car.toString());
                    // print the notes of the car
                    log.info("Notes Of the Car " + car.getMetadataObject().getNotes());
                }
            }
        } catch (IOException ioe) {
            log.info("Caught Exception " + ioe.getMessage());
        }
    }

    @Test(groups = "all")
    public void searchByHighestRevenueGeneratingCarTest() {

        float highestProfit = Float.MIN_VALUE;
        List<Car> carsWithHighestProfitList = new ArrayList<>();

        RestAssured.baseURI = baseUri;
        Response response = given().when().get().then().extract().response();

        try {
            List<Car> carsList =
                objectMapper.readValue((response).getBody().asInputStream(),
                    typeFactory.constructCollectionType(List.class, Car.class));

            for (Car car : carsList) {
                float currentIncome = (car.getPerdayrentObject().getPrice()
                        - car.getPerdayrentObject().getDiscount())
                        * car.getMetricsObject().getRentalcountObject().getYeartodate();

                float currentExpense =
                    car.getMetricsObject().getDepreciation()
                        + car.getMetricsObject().getYoymaintenancecost();

                float currentHighestProfit = currentIncome - currentExpense;

                log.info("current profit:  " + currentHighestProfit + "  car make:  " + car.getMake());
                if (currentHighestProfit > highestProfit) {
                    highestProfit = currentHighestProfit;
                    carsWithHighestProfitList.clear();
                    carsWithHighestProfitList.add(car);
                } else if (currentHighestProfit == highestProfit) {
                    carsWithHighestProfitList.add(car);
                }
            }

            // Print the cars with highest Profit
            log.info("Cars with the highest Profit");
            carsWithHighestProfitList.forEach(car->log.info("Car info: " + car.toString()));
        } catch (IOException ioe) {
            log.info("Caught Exception " + ioe.getMessage());
        }
    }


    @Test(groups = "all")
    public void searchByLowestRentalCostAndDiscountTest() {

        float lowestPrice = Float.MAX_VALUE;
        float lowestPriceWithDiscount = Float.MAX_VALUE;
        List<Car> carsWithLowestPriceList = new ArrayList<>();
        List<Car> carsWithLowestPriceAndDiscountList = new ArrayList<>();

        RestAssured.baseURI = baseUri;
        Response response = given().when().get().then().extract().response();

        try {
            List<Car> carsList =
                objectMapper.readValue((response).getBody().asInputStream(),
                    typeFactory.constructCollectionType(List.class, Car.class));

            for (Car car : carsList) {
                float currentPriceWithDiscount =
                    car.getPerdayrentObject().getPrice()
                        - car.getPerdayrentObject().getDiscount();

                if (currentPriceWithDiscount < lowestPriceWithDiscount) {
                    lowestPriceWithDiscount = currentPriceWithDiscount;
                    carsWithLowestPriceAndDiscountList.clear();
                    carsWithLowestPriceAndDiscountList.add(car);
                } else if (currentPriceWithDiscount == lowestPriceWithDiscount) {
                    carsWithLowestPriceAndDiscountList.add(car);
                }

                float currentPrice = car.getPerdayrentObject().getPrice();
                if (currentPrice < lowestPrice) {
                    lowestPrice = car.getPerdayrentObject().getPrice();
                    carsWithLowestPriceList.clear();
                    carsWithLowestPriceList.add(car);
                } else if (currentPrice == lowestPrice) {
                    carsWithLowestPriceList.add(car);
                }
            }

            // Print the cars with lowest Price
            log.info("Cars with the lowest Price");
            carsWithLowestPriceList.forEach(car->log.info("Car info: " + car.toString()));

            // Print the cars with lowest Price + discount
            log.info("Cars with the lowest Price and Discount");
            carsWithLowestPriceAndDiscountList.forEach(car->log.info("Car info: " + car.toString()));

        } catch (IOException ioe) {
            log.info("Caught Exception " + ioe.getMessage());
        }
    }
}