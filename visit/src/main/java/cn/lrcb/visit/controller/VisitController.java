package cn.lrcb.visit.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lrcb.visit.model.BaseModel;
import cn.lrcb.visit.model.Visit;
import cn.lrcb.visit.model.VisitDto;
import cn.lrcb.visit.service.VisitService;
import cn.lrcb.visit.util.HttpUtil;

@Controller
public class VisitController extends BaseController {

	@Autowired
	private VisitService visitService;
	
	@ResponseBody
	@RequestMapping(value = "/list")
	public BaseModel getList(String fileName) {
		String url="";
		String head = "http://";
		String symbol = "/";
		List<VisitDto> list = new ArrayList<VisitDto>();
		list = visitService.getList();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (int i = 0; i < list.size(); i++) {
			String ip = list.get(i).getIp();
			String port = list.get(i).getPort();
			url=head+ip+":"+port+symbol;
			nvps.add(new BasicNameValuePair("search", fileName));
			nvps.add(new BasicNameValuePair("count", "1"));
			nvps.add(new BasicNameValuePair("j", "1"));
			if(null==fileName||"".equals(fileName)) {
				list.get(i).setNumber("0");
			}else {
				list.get(i).setNumber(HttpUtil.doGet(url, nvps));
			}
		}
	  return makeModel(SUCC_CODE, MSG_SUCC, list); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/getById")
	public BaseModel getById(String id){
	  return makeModel(SUCC_CODE, MSG_SUCC, visitService.getById(id));
	}
	
	@ResponseBody
	@RequestMapping(value = "/add")
	public BaseModel addVisit(Visit model) {
		int code = visitService.addVisit(model);
		
		if (code==0) {
			return makeModel(code, MSG_ADD_SUCC);
		} else {
			return makeModel(code, MSG_ADD_ERROR);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	public BaseModel editVisit(Visit model) {
		int code = visitService.editVisit(model);
		
		if(code==0) {
			return makeModel(code, MSG_UPDATE_SUCC);
		}else {
			return makeModel(code, MSG_UPDATE_ERROR);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public  BaseModel deleteVisit(String id) {
		int code = visitService.deleteVisit(id);
		if (code==0) {
			return makeModel(code, MSG_DELETE_SUCC);
		} else {
			return makeModel(code, MSG_DELETE_ERROR );
		}
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/query")
//	public BaseModel queryFile(String fileName) {
//		String url = "http://192.168.43.194:85/";
//		List<Visit> list = new ArrayList<Visit>();
//		list = visitService.getList();
//		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
////		for (int i = 0; i < list.size(); i++) {
////			String ip = list.get(i).getIp();
////			String port = list.get(i).getPort();
////			url+=ip+":"+port;
////		}
//		nvps.add(new BasicNameValuePair("search", fileName));
//		nvps.add(new BasicNameValuePair("count", "1"));
//		nvps.add(new BasicNameValuePair("j", "1"));
//
//		return makeModel(SUCC_CODE, MSG_SUCC,HttpUtil.doGet(url, nvps));
//	}
}
