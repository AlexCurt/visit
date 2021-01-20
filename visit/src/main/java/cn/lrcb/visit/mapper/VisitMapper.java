package cn.lrcb.visit.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.lrcb.visit.model.Visit;
import cn.lrcb.visit.model.VisitDto;

@Mapper
public interface VisitMapper {

	Visit getById(String id);
	
	List<VisitDto> getList();
	
	int addVisit(Visit entity);
	
	int deleteVisit(String id);
	
	int editVisit(Visit entity);
}
