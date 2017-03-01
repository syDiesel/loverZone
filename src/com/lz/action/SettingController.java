package com.lz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lz.entity.LoverApply;
import com.lz.entity.SecQuestion;
import com.lz.entity.Security;
import com.lz.entity.UserInfo;
import com.lz.service.LoverApplyService;
import com.lz.service.SecQuestionService;
import com.lz.service.SecurityService;
import com.lz.service.UserInfoService;
import com.lz.util.Fenye;
import com.lz.util.ResponseUtil;

@Controller
@RequestMapping(value = "/web/set")
public class SettingController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private SecQuestionService secQuestionService;

	@Resource
	private SecurityService securityService;

	@Resource
	private LoverApplyService loverApplyService;

	/*
	 * //查找好友
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping("searchFri/{id}.html") public String
	 * updateInfo(HttpServletRequest request, HttpServletResponse response,
	 * 
	 * @PathVariable long id){
	 * 
	 * System.out.println(
	 * "----------------------web/set/searchFri---------------------------");
	 * 
	 * String sql = "from Friend where userInfoByUserId.id = "+id; List<Friend>
	 * listF = new ArrayList<Friend>();
	 * if(friendService.listFriendBySql(sql).size()>0){
	 * 
	 * listF = friendService.listFriendBySql(sql);
	 * 
	 * }
	 * 
	 * //分页 List<Friend> item = null; int page = 1; int totalPage =
	 * listF.size(); try { page =
	 * Integer.parseInt(request.getParameter("page")); } catch (Exception e) {
	 * page = 1; } int pageSize = 10; // 最后一页 int lastPage =
	 * Fenye.lastPage(totalPage, page, pageSize); if (page > lastPage) { page =
	 * lastPage; }
	 * 
	 * request.setAttribute("lastPage", lastPage); request.setAttribute("page",
	 * page);
	 * 
	 * // 当前页第一个的位置 item = Fenye.fenye(listF, totalPage, page, pageSize,
	 * lastPage);
	 * 
	 * request.setAttribute("listF",item); return "../../setting/friend"; }
	 */

	// 进入个人资料页面
	@RequestMapping("toSetMain.html")
	public void toSetMain(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		if (loginUser == null) {

			ResponseUtil.write(response, "no_login");

		} else {
			ResponseUtil.write(response, "ok");
		}

	}

	// 进入个人资料页面
	@RequestMapping("perInfo.html")
	public void perInfo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		if (loginUser == null) {

			ResponseUtil.write(response, "no_login");

		} else {

			String sql = "from UserInfo where id = "+loginUser.getId();
			loginUser = userInfoService.listUserInfoBySql(sql).get(0);
			System.out.println(loginUser.getUserInfo());
			request.getSession().setAttribute("loginUser", loginUser);
			ResponseUtil.write(response, "ok");
		}

	}

	// 发现伴侣
	@SuppressWarnings("unchecked")
	@RequestMapping("searchLover.html")
	public String searchLover(HttpServletRequest request,
			HttpServletResponse response) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		if (loginUser == null) {

			request.setAttribute("result", "用户没有登录，请重新登录");
			return "../error/result";
		} else {

			String sex = loginUser.getSex();
			List<UserInfo> listLover = new ArrayList<UserInfo>();
			String sql = "from UserInfo where userInfo = null and (sex <> null and sex <> '"
					+ sex + "')";
			if (userInfoService.listUserInfoBySql(sql).size() > 0) {

				listLover = userInfoService.listUserInfoBySql(sql);
			}

			// 分页
			List<UserInfo> item = null;
			int page = 1;
			int totalPage = listLover.size();
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {
				page = 1;
			}
			int pageSize = 10; // 最后一页
			int lastPage = Fenye.lastPage(totalPage, page, pageSize);
			if (page > lastPage) {
				page = lastPage;
			}
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("page", page);
			// 当前页第一个的位置
			item = Fenye.fenye(listLover, totalPage, page, pageSize, lastPage);

			request.setAttribute("listLover", item);
		}

		return "../setting/friend";
	}

	// 申请查询
	@SuppressWarnings("unchecked")
	@RequestMapping("applyCheck.html")
	public String applyCheck(HttpServletRequest request,
			HttpServletResponse response) {

		System.out
				.println("----------------------applyCheck---------------------------");

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		if (loginUser == null) {

			return "../../../index";
		}

		List<LoverApply> listApplyer = new ArrayList<LoverApply>();
		String sql = "from LoverApply where userInfoByLoverId = '"
				+ loginUser.getId() + "' and statue = 0";
		if (loverApplyService.listLoverApplyBySql(sql).size() > 0) {

			listApplyer = loverApplyService.listLoverApplyBySql(sql);
		}

		// 分页
		List<UserInfo> item = null;
		int page = 1;
		int totalPage = listApplyer.size();
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		int pageSize = 10; // 最后一页
		int lastPage = Fenye.lastPage(totalPage, page, pageSize);
		if (page > lastPage) {
			page = lastPage;
		}
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("page", page);
		// 当前页第一个的位置
		item = Fenye.fenye(listApplyer, totalPage, page, pageSize, lastPage);

		request.setAttribute("listApplyer", item);

		return "../setting/apply";
	}

	// 密保问题，取出是否有保存
	@RequestMapping("secuQues/{id}.html")
	public String secuQues(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id)
			throws IOException {

		System.out
				.println("----------------------web/set/secuQues---------------------------");
		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		if (loginUser == null) {

			return "../../../index";
		}

		List<SecQuestion> listQuestion = new ArrayList<SecQuestion>();
		listQuestion = secQuestionService
				.listSecQuestionBySql("from SecQuestion");

		request.setAttribute("listQuestion", listQuestion);

		List<Security> listSec = new ArrayList<Security>();
		String sql = "from Security where userInfo.id = " + id;
		if (securityService.listSecurityBySql(sql).size() > 0) {

			listSec = securityService.listSecurityBySql(sql);
		}

		request.setAttribute("listSec", listSec);

		return "../../setting/security";
	}

	// 设置密保问题
	@RequestMapping("setSecurity.html")
	public String setSecurity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		System.out
				.println("----------------------web/set/setSecurity---------------------------");

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		if (loginUser == null) {

			return "../../index";
		}

		int val = Integer.parseInt(request.getParameter("security_q"));
		String answer = request.getParameter("security_a");

		String sqlSecQuestion = "from SecQuestion where id = " + val;
		SecQuestion secQ = secQuestionService.listSecQuestionBySql(
				sqlSecQuestion).get(0);

		Security sec = new Security();
		sec.setUserInfo(loginUser);
		sec.setSecQuestion(secQ);
		sec.setAnswer(answer);

		securityService.saveSecurity(sec);

		List<SecQuestion> listQuestion = new ArrayList<SecQuestion>();
		listQuestion = secQuestionService
				.listSecQuestionBySql("from SecQuestion");

		request.setAttribute("listQuestion", listQuestion);

		List<Security> listSec = new ArrayList<Security>();
		String sql = "from Security where userInfo.id = " + loginUser.getId();
		if (securityService.listSecurityBySql(sql).size() > 0) {

			listSec = securityService.listSecurityBySql(sql);
		}

		request.setAttribute("listSec", listSec);

		return "../setting/security";
	}

	// 密保问题找回密码
	@RequestMapping("resetPsw.html")
	public void resetPsw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out
				.println("----------------------web/set/resetPsw---------------------------");

		request.setCharacterEncoding("utf-8"); // 这里不设置编码会有乱码
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter(); // 输出中文，这一句一定要放到response.setContentType("text/html;charset=utf-8"),
												// response.setHeader("Cache-Control",
												// "no-cache")后面，否则中文返回到页面是乱码

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		if (loginUser == null) {

			out.print("已掉线，请先登录");
		}

		long val = Long.parseLong(request.getParameter("security_q"));
		String answer = request.getParameter("security_a");
		String resetPsw = request.getParameter("reset_psw");

		String sql = "from Security where userInfo.id = " + loginUser.getId();
		Security sec = securityService.listSecurityBySql(sql).get(0);

		String sqlSecQuestion = "from SecQuestion where id = " + val;
		SecQuestion secQ = secQuestionService.listSecQuestionBySql(
				sqlSecQuestion).get(0);

		if (secQ.getId() == sec.getSecQuestion().getId()) {

			if (sec.getAnswer().equals(answer)) {

				loginUser.setPassWord(resetPsw);
				userInfoService.updateUserInfo(loginUser);
				request.getSession().invalidate();
				out.print("ok");

			} else
				out.print("密保答案与设置的不符");

		} else
			out.print("选中的密保问题与设置不符");

		out.flush();
		out.close();

	}

	// 设置头像
	@RequestMapping("setHead.html")
	public String setHead(HttpServletRequest request,
			HttpServletResponse response) {

		System.out
				.println("----------------------web/set/setHead---------------------------");

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		if (loginUser == null) {

			return "../../index";
		}

		String head = request.getParameter("syHead");
		loginUser.setHeadImg(head);

		userInfoService.updateUserInfo(loginUser);
		request.getSession().setAttribute("loginUser", loginUser);

		return "../setting/info";
	}

	// 完善个人资料
	@RequestMapping("personInfo.html")
	public void personInfo(HttpServletRequest request,
			HttpServletResponse response, String sex, String age,
			String address, String contact, String homeName, String homeDesc)
			throws Exception {

		System.out
				.println("-----------------------------personInfo--save-----------------------------------");

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		if (loginUser != null) {

			loginUser.setSex(sex);
			loginUser.setAge(age);
			loginUser.setAddress(address);
			loginUser.setContact(contact);
			loginUser.setHomeName(homeName);
			loginUser.setHomeDesc(homeDesc);

			userInfoService.updateUserInfo(loginUser);

			try {
				ResponseUtil.write(response, "保存成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ResponseUtil.write(response, "保存失败");
			}

		} else {
			ResponseUtil.write(response, "已掉线，请重新登录");
		}

	}

}
