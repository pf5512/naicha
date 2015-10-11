package com.test;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(locations = { "classpath:/applicationContext.xml",
        "classpath:/application-mongo.xml" })
public class LocationDBTest {
	 @Autowired
	    LocationDao locationDao;
	 
	    @Autowired
	    MongoTemplate template;
	 
	    public void setUp() {
	        // 等同db.location.ensureIndex( {position: "2d"} )
//	        template.indexOps(Location.class).ensureIndex(new GeospatialIndex("position"));
	        // 初始化数据
	        template.save(new Location("A", 0.1, -0.1));
	        template.save(new Location("B", 1, 1));
	        template.save(new Location("C", 0.5, 0.5));
	        template.save(new Location("D", -0.5, -0.5));
	    }
	 
	    public void findCircleNearTest() {
	        List<Location> locations = locationDao.findCircleNear(new Point(0, 0), 0.7);
	        print(locations);
	        System.err.println("-----------------------");
	        locations = locationDao.findCircleNear(new Point(0, 0), 0.75);
	        print(locations);
	    }
	 
	    public void findBoxNearTest() {
	        List<Location> locations = locationDao.findBoxNear(new Point(0.2, 0.2), new Point(1, 1));
	        print(locations);
	    }
	 
	    public static void print(Collection<Location> locations) {
	        for (Location location : locations) {
	            System.err.println(location);
	        }
	    }
}
