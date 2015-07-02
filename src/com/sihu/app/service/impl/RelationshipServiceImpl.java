package com.sihu.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sihu.app.dao.RelationshipDao;
import com.sihu.app.mode.Relationship;
import com.sihu.app.service.RelationshipService;

@Service
@Transactional
public class RelationshipServiceImpl implements RelationshipService {

	@Autowired
	private RelationshipDao relationshipDao;

	@Override
	public Relationship save(Relationship relationship) {
		return relationshipDao.save(relationship);
	}

}
 