package com.pesqol.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import com.pesqol.dao.ImageDao;
import com.pesqol.model.Image;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class ImageUploadTestServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("StudentServlet.doGet()");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rootPath = req.getServletContext().getRealPath("");
		String imagePath = File.separator + "user-images";
		Part filePart = req.getPart("file");
		String filename = getFilename(filePart);
		Image im = new Image();
		String extension = FilenameUtils.getExtension(filename);
		imagePath += File.separator + im.getId() + "." + extension;
		filePart.write(rootPath + imagePath);
		ImageDao imd = new ImageDao();
		im.setName(filename);
		im.setImagePath(imagePath);
		try {
			imd.uploadImage(im);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("imageUpload.jsp").forward(req, resp);
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}
}
