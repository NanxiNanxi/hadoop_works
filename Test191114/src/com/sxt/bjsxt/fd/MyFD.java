package com.sxt.bjsxt.fd;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyFD {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job= Job.getInstance(conf);
		job.setJarByClass(MyFD.class);
		job.setJobName("friend");
		
		Path inPath = new Path("/fd/input");
		FileInputFormat.addInputPath(job,inPath);
		Path outPath = new Path("/fd/output");
		if (outPath.getFileSystem(conf).exists(outPath)) {
			outPath.getFileSystem(conf).delete(outPath,true);
		}
		FileOutputFormat.setOutputPath(job, outPath);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapperClass(FMapper.class);
		job.setReducerClass(FReducer.class);
		job.waitForCompletion(true);
	}

}
