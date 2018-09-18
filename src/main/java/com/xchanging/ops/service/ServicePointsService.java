package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.ServicePoint;
import com.xchanging.ops.model.User;

public interface ServicePointsService extends AbstractService<Integer, ServicePoint>{
	public Integer findUserServicePoints(User user);
	public List<ServicePoint> findAllUserEarnedPoints();
	public void appendServicePoints();
	public List<ServicePoint> findDraftPoints();
	public void updateAllServicePoints();
	public List<ServicePoint> findAllUserSPList();
	public void kbApproved(Integer id);
	public List<ServicePoint> findByUserId(User user);
}
