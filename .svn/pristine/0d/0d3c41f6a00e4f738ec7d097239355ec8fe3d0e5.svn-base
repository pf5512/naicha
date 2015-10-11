package com.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class LocationDao {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Location> findCircleNear(Point point,double maxDistance){
		return mongoTemplate.find(new Query(Criteria.where("position").near(point).maxDistance(maxDistance)),Location.class);
	}
	
	public List<Location> findBoxNear(Point lowerLeft,Point upperRight){
		return mongoTemplate.find(new Query(Criteria.where("position").withinBox(new Box(lowerLeft, upperRight))), Location.class);
	}
}
