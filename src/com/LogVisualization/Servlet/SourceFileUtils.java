package com.LogVisualization.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.directwebremoting.io.FileTransfer;

import com.LogVisualization.Splunk.LogVisualizationService;
import com.LogVisualization.Utils.SV;

public class SourceFileUtils {

	public static final int BUFFERSIZE = 1024 * 1024;

	public String uploadFile(List<FileTransfer> fileTransfer, String sourceName){
		
		StringBuilder retVal = new StringBuilder();
		
		String path = SV.SPLUNK_LOCALPATH;
		File uploadDir = new File(path);
		if (!uploadDir.exists()){
			uploadDir.mkdir();
		}
		LogVisualizationService s = new LogVisualizationService();

		for (FileTransfer obj : fileTransfer) {
			String fileName = getRealFileName(obj.getFilename());
			System.out.println(fileName);
			
			try {
				InputStream in = obj.getInputStream();
				OutputStream out = new FileOutputStream(new File(uploadDir, fileName));
				int bytesRead = 0;  
                byte[] buffer = new byte[BUFFERSIZE];  
                while ((bytesRead = in.read(buffer)) != -1) {  
                    out.write(buffer, 0, bytesRead);  
                }  
                out.close();  
                in.close();
                
                System.out.println(uploadDir.getAbsolutePath() + SV.CONSTANT_SLASH + fileName);
                s.Import(sourceName, uploadDir.getAbsolutePath() + SV.CONSTANT_SLASH + fileName);
                
                retVal.append(fileName).append(':').append("success\n");
			}
			catch (IOException e){
				retVal.append(fileName).append(':').append("failed\n");
			}
		}
		return retVal.toString();
	}
	
	private String getRealFileName(String ori){
		return ori.substring(ori.lastIndexOf('\\') + 1);
	}
	
}
