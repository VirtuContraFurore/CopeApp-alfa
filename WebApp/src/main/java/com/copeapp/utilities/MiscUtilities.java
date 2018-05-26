package com.copeapp.utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.copeapp.entities.common.Role;

public class MiscUtilities {
	
	public static boolean checkRoles(List<Role> roles0, List<Role> roles1) {
		for (Role r : roles0) {
			if (r.getRole().equalsIgnoreCase("admin")) {
				return true;
			}
		}

		ArrayList<Role> commonRole = new ArrayList<Role>(roles0);
		commonRole.retainAll(roles1);
		if (!commonRole.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean isAdmin(List<Role> roles) {
		for (Role r : roles) {
			if (r.getRole().equalsIgnoreCase("amministratore")) {
				return true;
			}
		}
		return false;
	}
	
	public static String resizeImage(String image, int maxWidth, int maxHeight) throws IOException {
		Pattern regex = Pattern.compile("(?<=data:image\\/)(.*)(?=;base64,)");
		Matcher m = regex.matcher("FOO[BAR]");
		String type = "";
		if (m.find()) {
			type = m.group(1);
		} else {
			throw new IOException("base64 bad formatted");
		}
		byte[] imageBta = Base64.getDecoder().decode(image.substring(image.indexOf(',')+1));
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBta));
		
		int originalHeight = img.getHeight();
		int originalWidth = img.getWidth();
		double ratioHeight = maxHeight/originalHeight;
		double ratioWidth = maxWidth/originalWidth;
		Image imgScaled = null;
		BufferedImage scaledImage = null;
		
		if (ratioHeight < 1 || ratioWidth < 1) {
			if (ratioHeight < 1 && ratioWidth >= 1) {
				imgScaled = img.getScaledInstance((int)Math.floor(originalWidth*ratioHeight), (int)Math.floor(originalHeight*ratioHeight), Image.SCALE_SMOOTH);
				scaledImage = new BufferedImage((int)Math.floor(originalWidth*ratioHeight), (int)Math.floor(originalHeight*ratioHeight), BufferedImage.TYPE_INT_ARGB);
			} else if (ratioWidth < 1 && ratioHeight >= 1) {
				imgScaled = img.getScaledInstance((int)Math.floor(originalWidth*ratioWidth), (int)Math.floor(originalHeight*ratioWidth), Image.SCALE_SMOOTH);
				scaledImage = new BufferedImage((int)Math.floor(originalWidth*ratioWidth), (int)Math.floor(originalHeight*ratioWidth), BufferedImage.TYPE_INT_ARGB);
			} else if (ratioHeight < 1 && ratioWidth < 1) {
				if (ratioHeight <= ratioWidth) {
					imgScaled = img.getScaledInstance((int)Math.floor(originalWidth*ratioHeight), (int)Math.floor(originalHeight*ratioHeight), Image.SCALE_SMOOTH);
					scaledImage = new BufferedImage((int)Math.floor(originalWidth*ratioHeight), (int)Math.floor(originalHeight*ratioHeight), BufferedImage.TYPE_INT_ARGB);
				} else {
					imgScaled = img.getScaledInstance((int)Math.floor(originalWidth*ratioWidth), (int)Math.floor(originalHeight*ratioWidth), Image.SCALE_SMOOTH);
					scaledImage = new BufferedImage((int)Math.floor(originalWidth*ratioWidth), (int)Math.floor(originalHeight*ratioWidth), BufferedImage.TYPE_INT_ARGB);
				}
			}
		} else {
			imgScaled = img.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH);
			scaledImage = new BufferedImage(originalWidth, originalHeight, BufferedImage.TYPE_INT_ARGB);
		}
		
		Graphics2D g2d = scaledImage.createGraphics();
	    g2d.drawImage(imgScaled, 0, 0, null);
	    g2d.dispose();
	    
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(scaledImage, "png", baos );
	    
	    return "data:image/"+type+";base64,"+Base64.getEncoder().encodeToString(baos.toByteArray());
	}
	
}