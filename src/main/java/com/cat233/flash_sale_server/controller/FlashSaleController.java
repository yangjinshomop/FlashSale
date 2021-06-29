package com.cat233.flash_sale_server.controller;

import java.util.Optional;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat233.flash_sale_server.FlashSaleService;
import com.cat233.flash_sale_server.model.Response;

@Controller
public class FlashSaleController {
	@Autowired FlashSaleService flashSaleService;
	
	@RequestMapping(value = "/api/seckill", method = RequestMethod.GET)
	@ResponseBody
	public Callable<Response<Long>> seckill(String userId, Long skuId){
		return new Callable<Response<Long>>() {

			@Override
			public Response<Long> call() throws Exception {			
				return flashSaleService.seckill(Optional.of(userId), skuId);
			}
			
		};
	}
}
