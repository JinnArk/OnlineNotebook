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

import com.notebook.entities.UserInfo;
import com.notebook.service.UserInfoService;
import com.notebook.util.CommonUtil;
import com.notebook.util.ConstantUtil;
import com.notebook.util.PasswordUtil;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月28日
 * @remarks	公用控制器
 */
@Controller
@RequestMapping(value={"/commons"})
public class CommonController {
	
	@Autowired
	UserInfoService userInfoService;
	
	String uiORadmin = null;//去admin还是ui
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 密码修改页面
	 */
	@RequestMapping(value="/password", method=RequestMethod.GET)
	public ModelAndView password(final Model model, final HttpServletRequest request, HttpServletResponse response){

		UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
		uiORadmin = CommonUtil.whereTogo(user);
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.PASSWORD);
		return new ModelAndView(uiORadmin);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月24日
	 * @remarks AJAX修改密码
	 */
	@RequestMapping(value = "/newpassword", method = RequestMethod.POST)
    @ResponseBody
    public String newpassword(String oldpassword,String newpassword) {
		
		try {
			String salt = PasswordUtil.createSalt();
			//获取当前用户
	 		UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			if(PasswordUtil.encryptPassword(oldpassword,user.getSalt()).equals(user.getPassword())){//旧密码是否正确
		 		user.setSalt(salt);
		 		user.setPassword(PasswordUtil.encryptPassword(newpassword,salt));
		 		userInfoService.saveUserInfo(user);
				//int result = userInfoService.saveUserInfo(user);//影响行数
				return ConstantUtil.AJAX_SUCCESS;
				
			}else{
				return ConstantUtil.AJAX_FAIL;
			}
	 		
			
		} catch (Exception e) {
			e.printStackTrace();
			return ConstantUtil.AJAX_ERROR;
		}
	}
}
