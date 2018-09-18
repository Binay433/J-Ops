package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.PublicHoliday;

public interface PublicHolidayService extends AbstractService<Integer,PublicHoliday>{
	public List<PublicHoliday> findByYear(int year);
}
