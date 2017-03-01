package com.lz.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.lz.entity.Diary;
import com.lz.entity.UserAlbum;
import com.lz.entity.UserBlog;
import com.lz.entity.UserInfo;
import com.lz.entity.UserPhoto;
import com.lz.entity.UserQuestion;
import com.lz.entity.UserReply;
import com.lz.service.DiaryService;
import com.lz.service.UserAlbumService;
import com.lz.service.UserBlogService;
import com.lz.service.UserInfoService;
import com.lz.service.UserPhotoService;
import com.lz.service.UserQuestionService;
import com.lz.service.UserReplyService;
import com.lz.util.AuditModel;
import com.lz.util.Fenye;
import com.lz.util.HtmlSpecialChars;
import com.lz.util.Password;
import com.lz.util.getBasePath;

@Controller
@RequestMapping(value = "web/per/")
public class PersonalAction {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserBlogService userBlogService;

	@Resource
	private UserQuestionService userQuestionService;

	@Resource
	private UserReplyService userReplyService;

	@Resource
	private UserAlbumService userAlbumService;

	@Resource
	private UserPhotoService userPhotoService;

	@Resource
	private DiaryService diaryService;

	@SuppressWarnings("unchecked")
	@RequestMapping("toHomeIndex/{id}")
	public String toHomeIndex(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id, String Page)
			throws IOException {

		System.out
				.println("-----------------------toHomeIndex------------------------");
		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		if(loginUser != null){
			
			String sqlU = "from UserInfo where id = " + loginUser.getId();
			loginUser = userInfoService.listUserInfoBySql(sqlU).get(0);
		}

		String basePath = getBasePath.getBasePath(request);

		UserInfo blogUser = new UserInfo();
		String sql = "from UserInfo where id = " + id;
		if (userInfoService.listUserInfoBySql(sql).size() > 0) {

			blogUser = userInfoService.listUserInfoBySql(sql).get(0);
		}

		request.setAttribute("blogUser", blogUser);

		// 取出空间的动态
		List<AuditModel> listAudit = new ArrayList<AuditModel>();
		// 先加进blogUser的动态（说说和日志）
		Collection<UserBlog> listB = blogUser.getUserBlogs();
		Collection<Diary> listD = blogUser.getDiaries();

		for (UserBlog b : listB) {

			AuditModel a = new AuditModel();
			a.setId(b.getId());
			a.setType("blog");
			a.setContent(b.getBlogContent());
			a.setTime(b.getBlogTime());
			a.setVisit(b.getPraise());
			a.setBlogPic(b.getBlogPic());
			a.setUserInfo(blogUser);
			listAudit.add(a);
		}

		for (Diary d : listD) {

			AuditModel a = new AuditModel();
			a.setId(d.getId());
			a.setType("diary");
			a.setSubject(d.getDiarySubject());
			a.setContent(HtmlSpecialChars.htmlspecialchars2(d.getDiaryContent()));
			a.setTime(d.getDiaryTime());
			a.setVisit(Integer.parseInt(d.getDiaryVisited()));
			a.setUserInfo(blogUser);
			listAudit.add(a);
		}

		if(loginUser != null){
			
			if (loginUser.getUserInfo() != null && loginUser.getId() == blogUser.getId()) {

				UserInfo lover = loginUser.getUserInfo();

				Collection<UserBlog> listb = lover.getUserBlogs();
				Collection<Diary> listd = lover.getDiaries();

				for (UserBlog b : listb) {

					AuditModel a = new AuditModel();
					a.setId(b.getId());
					a.setType("blog");
					a.setContent(b.getBlogContent());
					a.setTime(b.getBlogTime());
					a.setVisit(b.getPraise());
					a.setBlogPic(b.getBlogPic());
					a.setUserInfo(lover);
					listAudit.add(a);
				}

				for (Diary d : listd) {

					AuditModel a = new AuditModel();
					a.setId(d.getId());
					a.setType("diary");
					a.setSubject(d.getDiarySubject());
					a.setContent(HtmlSpecialChars.htmlspecialchars2(d
							.getDiaryContent()));
					a.setTime(d.getDiaryTime());
					a.setVisit(Integer.parseInt(d.getDiaryVisited()));
					a.setUserInfo(lover);
					listAudit.add(a);
				}
			}
		}
		

		// 对动态进行时间排序
		// 时间排序
		Collections.sort(listAudit, new Comparator<AuditModel>() {
			public int compare(AuditModel o1, AuditModel o2) {
				// TODO Auto-generated method stub
				return o2.getTime().compareTo(o1.getTime());
			}

		});
		
		//分页
		List<AuditModel> item = null;
		int page = 1;
		int totalPage = listAudit.size();
		try {
			page = Integer.parseInt(Page);
		} catch (Exception e) {
			page = 1;
		}
		int pageSize = 10;
		// 最后一页
		int lastPage = Fenye.lastPage(totalPage, page, pageSize);
		if (page > lastPage) {
			page = lastPage;
		}

		request.setAttribute("lastPage", lastPage);
		request.setAttribute("page", page);

		// 当前页第一个的位置
		item = Fenye.fenye(listAudit, totalPage, page, pageSize, lastPage);
		
		request.setAttribute("listAudit",item);

		return "../home";
	}
	
	
	
	//从好友动态页面进行删除操作
	@RequestMapping("deleteAudit/{id}")
	public String deleteAudit(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id) throws IOException{
		
		System.out.println("---------------------------deleteAudit----------------------------");
		
		UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");
		
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		
		if(type.equals("blog")){
			
			String sqlBlog = "from UserBlog where id = "+id;
			UserBlog blog = userBlogService.listUserBlogBySql(sqlBlog).get(0);
			userBlogService.deleteUserBlog(blog);
			
		}else{
			
			String sqlDiary = "from Diary where id ="+id;
			Diary diary = diaryService.listDiaryBySql(sqlDiary).get(0);
			diaryService.deleteDiary(diary);
			
		}
		
		/*response.sendRedirect("toUserZone/'"+loginUser.getId()+"'?page='"+page+"'");*/
		return toHomeIndex(request,response,loginUser.getId(),page);
	}
	
	
	

	// ----------------------------------------说说-----------------------------------
	// 点击说说进入说说页面
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/blog/{id}.html")
	public String blog(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) {

		String sqlBlogUser = "from UserInfo where id = " + id;
		UserInfo blogUser = userInfoService.listUserInfoBySql(sqlBlogUser).get(
				0);
		request.setAttribute("blogUser", blogUser);

		List<UserBlog> listBlog = new ArrayList<UserBlog>();

		String sql = "from UserBlog where userInfo.id = '" + id
				+ "' order by blogTime desc";
		listBlog = userBlogService.listUserBlogBySql(sql);

		List<UserBlog> item = null;
		int page = 1;
		int totalPage = listBlog.size();
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		int pageSize = 10;
		// 最后一页
		int lastPage = Fenye.lastPage(totalPage, page, pageSize);
		if (page > lastPage) {
			page = lastPage;
		}

		request.setAttribute("lastPage", lastPage);
		request.setAttribute("page", page);

		// 当前页第一个的位置
		item = Fenye.fenye(listBlog, totalPage, page, pageSize, lastPage);
		request.setAttribute("listBlog", item);

		return "../blog";
	}

	// 发表说说
	@RequestMapping(value = "/newBlog.html", method = RequestMethod.POST)
	public String newBlog(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file)
			throws IOException {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}

		String blogContent = request.getParameter("blogContent");

		UserBlog blog = new UserBlog();
		blog.setUserInfo(loginUser);
		blog.setBlogContent(blogContent);
		blog.setBlogTime(getNowTime());
		blog.setPraise(0);

		String fileName = file.getOriginalFilename();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (fileName.length() > 0) {
			if (!type.equals("jpg") && !type.equals("bmp")
					&& !type.equals("gif") && !type.equals("png")
					&& !type.equals("jpeg")) {
				request.setAttribute("result", "图片格式错误");
				return "../error/result";
			}
		}

		String realPath1 = request.getSession().getServletContext()
				.getRealPath("web/per/Img");
		if ((new File(realPath1).isDirectory())) {
			System.out.println("文件夹已存在！创建失败！");

		} else {
			new File(realPath1).mkdir();
			System.out.println("创建文件夹成功！");

		}
		System.out.println("realPath:" + realPath1);
		if (fileName.length() > 0) {
			// 存入照片
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					realPath1, fileName));
			// 相片路径
			String realPath = realPath1 + "\\" + fileName;

			int beginIndex = realPath.lastIndexOf("web");
			realPath = realPath.substring(beginIndex, realPath.length());
			blog.setBlogPic(realPath.replace("\\", "/"));
			System.out.println("fileUrl:" + blog.getBlogPic());
		}

		userBlogService.saveUserBlog(blog);

		return "redirect:blog/" + loginUser.getId() + ".html";
	}

	// 删除说说(blog)
	@RequestMapping(value = "/deleteBlog/{id}.html")
	public String deleteBlog(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}

		String sqlUserBlog = "from UserBlog where id = " + id;
		UserBlog userBlog = userBlogService.listUserBlogBySql(sqlUserBlog).get(
				0);
		userBlogService.deleteUserBlog(userBlog);

		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}

		return "redirect:../blog/" + loginUser.getId() + ".html?page=" + page;
	}

	// 给说说点赞ajax
	@RequestMapping("good.html")
	public void good(HttpServletRequest request, HttpServletResponse response,
			int id) throws Exception {

		System.out
				.println("----------------------web/set/good---------------------------");

		request.setCharacterEncoding("utf-8"); // 这里不设置编码会有乱码
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter(); // 输出中文，这一句一定要放到response.setContentType("text/html;charset=utf-8"),
												// response.setHeader("Cache-Control",
												// "no-cache")后面，否则中文返回到页面是乱码

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		if (loginUser == null) {

			out.print("已掉线，请重新登录");
		}

		String sqlUserBlog = "from UserBlog where id = " + id;
		UserBlog userBlog = userBlogService.listUserBlogBySql(sqlUserBlog).get(
				0);

		int praise = userBlog.getPraise() + 1;
		userBlog.setPraise(praise);
		userBlogService.updateUserBlog(userBlog);
		out.print(praise);

		out.flush();
		out.close();

	}

	// ----------------------------------------留言板-----------------------------------
	@RequestMapping(value = "/newQuestion.html")
	public String newQuestion(HttpServletRequest request,
			HttpServletResponse response) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}

		long blogUserId = Integer.parseInt(request.getParameter("blogUserId"));
		String sqlUserInfo = "from UserInfo where id = " + blogUserId;
		UserInfo blogUser = userInfoService.listUserInfoBySql(sqlUserInfo).get(
				0);

		String qContent = request.getParameter("QContent");
		String qSubject = request.getParameter("QSubject");

		UserQuestion question = new UserQuestion();

		question.setQuestionContent(qContent);
		question.setQuestionSubject(qSubject);
		question.setQuestionTime(getNowTime());
		question.setUserInfoByUseId(loginUser);
		question.setUserInfoByBlogUser(blogUser);
		question.setQuestionSubject(qSubject);

		userQuestionService.saveUserQuestion(question);
		return "redirect:question/" + blogUserId + ".html";
	}

	// 列出所有的留言板内容
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/question/{id}.html")
	public String listQuestion(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id) {

		String sqlUserInfo = "from UserInfo where id = " + id;
		UserInfo blogUser = userInfoService.listUserInfoBySql(sqlUserInfo).get(
				0);
		request.setAttribute("blogUser", blogUser);

		List<UserQuestion> listQuestion = new ArrayList<UserQuestion>();
		String sql = "from UserQuestion where userInfoByBlogUser.id = '" + id
				+ "' order by questionTime desc";
		listQuestion = userQuestionService.listUserQuestionBySql(sql);
		request.setAttribute("listQuestion", listQuestion);

		// 取出全部回复
		List<List<UserReply>> listAllReply = new ArrayList<List<UserReply>>();
		for (int i = 0; i < listQuestion.size(); i++) {
			String hql = "from UserReply where userQuestion.id = '"
					+ listQuestion.get(i).getId() + "' "
					+ "order by replyTime asc";
			List<UserReply> listReply = this.userReplyService
					.listUserReplyBySql(hql);
			listAllReply.add(listReply);
		}
		request.setAttribute("listAllReply", listAllReply);

		List<UserQuestion> item = null;
		int page = 1;
		int totalPage = listQuestion.size();
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		int pageSize = 10;
		// 最后一页
		int lastPage = Fenye.lastPage(totalPage, page, pageSize);

		if (page > lastPage) {
			page = lastPage;
		}

		request.setAttribute("lastPage", lastPage);
		request.setAttribute("page", page);

		// 当前页第一个的位置
		item = Fenye.fenye(listQuestion, totalPage, page, pageSize, lastPage);
		request.setAttribute("listQuestion", item);

		return "/web/per/question";
	}

	// 回复提问者新回复添加
	@RequestMapping(value = "/newReply/{id}.html")
	public String newReply(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}

		long replyedId = Integer.parseInt(request.getParameter("replyedId"));
		long questionId = Integer.parseInt(request.getParameter("questionId"));
		String replyContent = request.getParameter("replyContent");

		String sqlUserQuestion = "from UserQuestion where id = " + questionId;
		UserQuestion question = userQuestionService.listUserQuestionBySql(
				sqlUserQuestion).get(0);

		String sqlUserInfo = "from UserInfo where id = " + replyedId;
		UserInfo userBasic = userInfoService.listUserInfoBySql(sqlUserInfo)
				.get(0);

		UserReply userReply = new UserReply();
		userReply.setReplyContent(replyContent);
		userReply.setReplyTime(getNowTime());
		userReply.setUserQuestion(question);
		userReply.setUserInfoByUserId(userBasic);
		userReply.setUserInfoByReplyid(loginUser);
		userReply.setIsRead("0");

		userReplyService.saveUserReply(userReply);

		return "redirect:/web/per/question/" + id + ".html";
	}

	// -------------------------------------相册-------------------------------------------
	@RequestMapping(value = "/uploadImg.html")
	public String uploadImg(HttpServletRequest request,
			HttpServletResponse response) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}

		request.setAttribute("blogUser", loginUser);

		String sql = "from UserAlbum where userInfo.id='" + loginUser.getId()
				+ "'";
		List<UserAlbum> listAlbum = userAlbumService.listUserAlbumBySql(sql);
		request.setAttribute("listAlbum", listAlbum);

		return "/web/per/uploadImg";
	}

	@RequestMapping(value = "/creat.html")
	public String creatAlbum(HttpServletRequest request,
			HttpServletResponse response) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}

		String creatAlbum = request.getParameter("album_creatName");
		String albumDesc = request.getParameter("album_desc");
		String isPwd = request.getParameter("album_isPwd");
		String password = request.getParameter("album_pwd");

		String sqlUserPhoto = "from UserPhoto where id = " + 1;
		UserPhoto userPhoto = userPhotoService.listUserPhotoBySql(sqlUserPhoto)
				.get(0);

		UserAlbum userAlbum = new UserAlbum();
		userAlbum.setUserInfo(loginUser);
		userAlbum.setAlbumName(creatAlbum);
		userAlbum.setAlbumTime(getNowTime());
		userAlbum.setUserPhoto(userPhoto);
		userAlbum.setAlbumDesc(albumDesc);

		if (isPwd.equals("1") && password.trim() != null) {
			userAlbum.setAlbumPsw(Password.createPassword(password.trim()));

		}

		userAlbumService.saveUserAlbum(userAlbum);

		return "redirect:/web/per/uploadImg.html";
	}

	// 编辑相册权限设置
	@RequestMapping(value = "/editAlbum.html")
	public String editAlbum(HttpServletRequest request,
			HttpServletResponse response) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}

		long albumId = Integer.parseInt(request.getParameter("hidden_album"));
		int page = Integer.parseInt(request.getParameter("page"));
		String albumName = request.getParameter("album_creatName");
		String oldPsw = request.getParameter("oldPsw");
		String newPsw = request.getParameter("newPsw");
		String album_isPwd = request.getParameter("album_isPwd");
		String password = request.getParameter("album_pwd");
		String albumDesc = request.getParameter("album_desc");

		String sqlUserAlbum = "from UserAlbum where id = " + albumId;
		UserAlbum album = userAlbumService.listUserAlbumBySql(sqlUserAlbum)
				.get(0);
		album.setAlbumName(albumName);
		album.setAlbumName(albumName);
		album.setAlbumDesc(albumDesc);

		if (album.getAlbumPsw() == null) {

			if (album_isPwd.equals("1") && password.trim() != null) {
				album.setAlbumPsw(Password.createPassword(password.trim()));

			}

		} else {
			if (oldPsw.trim() == null) {
				request.setAttribute("result", "请输入原始密码");
				return "/web/error/result";

			} else {
				if (album.getAlbumPsw().equals(
						Password.createPassword(oldPsw.trim()))) {
					if (newPsw.trim() == null || newPsw.trim().length() == 0) {
						System.out
								.println("==========================old================="
										+ Password.createPassword(oldPsw.trim()));
						album.setAlbumPsw(null);
					} else {
						System.out
								.println("==1"
										+ newPsw
										+ "2==========================new================="
										+ Password.createPassword(newPsw.trim()));
						album.setAlbumPsw(Password.createPassword(newPsw.trim()));

					}
				} else {
					request.setAttribute("result", "原始密码输入错误");
					return "../error/result";

				}
			}
		}

		userAlbumService.updateUserAlbum(album);

		return "redirect:listAlbum.do?id=" + loginUser.getId() + "&page="
				+ page;
	}

	@RequestMapping(value = "/uploadImgFile.html")
	public String uploadImgFile(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file)
			throws IOException {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}
		request.setAttribute("blogUser",loginUser);

		/* try { */

		UserAlbum userAlbum = new UserAlbum();
		UserPhoto userPhoto = new UserPhoto();

		// 获取当期时间，作为文件名，避免重复
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fileTime = sdf.format(new Date());

		String fileName = file.getOriginalFilename();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (fileName.length() > 0) {
			if (!type.equals("jpg") && !type.equals("bmp")
					&& !type.equals("gif") && !type.equals("png")
					&& !type.equals("jpeg")) {
				request.setAttribute("result", "图片格式错误");
				return "../error/result";
			}
		}

		String albumName = request.getParameter("selectAlbum");

		if (albumName.equals("please")) {
			request.setAttribute("result", "请选择要上传的相册");
			return "/web/error/result";
		} else {
			String sql = "from UserAlbum where userInfo.id = '"
					+ loginUser.getId() + "' and albumName = '" + albumName
					+ "'";
			userAlbum = userAlbumService.listUserAlbumBySql(sql).get(0);
		}

		// 上传的文件放在“realPath”目录下
		String realPath1 = request.getSession().getServletContext()
				.getRealPath("web/album/" + fileTime);
		if ((new File(realPath1).isDirectory())) {
			System.out.println("文件夹已存在！创建失败！");

		} else {
			new File(realPath1).mkdir();
			System.out.println("创建文件夹成功！");

		}

		request.setAttribute("userAlbum", userAlbum);
		System.out.println("realPath:" + realPath1);

		if (fileName.length() > 0) {
			// 存入照片
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					realPath1, fileName));
			// 相片路径
			String realPath = realPath1 + "\\" + fileName;

			System.out.println("========================================");

			// 将文件名存入数据库
			// realPath=realPath+"\\"+fileName;
			int beginIndex = realPath.lastIndexOf("web");
			realPath = realPath.substring(beginIndex, realPath.length());
			userPhoto.setPhoto(realPath.replace("\\", "/"));
			userPhoto.setPhotoName(fileName);
			System.out.println("fileName:" + userPhoto.getPhotoName());
			System.out.println("========================================");

			String userPhotoDesc = request.getParameter("photo_desc");
			userPhoto.setUserAlbum(userAlbum);
			userPhoto.setPhotoTime(getNowTime());
			userPhoto.setPhotoDesc(userPhotoDesc);

			userPhotoService.saveUserPhoto(userPhoto);
			if (userAlbum.getUserPhoto().getId() == 1) {
				userAlbum.setUserPhoto(userPhoto);
				userAlbumService.updateUserAlbum(userAlbum);

			}

			request.setAttribute("result", "上传相片成功");
			/*return "redirect:http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/web/per/result.jsp";*/
			return "../per/result";
		} else {
			request.setAttribute("result", "请选择要上传的相片");
			return "../error/result";
		}
	}

	@RequestMapping(value = "listAlbum.do", method = RequestMethod.GET)
	public String listAlbum(HttpServletRequest request,
			HttpServletResponse response, long id) throws ServletException,
			IOException {
		// id对应的user
		String sqlUserInfo = "from UserInfo where id = " + id;
		UserInfo blogUser = userInfoService.listUserInfoBySql(sqlUserInfo).get(
				0);

		try {

			String sql = "from UserAlbum where userInfo.id = '" + id + "'";
			List<UserAlbum> listUserAlbum = userAlbumService
					.listUserAlbumBySql(sql);
			List<UserAlbum> albumdt = null;
			if (listUserAlbum.size() < 1) {
				request.setAttribute("blogUser", blogUser);
				return "/web/per/personAlbum";
			}

			int totalPage = listUserAlbum.size();

			// 分页
			int page = 1;

			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {

				page = 1;
			}
			int pageSize = 12;
			// 最后一页
			int lastPage = 1;
			if (totalPage % pageSize == 0) {
				lastPage = totalPage / pageSize;
			} else
				lastPage = totalPage / pageSize + 1;

			if (page > lastPage) {
				page = lastPage;
			}
			if (page <= 0) {
				page = 1;
			}
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("page", page);

			// 当前页第一个的位置
			int firstSet = (page - 1) * pageSize;
			// 下方排序
			if (listUserAlbum.size() > pageSize) {
				if ((firstSet + pageSize) < (totalPage - 1)) {
					albumdt = listUserAlbum.subList(firstSet, firstSet
							+ pageSize);
				} else {
					albumdt = listUserAlbum.subList(firstSet, totalPage);
				}
			} else {
				albumdt = listUserAlbum;
			}
			request.setAttribute("albumdt", albumdt);
			request.setAttribute("blogUser", blogUser);

			return "/web/per/personAlbum";
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("value", "没有相册");
			return "/web/per/personAlbum";
			// return "/web/error/error";
		}
	}

	// 删除相册
	@RequestMapping(value = "deleteAlbum.do")
	public String deleteAlbum(HttpServletRequest req, HttpServletResponse res,
			long id, int page, long blId) {

		// 判断是否登录
		UserInfo loginUser = (UserInfo) req.getSession().getAttribute(
				"loginUser");
		if (loginUser == null) {
			req.setAttribute("result", "还没登录，请先登录");
			return "../../../index";
		}

		String sqlUserInfo = "from UserInfo where id = " + blId;
		UserInfo blogUser = userInfoService.listUserInfoBySql(sqlUserInfo).get(
				0);
		req.setAttribute("blogUser", blogUser);

		try {

			String sqlUserAlbum = "from UserAlbum where id = " + id;
			UserAlbum album = userAlbumService.listUserAlbumBySql(sqlUserAlbum)
					.get(0);

			album.setUserPhoto(null);
			userAlbumService.updateUserAlbum(album);
			String sql = "from UserPhoto where userAlbum.id = '" + id + "'";
			List<UserPhoto> listPhoto = userPhotoService
					.listUserPhotoBySql(sql);
			for (UserPhoto photo : listPhoto) {
				userPhotoService.deleteUserPhoto(photo);
			}
			UserInfo user = album.getUserInfo();
			userAlbumService.deleteUserAlbum(album);

			return "redirect:listAlbum.do?id=" + user.getId();
		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("result", "没有找到相册");
			return "/web/error/result";
			// return "/web/error/error";
		}

	}

	@RequestMapping(value = "permission.do", method = RequestMethod.GET)
	public String Permission(HttpServletRequest request,
			HttpServletResponse response, long id, long blId)
			throws IOException, Exception {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String sqlUserInfo = "from UserInfo where id = " + blId;
		UserInfo blogUser = userInfoService.listUserInfoBySql(sqlUserInfo).get(
				0);

		request.setAttribute("blogUser", blogUser);

		// 分页
		int page = 1;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {

			page = 1;
		}

		String sql = "from UserPhoto where userAlbum.id = '" + id + "'";

		String sqlUserAlbum = "from UserAlbum where id = " + id;
		UserAlbum album = userAlbumService.listUserAlbumBySql(sqlUserAlbum)
				.get(0);
		// 比较是不是本人
		/*
		 * if (userPhotoService.listUserPhotoBySql(sql).size() > 0) {
		 * 
		 * } else { request.setAttribute("value", "没有相片"); return
		 * "/web/personal/personPhoto"; }
		 */
		if (album.getAlbumPsw() != null) {
			if (loginUser != null) {
				if (loginUser.getId() == blogUser.getId()) {
					return "redirect:listAlbumPhoto.html?id=" + id + "&page="
							+ page + "&blId=" + blId;
				} else {
					// 返回输密码页面
					request.setAttribute("clickPass", "1");
					request.setAttribute("page", page);
					request.setAttribute("album", album);
					return "/web/per/personPhoto";

				}
			} else {
				// 返回输密码页面
				request.setAttribute("clickPass", "1");
				request.setAttribute("page", page);
				request.setAttribute("album", album);
				return "/web/per/personPhoto";

			}
		} else {
			return "redirect:listAlbumPhoto.html?id=" + id + "&page=" + page
					+ "&blId=" + blId;
		}

	}

	// 判断查看资质相片用户的输入密码是否正确
	@RequestMapping(value = "/confirmPass.html")
	public String confirmPass(HttpServletRequest request,
			HttpServletResponse response) {

		String clickPass = request.getParameter("clickPass");
		String page = request.getParameter("page");
		String blId = request.getParameter("blId");
		String id = request.getParameter("album");

		// 当前查看的相册
		String sqlUserAlbum = "from UserAlbum where id = "
				+ Integer.valueOf(id);
		UserAlbum album = userAlbumService.listUserAlbumBySql(sqlUserAlbum)
				.get(0);

		if (album.getAlbumPsw().equals(Password.createPassword(clickPass))) {

			return "redirect:listAlbumPhoto.html?id=" + id + "&page=" + page
					+ "&blId=" + blId;
		}

		request.setAttribute("result", "您输入的密码错误");
		return "/web/error/result";
	}

	@RequestMapping(value = "listAlbumPhoto.html", method = RequestMethod.GET)
	public String listAlbumPhoto(HttpServletRequest request,
			HttpServletResponse response, long id, long blId) throws Exception,
			IOException {

		try {

			String clickPass = (String) request.getAttribute("clickPass");
			request.setAttribute("clickPass", clickPass);
			// id对应的user
			String sqlUserInfo = "from UserInfo where id = " + blId;
			UserInfo blogUser = userInfoService.listUserInfoBySql(sqlUserInfo)
					.get(0);
			request.setAttribute("blogUser", blogUser);

			String sqlUserAlbum = "from UserAlbum where id = " + id;
			UserAlbum album = userAlbumService.listUserAlbumBySql(sqlUserAlbum)
					.get(0);
			List<UserPhoto> listPhoto = new ArrayList<UserPhoto>();

			String sql = "from UserPhoto where userAlbum.id = '" + id + "'";
			listPhoto = userPhotoService.listUserPhotoBySql(sql);

			List<UserPhoto> photodt = null;

			int totalPage = listPhoto.size();

			// 分页
			int page = 1;

			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {

				page = 1;
			}
			int pageSize = 12;
			// 最后一页
			int lastPage = 1;
			if (totalPage % pageSize == 0) {
				lastPage = totalPage / pageSize;
			} else
				lastPage = totalPage / pageSize + 1;

			if (page > lastPage) {
				page = lastPage;
			}
			if (page <= 0) {
				page = 1;
			}
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("page", page);

			// 当前页第一个的位置
			int firstSet = (page - 1) * pageSize;
			// 下方排序
			if (listPhoto.size() > pageSize) {
				if ((firstSet + pageSize) < (totalPage - 1)) {
					photodt = listPhoto.subList(firstSet, firstSet + pageSize);
				} else {
					photodt = listPhoto.subList(firstSet, totalPage);
				}
			} else {
				photodt = listPhoto;
			}

			request.setAttribute("album", album);
			request.setAttribute("photodt", photodt);

			return "/web/per/personPhoto";
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("value", "没有相片");
			return "/web/per/personPhoto";
		}

	}

	@RequestMapping(value = "photo.do", method = RequestMethod.GET)
	public String photo(HttpServletRequest req, HttpServletResponse res,
			long id, long blId) {

		String sqlUserInfo = "from UserInfo where id = " + blId;
		UserInfo blogUser = userInfoService.listUserInfoBySql(sqlUserInfo).get(
				0);

		String sqlUserPhoto = "from UserPhoto where id = " + id;
		UserPhoto userPhoto = userPhotoService.listUserPhotoBySql(sqlUserPhoto)
				.get(0);
		req.setAttribute("userPhoto", userPhoto);
		req.setAttribute("blogUser", blogUser);

		return "/web/per/photo";

	}

	// 删除相片
	@RequestMapping(value = "deletePhoto.do")
	public String deletePhoto(HttpServletRequest req, HttpServletResponse res,
			long id, int page, long blId) throws Exception, IOException {
		try {
			// 判断是否登录
			UserInfo loginUser = (UserInfo) req.getSession().getAttribute(
					"loginUser");
			if (loginUser == null) {
				req.setAttribute("result", "已掉线，请重新的登录");
				return "../../../index";
			}
			String sqlUserPhoto = "from UserPhoto where id = " + id;
			UserPhoto photo = userPhotoService.listUserPhotoBySql(sqlUserPhoto)
					.get(0);
			UserAlbum album = photo.getUserAlbum();
			req.setAttribute("album", album);
			// 判断该相册是否还有照片
			String sql = "from UserPhoto where userAlbum.id = '" + album.getId() + "'";
			List<UserPhoto> listPhoto = userPhotoService
					.listUserPhotoBySql(sql);
			if (listPhoto.size() > 1) {
				userPhotoService.deleteUserPhoto(photo);
			} else {
				UserPhoto cover = userPhotoService.listUserPhotoBySql(
						"from UserPhoto where id = " + 1).get(0);
				album.setUserPhoto(cover);
				userAlbumService.updateUserAlbum(album);

				userPhotoService.deleteUserPhoto(listPhoto.get(0));
				return "redirect:listAlbum.do?id=" + loginUser.getId()
						+ "&page=" + page;

			}
			return "redirect:listAlbumPhoto.html?&id=" + album.getId()
					+ "&blId=" + blId + "&page=" + page;

		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("result", "该相片已作为相册封面，只能最后删除");
			return "/web/error/result";
			// return "/web/error/error";
		}

	}

	// 设置相片为相册的封面
	@RequestMapping(value = "photoAlbum.do")
	public String photoAlbum(HttpServletRequest req, HttpServletResponse res,
			long photoId) {

		String sqlUserPhoto = "from UserPhoto where id = " + photoId;
		UserPhoto userPhoto = userPhotoService.listUserPhotoBySql(sqlUserPhoto)
				.get(0);
		UserAlbum userAlbum = userPhoto.getUserAlbum();

		userAlbum.setUserPhoto(userPhoto);
		userAlbumService.updateUserAlbum(userAlbum);

		return "redirect:listAlbum.do?id=" + userAlbum.getUserInfo().getId();
	}

	// -------------------------------------日志-------------------------------------------
	// 进入个人空间的日记
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/diary/{id}.html")
	public String listDiary(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id) {

		String sqlUserInfo = "from UserInfo where id = " + id;
		UserInfo blogUser = userInfoService.listUserInfoBySql(sqlUserInfo).get(
				0);
		request.setAttribute("blogUser", blogUser);

		List<Diary> listDiary = new ArrayList<Diary>();
		String sql = "from Diary where userInfo.id = '" + id
				+ "' order by diaryTime desc";
		listDiary = diaryService.listDiaryBySql(sql);

		for (Diary diary : listDiary) {
			diary.setDiaryContent(HtmlSpecialChars.htmlspecialchars2(diary
					.getDiaryContent()));
		}

		List<UserBlog> item = null;
		int page = 1;
		int totalPage = listDiary.size();
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		int pageSize = 10;
		// 最后一页
		int lastPage = Fenye.lastPage(totalPage, page, pageSize);

		if (page > lastPage) {
			page = lastPage;
		}

		request.setAttribute("lastPage", lastPage);
		request.setAttribute("page", page);

		// 当前页第一个的位置
		item = Fenye.fenye(listDiary, totalPage, page, pageSize, lastPage);
		request.setAttribute("listDiary", item);

		return "/web/per/personDiary";
	}

	// 发表日记
	@RequestMapping(value = "/addDiary.html")
	public String addDiary(HttpServletRequest request,
			HttpServletResponse response) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "../../../index";

		}

		String diarySub = request.getParameter("diarySub");
		String diaryContent = request.getParameter("diaryContent");

		Diary diary = new Diary();

		diary.setDiarySubject(diarySub);
		diary.setDiaryContent(diaryContent);
		diary.setDiaryTime(getNowTime());
		diary.setUserInfo(loginUser);
		diary.setDiaryVisited("0");

		diaryService.saveDiary(diary);

		return "redirect:diary/" + loginUser.getId() + ".html";
	}

	// 日记详情
	@RequestMapping(value = "/detailDiary/{id}.html")
	public String detailDiary(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id) {

		String sqlDiary = "from Diary where id = " + id;
		Diary diary = diaryService.listDiaryBySql(sqlDiary).get(0);

		int vis = Integer.parseInt(diary.getDiaryVisited()) + 1;
		diary.setDiaryVisited(vis + "");
		diaryService.updateDiary(diary);

		UserInfo blogUser = diary.getUserInfo();

		request.setAttribute("diary", diary);
		request.setAttribute("blogUser", blogUser);

		return "/web/per/detailDiary";
	}

	// 删除日记
	@RequestMapping(value = "/deleteDiary/{id}.html")
	public String deleteDiary(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id) {

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		// 判断是否登录
		if (loginUser == null) {
			request.setAttribute("result", "还没登录，请先登录");
			return "/index/index";

		}

		String sqlDiary = "from Diary where id = " + id;
		Diary diary = diaryService.listDiaryBySql(sqlDiary).get(0);
		diaryService.deleteDiary(diary);

		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}

		return "redirect:../diary/" + loginUser.getId() + ".html?page=" + page;
	}

	// -----------------------------------end---------------------------------------------
	// 服务器时间
	public static String getNowTime() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = f.format(date);

		return nowTime;
	}
}
