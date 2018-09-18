package com.xchanging.ops.dao;

import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.TranRelease;

@Repository("releaseDao")
public class ReleaseDaoImpl extends AbstractDaoImpl<Integer,TranRelease> implements ReleaseDao{

}
