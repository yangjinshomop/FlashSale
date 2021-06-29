package com.cat233.flash_sale_server;

import java.util.Optional;

import com.cat233.flash_sale_server.model.Response;

public interface FlashSaleService {
	public Response<Long> seckill(Optional<String> userId, Long skuId);
}
