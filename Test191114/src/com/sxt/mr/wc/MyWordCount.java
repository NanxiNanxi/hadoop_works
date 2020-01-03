package com.sxt.mr.wc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyWordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf);
	    job.setJarByClass(MyWordCount.class);
	
	    Path inPath = new Path("/user/root/test.txt");
		FileInputFormat.addInputPath(job, inPath);
		Path outPath = new Path("/output/wordcount");
		//delete path if exists
		if(outPath.getFileSystem(conf).exists(outPath)){
			outPath.getFileSystem(conf).delete(outPath,true);
		}
		FileOutputFormat.setOutputPath(job, outPath);
		
		job.setMapperClass(MyMapperClass.class);
		job.setMapOutputKeyClass(Text.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setReducerClass(MyReduceClass.class);
		
		job.waitForCompletion(true);
	    
	}
}
