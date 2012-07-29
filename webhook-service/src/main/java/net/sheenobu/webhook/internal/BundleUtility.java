package net.sheenobu.webhook.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BundleUtility {

	public static InputStream createBundle() throws IOException {
		
		File tmpFile = File.createTempFile("OSGI-", ".jar");
		
		System.out.println(tmpFile.toString());
		
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(tmpFile));

		// create the MANIFEST.MF
		addFileFromTemplate(zip,"META-INF/MANIFEST.MF","MANIFEST-template.mf");
		
		// create the spring context.
		addFileFromTemplate(zip,"META-INF/spring/webhook.xml","webhook-template.xml");
		
		//complete the zip.
		zip.close();
		
		InputStream is = new FileInputStream(tmpFile);
		
		return is;
	}

	private static void addFileFromTemplate(ZipOutputStream out, 
			String filename, 
			String sourceName) throws IOException {
		
		byte[] buf = new byte[1024];
		
		InputStream in = BundleUtility.class.getResourceAsStream("/" + sourceName);
		
		out.putNextEntry(new ZipEntry(filename));
		
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        
        out.closeEntry();
        
        in.close();

	}
	
}
