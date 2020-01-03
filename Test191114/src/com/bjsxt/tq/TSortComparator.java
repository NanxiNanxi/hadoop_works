package com.bjsxt.tq;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TSortComparator extends WritableComparator{
	
	Tq t1 = null;
	Tq t2 = null;
	
	
	public TSortComparator() {
		super(Tq.class, true); //super一个带参数的父类构造器
	}


	@Override
	public int compare(WritableComparable a, WritableComparable b) {
	
		t1=(Tq)a;
		t2=(Tq)b;
		
		int c1 = Integer.compare(t1.getYear(), t2.getYear());
		if (c1==0) {
			int c2 = Integer.compare(t1.getMonth(), t2.getMonth());
			if(c2==0){
				//默认为从小到大，加负号可设为从大到小
				return (-Integer.compare(t1.getWd(), t2.getWd()));
			}
			
			
			return c2;
		}
		return c1;
		
	}
}
