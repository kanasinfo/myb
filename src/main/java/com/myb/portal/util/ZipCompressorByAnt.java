package com.myb.portal.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class ZipCompressorByAnt {
	private File zipFile;

	/**
	 * 压缩文件构造函数
	 * 
	 * @param pathName
	 *            最终压缩生成的压缩文件：目录+压缩文件名.zip
	 */
	public ZipCompressorByAnt(String finalFile) {
		zipFile = new File(finalFile);
	}

	/**
	 * 执行压缩操作
	 * 
	 * @param srcPathName
	 *            需要被压缩的文件/文件夹
	 */
	public boolean compressExe(String srcPathName) {
		try {
			File srcdir = new File(srcPathName);
			if (!srcdir.exists()) {
				throw new RuntimeException(srcPathName + "不存在！");
			}

			Project prj = new Project();
			Zip zip = new Zip();
			zip.setProject(prj);
			zip.setDestFile(zipFile);
			FileSet fileSet = new FileSet();
			fileSet.setProject(prj);
			fileSet.setDir(srcdir);
			zip.addFileset(fileSet);
			zip.execute();	
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	public  boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
//	public static void main(String[] args) {
//		 ZipCompressorByAnt zca = new ZipCompressorByAnt("/Users/wangzx/Desktop/workspace/syct/service/tomcat1/wtpwebapps/myb-portal/WEB-INF/qrcodeImg/575b131524ea2151a11f41c6.zip");
//		 zca.compressExe("/Users/wangzx/Desktop/workspace/syct/service/tomcat1/wtpwebapps/myb-portal/WEB-INF/qrcodeImg/575b131524ea2151a11f41c6/");
//	}
}
