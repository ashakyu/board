package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;

public class ControllerAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//명령어와 명령어 처리 클래스를 쌍으로 저장하는 MAP기능
	private Map<String, Object> commandMap=
			new HashMap<String, Object>();
	
	/*
	 * 명령어와 처리 클래스가 맵핑되어 있는 properties 파일(CommandPro.properties)를 읽어
	 * Map 객체인 CommandMap에 저장한다.
	 */
	
	//web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어온다.
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("propertyConfig");
		
		//명령어와 처리클래스의 맵핑 정보를 저장할 Properties 객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			f = new FileInputStream(new File(path,props));
			//Command.properties 파일의 정보를 Properties 객체에 저장.
			pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if(f!=null)try {f.close();}catch(IOException e) {}
		}
		//Iterator 객체 사용
		Iterator<Object> keyIter = pr.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try {
				//가져온 문자열을 클래스로 생성
				Class<?> commandClass = Class.forName(className);
				
				//만들어진 해당 클래스의 객체 생성
				Object commandInstance = commandClass.newInstance();
				
				//생성된 객체를 CommandMap에 저장
				commandMap.put(command, commandInstance);
			}catch(ClassNotFoundException e) {
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}
	
	//Get방식 서비스 메서드
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		requestPro(request, response);
	}
	
	//Post방식 서비스 메서드 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		requestPro(request, response);
	}
	
	//사용자의 요청에 따라 분석하여 해당 작업을 처리
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String view = null;
		
		//action 패키지의 CommandAction을 import, 따라서 해당 파일이 없으면 오류
		CommandAction com =null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0) {
				command = command.substring(
					request.getContextPath().length());
				}
				com = (CommandAction) commandMap.get(command);  
				view = com.requestPro(request, response);
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
