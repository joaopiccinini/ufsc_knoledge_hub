package org.ufsc.knoledgehub.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@SuppressWarnings("nls")
@RunWith(MockitoJUnitRunner.class)
public class UploadBusinessTest {

	@Before
	public void configurePersistenceManager() {
		File folder = new File("uploads/projects");
		folder.mkdirs();
	}

	@After
	public void removeDatabase() {
		File folder = new File("uploads");
		removeChilds(folder);
		folder.delete();
	}

	private void removeChilds(File folder) {
		for (File folderChild : folder.listFiles()) {
			if (folderChild.isDirectory()) removeChilds(folderChild);
			folderChild.delete();
		}
	}

	@Mock
	private HttpSession session;

	@Mock
	private ServletContext servletContext;

	@Test
	public void shouldPersistProjectStream() throws Exception {
		when(session.getAttribute("login")).thenReturn("user");

		File sourceFile = new File("src/test/resources/java.png");
		FileInputStream projectInputStream = new FileInputStream(sourceFile);

		File targetFile = new File("uploads/projects/user/title.png");
		targetFile.getParentFile().mkdir();

		when(servletContext.getRealPath(any(String.class))).thenReturn("uploads/projects");
		String filePath = new UploadBusiness(servletContext).processUpload(projectInputStream, "user", "title", "test.png");

		assertTrue(targetFile.exists());
		assertEquals(sourceFile.length(), targetFile.length());
		assertEquals(targetFile.getPath(), filePath);
	}
}
