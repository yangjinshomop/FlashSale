package com.cat233.flash_sale_server;

public class UUIDShort {
	private static Long shard_index = 1L;
    private static Long index=0L;
    private static long server_startup_time_in_seconds;

    static{
        server_startup_time_in_seconds=System.currentTimeMillis()/1000;
    }
    
    public UUIDShort() {
        
    }
    
    public UUIDShort(Long shard_index) {
        UUIDShort.shard_index = shard_index;
    }

    
    public static Long getShard_index() {
        return shard_index;
    }

    public void setShard_index(Long shard_index) {
        UUIDShort.shard_index = shard_index;
    }

    public  static synchronized Long getUUIDShort() {  
        Long id=(shard_index <<56)+(server_startup_time_in_seconds <<24)+index;
       index++;
       return id;
    }
}
