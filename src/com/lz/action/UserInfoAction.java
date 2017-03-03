package com.lz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lz.entity.LoverApply;
import com.lz.entity.UserInfo;
import com.lz.service.LoverApplyService;
import com.lz.service.UserInfoService;
import com.lz.util.Mail;
import com.lz.util.ResponseUtil;

@Controller
@RequestMapping(value = "com/")
public class UserInfoAction {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private LoverApplyService loverApplyService;

	private String url_result = "../web/error/result";

	@RequestMapping("test.html")
	public String test(HttpServletRequest request, HttpServletResponse response) {

		List<UserInfo> listUser = new ArrayList<UserInfo>();
		String sql = "from UserInfo";
		listUser = userInfoService.listUserInfoBySql(sql);
		request.setAttribute("list", listUser);
		System.out.println("==========================list:" + listUser
				+ "=====size:" + listUser.size());

		return "index";
	}

	// ajax检测该用户是否已经注册
	@RequestMapping("checkUserName")
	public void checkUserName(HttpServletRequest request,
			HttpServletResponse response, String userName) throws Exception {

		userName = URLDecoder.decode(userName, "UTF-8");
		System.out
				.println("--------------------checkUserName-----------------------"
						+ userName);
		String sql = "from UserInfo where userName = '" + userName + "'";

		if (userInfoService.listUserInfoBySql(sql).size() > 0) {

			ResponseUtil.write(response, "error");
		} else
			ResponseUtil.write(response, "ok");

	}

	// 公司注册
	@RequestMapping(value = "register.html")
	public void register(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out
				.println("------------------------------register------------------------------");

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String email = request.getParameter("email");
		String headImage = "img/headImg/noHead.jpg";

		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setPassWord(passWord);
		userInfo.setEmail(email);
		userInfo.setHeadImg(headImage);
		userInfoService.saveUserInfo(userInfo);

		ResponseUtil.write(response, "注册成功");
	}

	// 登录
	@RequestMapping(value = "login.html")
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		System.out
				.println("------------------------------login------------------------------");

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");

		String sql1 = null;
		String sql2 = null;

		sql1 = "from UserInfo where userName = '" + userName + "'";
		sql2 = "from UserInfo where userName = '" + userName
				+ "' and passWord = '" + passWord + "'";

		List<UserInfo> list1 = userInfoService.listUserInfoBySql(sql1);
		List<UserInfo> list2 = userInfoService.listUserInfoBySql(sql2);

		if (list1.size() <= 0) {

			request.setAttribute("result", "用户名输入错误，请重新登录");
			return url_result;
		} else if (list2.size() <= 0) {

			request.setAttribute("result", "用户名和密码不匹配，请重新登录");
			return url_result;
		}

		UserInfo userInfo = list2.get(0);

		request.getSession().setAttribute("loginUser", userInfo);

		response.sendRedirect("../web/per/toHomeIndex/" + userInfo.getId());
		return null;
	}

	// 公司注销
	@RequestMapping(value = "logout.html")
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		System.out
				.println("------------------------------exit------------------------------");
		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		request.getSession().invalidate();

		response.sendRedirect("../web/com/login.jsp");
	}

	// 申请绑定关系
	@RequestMapping("beLoverApply.html")
	public void beLover(HttpServletRequest request,
			HttpServletResponse response, int id) throws Exception {

		System.out
				.println("--------------------beLoverApply-----------------------");
		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {

			ResponseUtil.write(response, "no_login");
		} else {

			String sqlUserInfo = "from UserInfo where id = " + id;
			UserInfo lover = userInfoService.listUserInfoBySql(sqlUserInfo)
					.get(0);
			LoverApply loverApply = new LoverApply();
			loverApply.setUserInfoByApplyId(loginUser);
			loverApply.setUserInfoByLoverId(lover);
			loverApply.setStatue("0");
			loverApplyService.saveLoverApply(loverApply);
			ResponseUtil.write(response, "ok");
		}
	}

	// 解除情侣关系
	@RequestMapping("relieve.html")
	public void relieve(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out
				.println("--------------------relieve-----------------------");
		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {

			ResponseUtil.write(response, "no_login");
		} else {

			UserInfo loverUser = loginUser.getUserInfo();
			loginUser.setUserInfo(null);
			loverUser.setUserInfo(null);
			userInfoService.updateUserInfo(loginUser);
			userInfoService.updateUserInfo(loverUser);
			request.getSession().setAttribute("loginUser", loginUser);
			ResponseUtil.write(response, "ok");
		}

	}

	// 同意申请绑定
	@RequestMapping("applyOK.html")
	public void applyOK(HttpServletRequest request,
			HttpServletResponse response, int id) throws Exception {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {

			ResponseUtil.write(response, "no_login");
		} else if (loginUser.getUserInfo() == null) {

			String sqlLover = "from LoverApply where id = " + id;
			LoverApply loverApply = loverApplyService.listLoverApplyBySql(
					sqlLover).get(0);
			UserInfo apply = loverApply.getUserInfoByApplyId();
			loginUser.setUserInfo(apply);
			apply.setUserInfo(loginUser);
			userInfoService.updateUserInfo(loginUser);
			userInfoService.updateUserInfo(apply);

			// 处理剩下的申请
			String sql = "from LoverApply where statue = 0 and (userInfoByLoverId = '"
					+ loginUser.getId()
					+ "' "
					+ "or (userInfoByLoverId = '"
					+ apply.getId()
					+ "' and userInfoByApplyId = '"
					+ loginUser.getId() + "' ) ) ";
			List<LoverApply> list = loverApplyService.listLoverApplyBySql(sql);
			for (LoverApply l : list) {

				l.setStatue("1");
				loverApplyService.updateLoverApply(l);
			}

			ResponseUtil.write(response, "ok");

		} else {

			ResponseUtil.write(response, "noMore");
		}
	}

	// 同意申请绑定
	@RequestMapping("apply_no.html")
	public void apply_no(HttpServletRequest request,
			HttpServletResponse response, int id) throws Exception {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {

			ResponseUtil.write(response, "no_login");
		} else {

			String sqlLover = "from LoverApply where id = " + id;
			LoverApply loverApply = loverApplyService.listLoverApplyBySql(
					sqlLover).get(0);
			UserInfo apply = loverApply.getUserInfoByApplyId();

			// 处理申请
			String sql = "from LoverApply where statue = 0 and (userInfoByLoverId = '"
					+ loginUser.getId()
					+ "' "
					+ "and userInfoByLoverId = '"
					+ loginUser.getId() + "') ";
			List<LoverApply> list = loverApplyService.listLoverApplyBySql(sql);
			for (LoverApply l : list) {

				l.setStatue("1");
				loverApplyService.updateLoverApply(l);
			}

			ResponseUtil.write(response, "ok");
		}

	}

	// 给用户发邮件用于找回密码
	@RequestMapping("forUser.html")
	public void forUser(HttpServletRequest request,
			HttpServletResponse response, String userName) throws IOException {

		System.out
				.println("------------------------forUser.html--------------------------");

		request.setCharacterEncoding("utf-8"); // 这里不设置编码会有乱码
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter(); // 输出中文，这一句一定要放到response.setContentType("text/html;charset=utf-8"),
												// response.setHeader("Cache-Control",
												// "no-cache")后面，否则中文返回到页面是乱码

		String basePath = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称

		UserInfo user = new UserInfo();

		String sql = "from UserInfo where userName = '" + userName + "'";
		if (userInfoService.listUserInfoBySql(sql).size() > 0) {

			user = userInfoService.listUserInfoBySql(sql).get(0);

			int n = 0;
			for (int i = 0; i < 3; i++) {
				Boolean value = mail(basePath, user.getEmail(), userName);
				if (!value) {

					i--;
					n++;
					System.out.println("------------发邮件失败第" + (i++) + "次");
				} else {

					out.print("邮件发送成功，请注意查收");
					break;
				}

			}

			if (n >= 3) {
				out.print("邮件发送失败");
			}

		} else
			out.print("不存在该用户，请重新输入");

		out.flush();
		out.close();

	}

	// 用户收到邮件点击链接跳转方法
	@RequestMapping("userPsw.html")
	public String userPsw(HttpServletRequest request,
			HttpServletResponse response, String userName) {

		UserInfo user = new UserInfo();

		String sql = "from UserInfo where userName = '" + userName + "'";
		if (userInfoService.listUserInfoBySql(sql).size() > 0) {

			user = userInfoService.listUserInfoBySql(sql).get(0);
		}

		request.setAttribute("user", user);

		return "../setPsw";
	}

	// 用户收到邮件点击链接跳转方法
	@RequestMapping("setPsw.html")
	public void setPsw(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		request.setCharacterEncoding("utf-8"); // 这里不设置编码会有乱码
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter(); // 输出中文，这一句一定要放到response.setContentType("text/html;charset=utf-8"),
												// response.setHeader("Cache-Control",
												// "no-cache")后面，否则中文返回到页面是乱码

		UserInfo user = new UserInfo();

		int id = Integer.parseInt(request.getParameter("id"));

		String sqlUser = "from UserInfo where id = "+id;
		user = userInfoService.listUserInfoBySql(sqlUser).get(0);

		String psw = request.getParameter("psw");
		user.setPassWord(psw);
		userInfoService.updateUserInfo(user);

		out.print("ok");

		out.flush();
		out.close();
	}

	// 专门用来测试邮件发送功能
	public boolean mail(String basePath, String mailTo, String name) throws UnsupportedEncodingException {
		String esmtp = "smtp.sina.cn"; // smtp.qq.com,smtp.sina.cn
		String efrom = "13387624698@sina.cn";
		String eto = mailTo; // userInfo.getE_mail()
		String ecopyto = ""; // 抄送人
		String esubject = "找回密码";// 邮件主题
		String url = basePath + "/com/userPsw.html?userName=" + java.net.URLEncoder.encode(name,"utf-8") + "";
		String econtent = getMailContent(name, url); // 邮件内容
		String eusername = "13387624698@sina.cn";
		String epassword = "";
		String efilename = ""; // 附件路径，如：F:\\笔记<a>\\struts2</a>与mvc.txt
		boolean emailresult = Mail.send(esmtp, efrom, eto, esubject, econtent,
				eusername, epassword);
		return emailresult;

	}

	public String getMailContent(String name, String url) {
		String ContentModel = "<table width='750' border='0' align='center' cellpadding='0' cellspacing='0' style='color:#000000;font-family:lucida Grande, Verdana, Microsoft YaHei;font-size:13.63636302948px;margin:0px auto;background-color:#FFFFFF;' class='ke-zeroborder'>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>"
				+ "<table width='750' border='0' cellspacing='0' cellpadding='0' class='ke-zeroborder'>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td colspan='3' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>"
				+ "<table width='750' border='0' cellspacing='0' cellpadding='0' class='ke-zeroborder'>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>"
				+ "<br />"
				+ "</td>"
				+ "<td width='550' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>&nbsp;"
				+

				"</td>"
				+ "</tr></tbody></table></td>	</tr><tr>"
				+ "<td colspan='3' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>	<table width='750' border='0' cellspacing='0' cellpadding='0' class='ke-zeroborder'>									<tbody>										<tr>											<td width='40' height='90' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>												&nbsp;											</td>											<td style='font-family:微软雅黑, 宋体;font-size:20px;font-weight:700;color:#816439;text-align:center;'>"
				+ "找回密码通知											</td>											<td width='40' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>"
				+ "	&nbsp;											</td>										</tr>									</tbody>"
				+ "	</table>							</td>						</tr>						<tr>							<td width='40' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>								&nbsp;							</td>							<td width='670' height='100' valign='top' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>								<table width='670' border='0' cellspacing='0' cellpadding='0' class='ke-zeroborder'>									<tbody>										<tr>											<td style='font-family:Arial, 宋体, Helvetica, sans-serif;font-size:14px;font-weight:700;color:#444040;'>"
				+ "		亲爱的用户：											</td>										</tr>										<tr>											<td style='font-family:Arial, 宋体, Helvetica, sans-serif;font-size:14px;color:#444040;'>"
				+ "			"
				+ name
				+ "您好，您申请了邮箱找回密码服务，请点击下方的链接进行操作（若非本人操作，不必操作）:<br />"
				+ "<a href='"+url+"' target='_blank'>"
				+ "（请点击此链接，跳转到修改密码页面进行相关操作。）"
				+ "</a><br />"
				+ "(如果点击无效，请您将邮件地址复制到浏览器地址栏中打开。)"
				+ "</td>"
				+ "										</tr>"
				+ "										<tr>"
				+ "											<td style='font-family:lucida Grande, Verdana, Microsoft YaHei;border:1px solid #FDEEB8;color:#D28E5C;text-align:center;background-color:#FBF6E4;'>"
				+ "												以上是您的重要个人信息。为了您的帐号安全，请妥善保管，切忌泄露给其他人。"
				+ "											</td>"
				+ "										</tr>"
				+ "										<tr>"
				+ "											<td valign='middle' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>"
				+ "											   <p style='color:#444040;'>"
				+ "													<a target='_blank'>"
				+ "</a> "
				+ "												</p>"
				+ "                                              <p style='color:#444040;'>"
				+ "													<a target='_blank'>"
				+ "</a> "
				+ "												</p>"
				+ "                                              <p style='color:#444040;'>"
				+ "													<a target='_blank'>"
				+ "</a> "
				+ "												</p>"
				+ "												<p style='color:#444040;'>"
				+ "													"
				+ ""
				+ "												</p>"
				+ "												<p style='color:#444040;'>"
				+ "													"
				+ "												</p>"
				+ "												<p style='color:#444040;'>"
				+ "													<br />"
				+ "												</p>"
				+ "		<p style='color:#847D7D;'>"
				+ "		竭诚为您服务，提高用户满意度是我们最大的目标，在此感谢各位用户对我们的支持和配合！！"
				+ "		</p>"
				+ "		<p style='text-align:right;color:#FF0000;'>"
				+ "		</p>"
				+ "		<p style='text-align:right;color:#FF0000;'>"
				+ "													<span>"
				+ getNowTime()
				+ "</span> "
				+ "												</p>"
				+ "											</td>"
				+ "										</tr>"
				+ "										<tr>"
				+ "											<td style='font-family:lucida Grande, Verdana, Microsoft YaHei;border:1px solid #FDEEB8;color:#D28E5C;text-align:center;background-color:#FBF6E4;'>"
				+ "												此邮件为系统自动发出，请勿直接回复"
				+ "												</td>"
				+ "										</tr>"
				+ "									</tbody>"
				+ "								</table>"
				+ "							</td>"
				+ "							<td width='40' style='font-family:lucida Grande, Verdana, Microsoft YaHei;'>"
				+ "								&nbsp;"
				+ "							</td>			"
				+ "			</tr>"
				+ "					</tbody>"
				+ "				</table>"
				+ "			</td>"
				+ "		</tr>"
				+ "	</tbody>" + "</table>";

		return ContentModel;

	}

	// -----------------------------------end---------------------------------------------
	// 服务器时间
	public static String getNowTime() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = f.format(date);

		return nowTime;
	}

	public static String getRootPath(HttpServletRequest request) {

		String path = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();

		return path;
	}

}
