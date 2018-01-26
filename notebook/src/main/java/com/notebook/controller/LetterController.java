package com.notebook.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.entities.UserInfo;
import com.notebook.model.common.LetterModel;
import com.notebook.service.LetterService;
import com.notebook.service.UserInfoService;
import com.notebook.util.ConstantUtil;
import com.notebook.util.StringUtil;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月25日
 * @remarks	站内信控制器
 */
@Controller
@RequestMapping(value={"/commons"})
public class LetterController {
	
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	LetterService letterService;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 未读站内信(标记为未读0)
	 */
	@RequestMapping(value="/letterUnread", method=RequestMethod.GET)
	public ModelAndView letterUnread(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			String pagenow = request.getParameter("pagenow");
			UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			
			Page<LetterModel> unreadLetterPage = null;
			if(!StringUtil.isEmpty(pagenow)){
				unreadLetterPage = new Page<LetterModel>(Integer.valueOf(pagenow).intValue(), 5);
			}else{
				unreadLetterPage = new Page<LetterModel>(1,5);
			}
		
			int[] states = new int[1];states[0]=0;//站内信状态
			
			letterService.getLetterModelsByPageAndConditionForRecipient(unreadLetterPage, states, user.getUserId(), null);
			unreadLetterPage.setTotal(letterService.getAllLetterNumByConditionForRecipient(states, user.getUserId(), null));
			
			//用于pagemodel跳转的url
			model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.UNREADLETTERSURL);
			
			model.addAttribute(ConstantUtil.PAGEMODELS, unreadLetterPage);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LETTER_UNREAD);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 新建站内信
	 */
	@RequestMapping(value="/letterNew", method=RequestMethod.GET)
	public ModelAndView newLetter(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LETTER_NEW);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	已发送站内信
	 */
	@RequestMapping(value="/letterSended", method=RequestMethod.GET)
	public ModelAndView letterSended(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			String pagenow = request.getParameter("pagenow");
			String createDate = request.getParameter("createDate");
			UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			
			Page<LetterModel> sendedLetterPage = null;
			if(!StringUtil.isEmpty(pagenow)){
				sendedLetterPage = new Page<LetterModel>(Integer.valueOf(pagenow).intValue(), 5);
			}else{
				sendedLetterPage = new Page<LetterModel>(1,5);
			}
		
			int[] states = new int[3];states[0]=0;states[1]=1;states[2]=2;//站内信状态
			
			letterService.getLetterModelsByPageAndConditionForSended(sendedLetterPage, states, user.getUserId(), createDate);
			sendedLetterPage.setTotal(letterService.getAllLetterNumByConditionForSended(states, user.getUserId(), createDate));
			
			//用于pagemodel跳转的url
			model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.SENDEDLETTERSURL);
			if(!StringUtil.isEmpty(createDate)){
				//url参数
				model.addAttribute(ConstantUtil.PAGEMODEL_PARAM, "createDate="+createDate);
			}
			model.addAttribute(ConstantUtil.PAGEMODELS, sendedLetterPage);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LETTER_SENDED);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	将-一个ID-站内信标记为已读
	 */
	@RequestMapping(value="/readLetter", method=RequestMethod.GET)
	public ModelAndView readLetter(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			String letterID = request.getParameter("letterID");
			letterService.updateOneLetterState(letterID);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ModelAndView(ConstantUtil.TOUNREADLETTERSPAGE);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	AJAX将-一个ID-站内信标记为已读
	 */
	@RequestMapping(value = "/readOneLetter", method = RequestMethod.POST)
    @ResponseBody
    public String readOneLetter(String letterID) {
		
		try {
			int result = letterService.updateOneLetterState(letterID);
			if(result==1){
				return ConstantUtil.AJAX_SUCCESS;
			}else{
				return ConstantUtil.AJAX_FAIL;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ConstantUtil.AJAX_ERROR;
		}
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	AJAX将-一组ID-站内信标记为已读
	 */
	@RequestMapping(value = "/readLetters", method = RequestMethod.POST)
    @ResponseBody
    public String readLetters(String[] letterIDs) {
		
		try {
			//letterIDs = new String[3];
			//letterIDs[0]="6";letterIDs[1]="7";letterIDs[2]="8";
			int result = letterService.updateLettersState(letterIDs);
			
			if(result==letterIDs.length){
				return ConstantUtil.AJAX_SUCCESS;
			}else{
				return ConstantUtil.AJAX_FAIL;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ConstantUtil.AJAX_ERROR;
		}
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	AJAX发送站内信
	 */
	@RequestMapping(value = "/sendLetter", method = RequestMethod.POST)
    @ResponseBody
    public String sendLetter(String recipientName,String letterTitle,String letterContent,String replyLetterID) {
		
		try {
			UserInfo sendUser = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			UserInfo recipientUser = userInfoService.getUserInfoByUsername(recipientName);
			
			letterService.saveLetter(sendUser, recipientUser, recipientName, letterTitle, letterContent, replyLetterID);
			
			return ConstantUtil.AJAX_SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
			return ConstantUtil.AJAX_ERROR;
		}
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 搜索站内信(标记为已读1)
	 */
	@RequestMapping(value="/letterSerach", method=RequestMethod.GET)
	public ModelAndView letterSerach(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		try {
			String pagenow = request.getParameter("pagenow");
			String createDate = request.getParameter("createDate");
			
			if(!StringUtil.isEmpty(createDate)){
				UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
				
				Page<LetterModel> readLetterPage = null;
				if(!StringUtil.isEmpty(pagenow)){
					readLetterPage = new Page<LetterModel>(Integer.valueOf(pagenow).intValue(), 5);
				}else{
					readLetterPage = new Page<LetterModel>(1,5);
				}
				
				int[] states = new int[2];states[0]=1;states[1]=2;//站内信状态
				
				letterService.getLetterModelsByPageAndConditionForRecipient(readLetterPage, states, user.getUserId(), createDate);
				readLetterPage.setTotal(letterService.getAllLetterNumByConditionForRecipient(states, user.getUserId(), createDate));
				//用于pagemodel跳转的url
				model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.SERACHLETTERSURL);
				//url参数
				model.addAttribute(ConstantUtil.PAGEMODEL_PARAM, "createDate="+createDate);
				
				model.addAttribute(ConstantUtil.PAGEMODELS, readLetterPage);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LETTER_SERACH);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
}
