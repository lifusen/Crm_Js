package cn.itproject.crm.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 定时备份数据库
 * @author MrLiu
 *
 */
public class BackupMySQLJob {
	private static Logger log = Logger.getLogger(BackupMySQLJob.class);
	private static final String DB_NAME = "crm";
	private static final String BACKUP_DIR = "D:\\CRM-DB-BACK\\";

	public void backup() {
		log.info("开始备份" + DB_NAME + "数据库...");
		long startTime = System.currentTimeMillis();
		try {
			// 密码后面不能有空格
			String cmd = "mysqldump -u root -proot " + DB_NAME;
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(cmd);
			// 得到输入流,写成.sql文件
			InputStream inputStream = process.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);

			Date date = new Date();
			// 备份目录名称
			String dirName = new SimpleDateFormat("yyyy-MM").format(date);
			// SQL文件名称
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(date);
			fileName = DB_NAME + "-" + fileName + ".sql";

			File file = new File(BACKUP_DIR + dirName + "\\" + fileName);
			file.getParentFile().mkdirs();

			PrintWriter writer = new PrintWriter(file);
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
			writer.flush();
			writer.close();

			reader.close();
			inputStreamReader.close();
			inputStream.close();

			// 计算文件大小
			long fileSize = file.length();
			long fileMB = fileSize / (1024 * 1024);
			// 计算耗时
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			diffTime = (diffTime / 1000); // 秒

			log.info("备份" + DB_NAME + "数据库完成");
			log.info(file);
			log.info(fileMB + "MB");
			log.info("耗时" + diffTime + "秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
