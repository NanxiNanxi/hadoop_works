package com.bjsxt.tq;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TReducer extends Reducer<Tq, IntWritable, Text, IntWritable>{
@Override
protected void reduce(Tq key, Iterable<IntWritable> vals, Context context)
		throws IOException, InterruptedException {
	Text tKey = new Text();
	/*
	 * 1949-10-01 39
	 * 1949-10-01 37
	 * 1949-10-04 39
	 * 1949-10-05 32
	 *找出每个月最高温的2天
	 * 
	 * */
	IntWritable intVal = new IntWritable();
	int flag = 0;
	int day = 0;
	for(IntWritable val:vals){
		//只需要找出最高温的2天
		if(flag==0){
			tKey.set(key.toString());
			intVal.set(val.get());
			context.write(tKey, intVal);
			flag++;
			day=key.getDay();
		}
		//只有在第二天与前一天不等的时候再输出数据，并退出循环
		if (flag!=0&& day !=key.getDay()){
			tKey.set(key.toString());
			intVal.set(val.get());
			context.write(tKey, intVal);
			return;
		}
			
		}
	}
}
