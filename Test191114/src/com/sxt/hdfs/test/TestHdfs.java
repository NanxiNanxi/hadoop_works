package com.sxt.hdfs.test;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestHdfs {
	Configuration conf = null;
	FileSystem fs = null;
	
	
	@Before
	public void conn() throws IOException{
		conf = new Configuration();
		fs = FileSystem.get(conf);
		
	}
	
	@Test
	public void mkdir() throws IOException{
		Path path = new Path("/myTemp");
		fs.mkdirs(path);
		if(fs.exists(path)){
			fs.delete(path,true);
		}
		fs.mkdirs(path);
	}
	
	@Test
	public void uploadFile() throws IOException{
		//文件上传路径
		Path path = new Path("/mytemp/haha.txt");
		FSDataOutputStream fdos =  fs.create(path);
		
		//get file from disk
		InputStream is = new BufferedInputStream(new FileInputStream("C:/Users/nancy/Documents/BD/rs/test.txt"));
		
		IOUtils.copyBytes(is, fdos, conf, true);
	}
	
	@Test
	public void readFile() throws IOException{
		Path path = new Path("/user/root/test.txt");
		/*FileStatus file = fs.getFileStatus(path);
		BlockLocation[] blks = fs.getFileBlockLocations(file, 0, file.getLen());
		for(BlockLocation blk:blks){
			System.out.println(blk);
		}*/
		FSDataInputStream fsis = fs.open(path);
		
		//根据偏移量寻找需要的文件部分
		fsis.seek(180000);
		System.out.print((char)fsis.readByte());
		System.out.print((char)fsis.readByte());
		System.out.print((char)fsis.readByte());
		System.out.print((char)fsis.readByte());
		System.out.print((char)fsis.readByte());

	}
	
	
	@After
	public void close() throws IOException{
		if(fs != null){
			fs.close();
		}
		
	}

}
