package com.bjsxt.tq;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyTQ {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//配置
		Configuration conf = new Configuration();
		Job job= Job.getInstance(conf);
		
		job.setJarByClass(MyTQ.class);
		job.setJobName("tq");
		
		//2 设置输入输出路径
		
		Path inPath = new Path("tq/input");
		FileInputFormat.addInputPath(job, inPath);
		Path outPath = new Path("tq/output");
		if (outPath.getFileSystem(conf).exists(outPath)) {
			outPath.getFileSystem(conf).delete(outPath,true);
		}
		FileOutputFormat.setOutputPath(job, outPath);
		
		//3.设置mapper
		job.setMapperClass(Tmapper.class);
		job.setMapOutputKeyClass(Tq.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//4.自定义排序比较器
		job.setSortComparatorClass(TSortComparator.class);
		
		//5.自定义分区器
		job.setPartitionerClass(Tpartioner.class);
		
		//6.因为在重写比较器的时候添加了温度作为条件，因此会在比较时根据温度分组进行迭代比较，
		// 而本程序的设计是以同年同月为一组进行比较，所以需要另为设定一个组排序
		//即自定义组排序器
		job.setGroupingComparatorClass(TGroupComparator.class);
		
		//7. 设置reduce task即分区的数量
		job.setNumReduceTasks(2);
		
		//8.设置reducer
		job.setReducerClass(TReducer.class);
		
		
		job.waitForCompletion(true);
	}

}
