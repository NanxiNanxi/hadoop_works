package com.bjsxt.tq;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;



public class Tmapper extends Mapper<Object, Text, Tq, IntWritable> {
	Tq tkey = new Tq();
	IntWritable tval = new IntWritable();
	
	@Override
	protected void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		
		String[] words = StringUtils.split(value.toString(), '\t');
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	
			try {
				Date date = sdf.parse(words[0]);
				
				//处理日期
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				tkey.setYear(cal.get(Calendar.YEAR));
				tkey.setMonth(cal.get(Calendar.MONTH));
				tkey.setDay(cal.get(Calendar.DAY_OF_MONTH));
				
				//处理温度
				int wd = Integer.parseInt(words[1].substring(0,words[1].lastIndexOf("c")));
				tkey.setWd(wd);
				tval.set(wd);
				
				
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			context.write(tkey, tval);
	
		
		
	}

}
