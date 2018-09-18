package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.ServicePoint;
import com.xchanging.ops.model.User;

public interface ServicePointsDAO extends AbstractDao<Integer,ServicePoint>{

	public Integer findUserServicePoints(User user);
	public List<ServicePoint> findAllUserEarnedPoints();
	public void updateServicePoints(User user);
	public List<ServicePoint> findDraftPoints();
	public void updateAllServicePoints();
	public List<ServicePoint> findAllUserSPList();
	public void appendAllServicePoints();
	public void kbApproved(Integer id);
	public List<ServicePoint> findByUserId(User user);
}
