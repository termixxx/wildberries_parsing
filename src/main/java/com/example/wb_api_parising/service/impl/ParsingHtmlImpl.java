package com.example.wb_api_parising.service.impl;

import com.example.wb_api_parising.entiti.Product;
import com.example.wb_api_parising.service.ParsingService;
import com.example.wb_api_parising.service.ProductService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@Qualifier("ParsingHtmlImpl")
public class ParsingHtmlImpl implements ParsingService {
    private static final Logger log = LoggerFactory.getLogger(ParsingApiImpl.class);
    private final ProductService productService;

    public ParsingHtmlImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void parse(Long brandId) {

        ClassPathResource resource = new ClassPathResource("seleniumDrivers/chromedriver.exe");
        try {
            System.setProperty("webdriver.chrome.driver", resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String urlString = "https://www.wildberries.ru/brands/" + brandId;
        try {
            driver.get(urlString);
            int cntOfProducts = 0;
            while (true) {
                long oldHeight = (Long) js.executeScript("return document.documentElement.scrollTop");
                long newHeight = oldHeight + 1;
                while (oldHeight != newHeight) {
                    js.executeScript("window.scrollBy(0, 1000)");
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    oldHeight = newHeight;
                    newHeight = (Long) js.executeScript("return document.documentElement.scrollTop");
                }

                String pageSource = driver.getPageSource();

                Document document = Jsoup.parse(pageSource);
                Elements elements = document.getElementsByClass("product-card");

                if (elements.size() == 0) {
                    return;
                }
                parseHtml(elements);
                cntOfProducts += elements.size();
                log.info("Total number of products processed: " + cntOfProducts);

                WebElement webElement = driver.findElement(By.className("pagination-next")); // кнопка перейти на след страницу
                webElement.click();
            }
        } catch (NoSuchElementException exception) {
            log.info("The pages are over");
            return; // Если не найдена кнопка, то значит след. Страницы нет
        } finally {
            driver.quit();
        }

    }

    private void parseHtml(Elements elements) {
        for (var el : elements) {
            long id = Long.parseLong(el.attr("data-nm-id"));

            String brand = el.getElementsByClass("product-card__brand").first().text();

            String name = el.getElementsByClass("product-card__name").first().text().substring(2);

            String price = el.getElementsByClass("price__lower-price").first().text();
            BigDecimal salePriceU = BigDecimal.valueOf(Long.parseLong(price.substring(0, price.length() - 2).replaceAll(" ", "")) * 100);

            String rating = el.getElementsByClass("address-rate-mini").first().text();
            double reviewRating = Double.parseDouble(rating.isEmpty() ? "0" : rating);

            String feed = el.getElementsByClass("product-card__count").first().text();

            int feedbacks = Integer.parseInt(
                    feed.equals("Нет оценок") ? "0" :
                            feed.substring(0, feed.length() - 6).replaceAll(" ", "")
            );

            productService.save(new Product(id, brand, name, salePriceU, reviewRating, feedbacks));
        }
    }
}
