package org.ufsc.knoledgehub.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

public class UploadBusiness {

	private static final String UPLOAD_PATH = "uploads/projects"; //$NON-NLS-1$
	private final File basePath;

	public UploadBusiness(ServletContext servletContext) {
		basePath = new File(servletContext.getRealPath(UPLOAD_PATH));
		basePath.mkdirs();
	}

	public String processUpload(InputStream projectInputStream, String firstAuthor, String title, String contentType) throws IOException {
		String fileName = title + getFileExtension(contentType);
		File targetFile = createTargetFile(firstAuthor, fileName);
		FileOutputStream projectOutputStream = new FileOutputStream(targetFile);

		int data = projectInputStream.read();
		while (!isEndOfFile(data)) {
			projectOutputStream.write(data);
			data = projectInputStream.read();
		}

		projectInputStream.close();
		projectOutputStream.close();

		return getRelativePath(targetFile);
	}

	private String getFileExtension(String contentType) {
		return "." + contentType.substring(contentType.length() - 3, contentType.length()); //$NON-NLS-1$
	}

	private File createTargetFile(String projectFirstAuthor, String fileName) {
		File targetFile = new File(new String(basePath + "/" + projectFirstAuthor + "/" + fileName)); //$NON-NLS-1$ //$NON-NLS-2$
		targetFile.getParentFile().mkdir();
		return targetFile;
	}

	private boolean isEndOfFile(int data) {
		return data == -1;
	}

	private String getRelativePath(File targetFile) {
		String path = targetFile.getAbsolutePath();
		return UPLOAD_PATH + path.substring(basePath.getAbsolutePath().length(), path.length());
	}
}
