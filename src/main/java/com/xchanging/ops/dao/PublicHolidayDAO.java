package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.PublicHoliday;

public interface PublicHolidayDAO extends AbstractDao<Integer, PublicHoliday>{
	public List<PublicHoliday> findByYear(int year);
}
