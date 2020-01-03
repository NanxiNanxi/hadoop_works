package com.bjsxt.tq;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class Tpartioner extends Partitioner<Tq, IntWritable> {
	@Override
	//或者Key所在分区的编号，编号由分区数量来定
	public int getPartition(Tq key, IntWritable value, int numPartition) {
		// 使用Hashcode来分区，int的hashcode为自身
		return key.getYear()%numPartition;
	}
}
