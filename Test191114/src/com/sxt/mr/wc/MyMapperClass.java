package com.sxt.mr.wc;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapperClass extends Mapper<Object, Text, Text, IntWritable>{
//KEYIN当前字符的下标号数字, VALUEIN为输入的整行文字，KEYOUT为输出的KEY，VALUEOUT为输出的value
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	public void map(Object key,Text value,Context context) throws IOException, InterruptedException {
		StringTokenizer itr = new StringTokenizer(value.toString());
		while(itr.hasMoreTokens()){
			word.set(itr.nextToken());
			context.write(word, one);
		}
	}
}
