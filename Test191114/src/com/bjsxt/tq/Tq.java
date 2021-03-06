package com.bjsxt.tq;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;


public class Tq implements WritableComparable<Tq>{
	
	private int year;
	private int month;
	private int day;
	private int wd;//温度
	

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWd() {
		return wd;
	}

	public void setWd(int wd) {
		this.wd = wd;
	}

	//序列化与反序列化，顺序必须相同
	@Override
	public void readFields(DataInput in) throws IOException {
		
		this.setYear(in.readInt());
		this.setMonth(in.readInt());
		this.setDay(in.readInt());
		this.setWd(in.readInt());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeInt(this.getYear());
		out.writeInt(this.getMonth());
		out.writeInt(this.getDay());
		out.writeInt(this.getWd());
	}

	@Override
	public int compareTo(Tq o) {
		int c1 = Integer.compare(this.getYear(), o.getYear());
		if(c1==0){
		int c2=Integer.compare(this.getMonth(), o.getMonth());
			if(c2==0){
				return Integer.compare(this.getDay(), o.getDay());
			}
			return c2;
		}
		return c1;
	}

	@Override
	public String toString() {
		return year + "-" +month+"-"+day;
	}
	
	

}
