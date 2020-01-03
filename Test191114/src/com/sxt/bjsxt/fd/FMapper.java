package com.sxt.bjsxt.fd;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

public class FMapper extends Mapper<Object, Text, Text, IntWritable >{

	Text tkey = new Text();
	IntWritable tval = new IntWritable();
	@Override
	protected void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] words = StringUtils.split(value.toString(), ' ');
		
		for (int i = 1; i < words.length; i++) {
			
			//用getFD方法把一对间接好友关系放在一个string里
			//用数组的第一个元素与后面的所有元素一一匹配，输出直接好友关系
			tkey.set(getFD(words[0], words[i]));
			//如果是直接好友则输出0
			tval.set(0);
			context.write(tkey, tval);
			for (int j = i+1; j < words.length; j++) {
				tkey.set(getFD(words[i], words[j]));
				//如果是间接好友关系输出1
				tval.set(1);
				context.write(tkey, tval);
			}
			
		}
		
	}
	
	private String getFD(String a, String b) {
		return a.compareTo(b)>0?b+":"+a:a+":"+b;
	}
}
