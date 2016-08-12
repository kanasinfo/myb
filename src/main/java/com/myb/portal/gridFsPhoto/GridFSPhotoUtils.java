package com.myb.portal.gridFsPhoto;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.myb.portal.util.Utils;
@Repository
public class GridFSPhotoUtils {
	@Autowired
	MongoTemplate mongoTemplate;
	public String saveFile(String key,String id,String questionName){
		try {
			GridFS g = new GridFS(mongoTemplate.getDb(), "qr_code");
			ByteArrayOutputStream out = Utils.storeCode(key);
			GridFSInputFile gfsfile = g.createFile(out.toByteArray());
			gfsfile.setFilename(id);
			gfsfile.put("questionName", questionName);
			gfsfile.save();
		} catch (Exception e) {
		}
		return key;
	}
	public Map<String, Object> getFileById(String key){
		Map<String, Object> map = new HashMap<>();
		try {
			GridFS g = new GridFS(mongoTemplate.getDb(), "qr_code");
			GridFSDBFile file = g.findOne(key);
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			map.put("questionName", file.get("questionName"));
			file.writeTo(b);
			map.put("file", b);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean removeFile(String key){
		boolean res =false;
		try {
			GridFS g = new GridFS(mongoTemplate.getDb(), "qr_code");
			g.remove(key);
			res = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return res;
	}
}
