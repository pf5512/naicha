package com.naicha.web.vo;

import java.math.BigInteger;

public class RespRankCount {
	private BigInteger count;
	private Integer rank;
	public Number getCount() {
		return count==null?-1:count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
	public Integer getRank() {
		return rank==null?-1:rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
}
