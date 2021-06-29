package com.cat233.flash_sale_server.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.BatchOptions;
import org.redisson.api.BatchResult;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBatch;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RScoredSortedSetAsync;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.cat233.flash_sale_server.FlashSaleService;
import com.cat233.flash_sale_server.FlashSaleTrade;
import com.cat233.flash_sale_server.UUIDShort;
import com.cat233.flash_sale_server.model.ErrorResponse;
import com.cat233.flash_sale_server.model.FlashSaleInfo;
import com.cat233.flash_sale_server.model.Response;
import com.cat233.flash_sale_server.model.SuccessfulResponse;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Component
public class FlashSaleServiceImpl implements FlashSaleService {
	@Autowired
	private RedissonClient redissonClient;
	@Autowired
    private RocketMQTemplate rocketMQTemplate;
	
	@Autowired
	private Cache<Long, FlashSaleInfo> flashSaleInfoCache ;
	
	@Override
	public Response<Long> seckill(Optional<String> userId, Long promotionId) {
		FlashSaleInfo flashSaleInfo = flashSaleInfoCache.getIfPresent(promotionId);
		if(flashSaleInfo == null) {
			return new ErrorResponse<Long>("ok", "200000", "业务失败", "抢购活动不存在或未开始", "抢购活动不存在或未开始");
		}
		if(System.currentTimeMillis() < flashSaleInfo.getStartTime()) {
			return new ErrorResponse<Long>("ok", "200000", "业务失败", "抢购活动不存在或未开始", "抢购活动不存在或未开始");
		}
		if(System.currentTimeMillis() > flashSaleInfo.getEndTime()) {
			return new ErrorResponse<Long>("ok", "200000", "业务失败", "抢购活动已结束", "抢购活动已结束");
		}
		if(flashSaleInfo.getIsSoldOut()) {
			return new ErrorResponse<Long>("ok", "200000", "业务失败", "抢完了", "抢完了");
		}
		System.out.println(System.nanoTime());
		RBatch rBatch = redissonClient.createBatch();
		RScoredSortedSetAsync<String> rScoredSortedSetAsync = rBatch.getScoredSortedSet(flashSaleInfo.getKey(),StringCodec.INSTANCE);
		rScoredSortedSetAsync.tryAddAsync(System.currentTimeMillis(),userId.get());
		rScoredSortedSetAsync.rankAsync(userId.get());
		BatchResult<?> result = rBatch.execute();
		if(Integer.valueOf(result.getResponses().get(1).toString()) < flashSaleInfo.getQuantity().intValue()) {
			FlashSaleTrade flashSaleTrade = new FlashSaleTrade();
			flashSaleTrade.setTradeId(UUIDShort.getUUIDShort());
			flashSaleTrade.setUserId(userId.get());
			flashSaleTrade.setPromotionId(promotionId);
			rocketMQTemplate.syncSend("flashSale-topic", flashSaleTrade);
			return new SuccessfulResponse<Long>(flashSaleTrade.getTradeId());
		}else {
			redissonClient.getScoredSortedSet(flashSaleInfo.getKey(),StringCodec.INSTANCE).remove(userId.get());
			flashSaleInfo.setIsSoldOut(true);
			flashSaleInfoCache.put(promotionId, flashSaleInfo);
			return new ErrorResponse<Long>("ok", "200000", "业务失败", "抢购失败", "抢购失败");
		}
//		RScoredSortedSet<String> rAtomicLong = redissonClient.getScoredSortedSet("flashSale"+skuId);
//		rAtomicLong.tryAdd(flashSaleTrade.getTradeId(),userId.get());
//		if(rAtomicLong.rank(userId.get()) <= a) {
//			rocketMQTemplate.syncSend("flashSale-topic", flashSaleTrade);
//		}
//		return new SuccessfulResponse<Long>(flashSaleTrade.getTradeId());
	}
}
