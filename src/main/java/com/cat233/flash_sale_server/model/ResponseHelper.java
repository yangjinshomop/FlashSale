package com.cat233.flash_sale_server.model;

import java.sql.SQLException;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResponseHelper {
	private static final Log logger = LogFactory.getLog(ResponseHelper.class);

	public static <T> Callable<Response<T>> getCallableResponse(Callable<T> c) {
		
		return null;
    }
	
//    public static <T> Response<T> getResponse(Callable<T> c) {
//        try {
//            T result = c.call();
//            return new SuccessfulResponse<>("OK","10000", "success", result);
//        }catch(BadSqlGrammarException sql){
//            logger.error("BadSqlGrammarException:", sql);
//            return new ErrorResponse<>("ERR", "20000", "Service Currently Unavailable", "isp.unknow-error", "服务不可用");
//        }catch (MyBatisSystemException ibatisErr) {
//            logger.error("MyBatisSystemException:", ibatisErr);
//            return new ErrorResponse<>("ERR", "20000", "Service Currently Unavailable", "isp.unknow-error", "服务不可用");
//        }catch (CannotAcquireLockException e) {
//            logger.error("CannotAcquireLockException:", e);
//            return new ErrorResponse<>("ERR", "20000", "Service Currently Unavailable", "api.too-many-requests", "请求过多，请稍后再试");
//        }catch (CatshitstarException e) {
//            logger.error("CatshitstarException:", e);
//            return new ErrorResponse<>("ERR", e.getCode(), e.getMsg(), e.getSub_code(), e.getSub_msg());
//        }catch (Exception e) {
//            logger.error("Exception:", e);
//            return new ErrorResponse<>("ERR", "20000", "Service Currently Unavailable", "isp.unknow-error", "服务不可用");
//        }
//    }
//
//    public static Response<Void> getResponseNoData(Runnable r) throws SQLException {
//        try {
//            r.run();
//            return new SuccessfulResponse<>("OK","10000", "success", null);
//        }catch (Exception e) {
//            logger.error("Exception:", e);
//            return new ErrorResponse<>("ERR", "20000", "Service Currently Unavailable", "isp.unknow-error", "服务不可用");
//        }
//    }
}
