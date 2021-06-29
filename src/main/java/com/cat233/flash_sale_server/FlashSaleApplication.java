package com.cat233.flash_sale_server;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cat233.flash_sale_server.model.FlashSaleInfo;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@SpringBootApplication
public class FlashSaleApplication {

	public static void main(String args[]) {
		SpringApplication.run(FlashSaleApplication.class, args);
	}

	@Bean
	public Cache<Long, FlashSaleInfo> flashSaleInfoCache() {
		Cache<Long, FlashSaleInfo> cache = Caffeine.newBuilder()
	    .expireAfterWrite(10, TimeUnit.MINUTES)
	    .maximumSize(10000)
	    .build();
		FlashSaleInfo flashSaleInfo = new FlashSaleInfo();
		flashSaleInfo.setEndTime(System.currentTimeMillis()+60000);
		flashSaleInfo.setPromotionId(1L);
		flashSaleInfo.setQuantity(2);
		flashSaleInfo.setSkuId(1L);
		flashSaleInfo.setStartTime(System.currentTimeMillis());
		flashSaleInfo.setKey("flashSale"+1);
		cache.put(Long.valueOf(1), flashSaleInfo);
		return cache;
	}
}
