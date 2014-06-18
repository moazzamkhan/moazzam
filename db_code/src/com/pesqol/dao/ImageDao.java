package com.pesqol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pesqol.db.Database;
import com.pesqol.model.Image;

public class ImageDao {
	public int uploadImage(Image image) throws SQLException {
		Connection c = Database.getConnection();
		String query = "insert into image(id,name,imagePath,uploadedOn) values(?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, image.getId());
		ps.setString(2, image.getName());
		ps.setString(3, image.getImagePath());
		ps.setTimestamp(4, new Timestamp(image.getUploadedOn().getTime()));
		return ps.executeUpdate();
	}

	public List<Image> getAllImages() throws SQLException {
		Connection c = Database.getConnection();
		String query = "select * from image";
		PreparedStatement ps = c.prepareStatement(query);

		ResultSet rs = ps.executeQuery();
		List<Image> images = new ArrayList<Image>();

		while (rs.next()) {
			Image s = new Image();
			s.setId(rs.getString(1));
			s.setName(rs.getString(2));
			s.setImagePath(rs.getString(3));
			s.setUploadedOn(new Date(rs.getTimestamp(4).getTime()));
			images.add(s);
		}
		return images;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
