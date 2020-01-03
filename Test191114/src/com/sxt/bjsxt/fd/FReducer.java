package com.sxt.bjsxt.fd;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	IntWritable tval =new IntWritable();;
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int num = 0;
		for(IntWritable val:values){
			//如果有0说明为直接好友，直接结束计算，只计算在没有0的前提下key的1的频度
			if(val.get()==0){
				return;
			}
			
			num++;
		}
		tval.set(num);
		context.write(key, tval);
	}
}
