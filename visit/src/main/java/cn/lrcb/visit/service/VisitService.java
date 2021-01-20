package cn.lrcb.visit.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lrcb.visit.mapper.VisitMapper;
import cn.lrcb.visit.model.Visit;
import cn.lrcb.visit.model.VisitDto;

@Service
public class VisitService {

	@Resource
	VisitMapper visitMapper;
	
	public Visit getById(String id) {
		Visit visit = visitMapper.getById(id);
		return visit;
	}
	
	public List<VisitDto> getList(){
		List<VisitDto> list = visitMapper.getList();
		return list;
	}
	
	public int addVisit(Visit entity) {
		int code = 0;
		try {
			visitMapper.addVisit(entity);
		} catch (Exception e) {
			// TODO: handle exception
			code = 1;
		}
		return code;
	}
	
	public int editVisit(Visit entity) {
		int code = 0;
		try {
			visitMapper.editVisit(entity);
		} catch (Exception e) {
			// TODO: handle exception
			code = 1;
		}
		return code;
	}
	
	public int deleteVisit(String id) {
		int code = 0 ;
		try {
			visitMapper.deleteVisit(id);
		} catch (Exception e) {
			// TODO: handle exception
			code = 1;
		}
		return code;
	}
}
