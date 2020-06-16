package com.liangrui.hadoop_disk.config.hadoop.conn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class HbaseConn {
	private Configuration configuration = null;
	private Connection connection = null;
    
    private static class SingletonHolder{
    	private static final HbaseConn INSTANCE = new HbaseConn();
    }
    
    private HbaseConn(){
    	try {
    		configuration = HBaseConfiguration.create();
        	configuration.set("hbase.zookeeper.quorum", "localhost");
        	configuration.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
        	connection = ConnectionFactory.createConnection(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final Connection getConn() {
    	return SingletonHolder.INSTANCE.connection;
    }
}