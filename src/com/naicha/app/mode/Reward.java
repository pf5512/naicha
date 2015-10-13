package com.naicha.app.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reward")
public class Reward {
	private Integer id;
	private Integer reward;
	private Date time;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "reward", nullable = false)
	public Integer getReward() {
		return reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	
	
	

	@Column(name = "time", nullable = false)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	

}
