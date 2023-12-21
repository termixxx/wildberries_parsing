package com.example.wb_api_parising.service.impl;

import com.example.wb_api_parising.entiti.temp.ApiResponse;
import com.example.wb_api_parising.service.ParsingService;
import com.example.wb_api_parising.service.ProductService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

@Service
@Qualifier("ParsingApiImpl")
public class ParsingApiImpl implements ParsingService {
    private static final Logger log = LoggerFactory.getLogger(ParsingApiImpl.class);
    private final ProductService productService;

    public ParsingApiImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void parse(Long brandId) {
        int page = 1;
        int cntOfProducts = 0;
        while (true) {
            try {
                String urlString = "https://catalog.wb.ru/brands/x/catalog?TestGroup=control&TestID=402&appType=1&brand=" + brandId
                        + "&curr=rub&dest=12358288&page=" + page + "&sort=popular&spp=27&uclusters=2";

                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");


                if (con.getResponseCode() == 429) {
                    String retryAfterHeader = con.getHeaderField("Retry-After");
                    int retryAfter = retryAfterHeader != null ? Integer.parseInt(retryAfterHeader) : 30;
                    log.warn("Too many requests. Waiting for " + retryAfter + " seconds.");
                    Thread.sleep(retryAfter * 1000L);
                    continue;
                }
                page++;
                InputStreamReader reader = new InputStreamReader(con.getInputStream());
                ApiResponse response = new Gson().fromJson(reader, ApiResponse.class);
                if (response.getData().getProducts().size() == 0) {
                    log.info("The products are over");
                    return;
                }
                response.getData().getProducts()
                        .forEach(productService::save);

                cntOfProducts += response.getData().getProducts().size();

                log.info("Total number of products processed: " + cntOfProducts);

                reader.close();

            } catch (IOException | InterruptedException e) {
                log.error(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
