package com.test;

import com.naicha.app.utils.MemCached;

public class TestMemcache {
 public static void main(String[] args) {
	MemCached cached =  MemCached.getInstance();
//	cached.add("yang2", 22222);
//	cached.replace("yang2", 234234);
	System.out.println(cached.get("13538140693"));
}
}
