package com.sihu.app.dao;

import org.springframework.data.repository.Repository;

import com.sihu.app.mode.Relationship;

public interface RelationshipDao extends Repository<Relationship, Integer> {
	public Relationship save(Relationship comment);

}
