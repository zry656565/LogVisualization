package com.LogVisualization.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.LogVisualization.DAO.SourceDAO;
import com.LogVisualization.Splunk.LogVisualizationService;
import com.LogVisualization.Utils.SV;

@WebServlet("/SourceUpload")
public class SourceUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 3669718056837160481L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String path = SV.SPLUNK_LOCALPATH;
		File uploadDir = new File(path);
		if (!uploadDir.exists()){
			uploadDir.mkdir();
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(uploadDir);
		factory.setSizeThreshold(1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				String name = item.getFieldName();
				if (item.isFormField()) {
					// get the parameter
					String value = item.getString();
					request.setAttribute(name, value);
				} else {
					// get the file name
					String value = item.getName();
					int start = value.lastIndexOf('\\');
					String filename = value.substring(start + 1);

					// upload file
					request.setAttribute(name, filename);

					item.write(new File(uploadDir, filename));
//					OutputStream out = new FileOutputStream(new File(uploadDir,
//							filename));
//					InputStream in = item.getInputStream();
//					int length = 0;
//					byte[] buf = new byte[1024];
//					System.out.println("��������������������������" + item.getSize());
//					while ((length = in.read(buf)) != -1) {
//						out.write(buf, 0, length);
//					}
//					in.close();
//					out.close();

				}
			}

			String sourcename = (String) request.getAttribute("sourcename");
			String filename = (String) request.getAttribute("file1");

			// call splunk
			LogVisualizationService s = new LogVisualizationService();
			s.Import(sourcename, uploadDir.getAbsolutePath() + SV.CONSTANT_SLASH + filename);
			Thread.sleep(500);
			String result = s.getSearchResult("search index=" + sourcename);

			// call database
			SourceDAO sourceDAO = new SourceDAO();
			sourceDAO.createSource(sourcename, path + SV.CONSTANT_SLASH + filename,
					"initialized", SV.DEFUALT_USERNAME);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("	<head>");
			out.println("		<title>Upload Results</title>");
			out.println("	</head>");
			out.println("	<body>");
			out.println("		Output:Sourcename: " + sourcename + "<br/>");
			out.println("		Output:Filename: " + filename + "<br/>");
			out.println("		Output:Splunk: " + result + "<br/>");
			out.println("	</body>");
			out.println("</html>");
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
