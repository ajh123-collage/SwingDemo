package uk.minersonline.SwingDemo.resource;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
	public static final String TEXTURE_PREFIX = "textures";
	private static final List<ResourceLoader> resourceLoaders = new ArrayList<>(List.of(
			new FileSystemResourceLoader(),
			new ClassLoaderResourceLoader()
	));

	@SuppressWarnings({"ReassignedVariable", "DataFlowIssue"})
	public static BufferedImage loadBufferedImage(ResourceIdentifier path) throws ResourceLoadingException {
		BufferedImage image = null;
		boolean nextLoader = true;
		ResourceLoadingException cause = null;
		for (ResourceLoader loader : resourceLoaders) {
			if (nextLoader) {
				try {
					image = loader.loadBufferedImage(path);
					nextLoader = false;
				} catch (ResourceLoadingException e) {
					nextLoader = true;
					// ^^^ IMPORTANT: set to true if there was an exception, so the next loader in the queue is used.
					// this is the reason why `@SuppressWarnings({"ReassignedVariable", "DataFlowIssue"})` is used.
					cause = e;
				}
			}
		}
		if (image == null) {
			if (cause != null) {
				throw new ResourceLoadingException("Could not load the image [" + path + "] no ResourceLoaders have found it.", cause);
			} else {
				throw new ResourceLoadingException("Could not load the image [" + path + "] no ResourceLoaders have found it.");
			}
		}
		return image;
	}

	@SuppressWarnings({"ReassignedVariable", "DataFlowIssue"})
	public static ImageIcon loadImageIcon(ResourceIdentifier path) throws ResourceLoadingException {
		ImageIcon image = null;
		boolean nextLoader = true;
		ResourceLoadingException cause = null;
		for (ResourceLoader loader : resourceLoaders) {
			if (nextLoader) {
				try {
					image = loader.loadImageIcon(path);
					nextLoader = false;
				} catch (ResourceLoadingException e) {
					nextLoader = true;
					// ^^^ IMPORTANT: set to true if there was an exception, so the next loader in the queue is used.
					// this is the reason why `@SuppressWarnings({"ReassignedVariable", "DataFlowIssue"})` is used.
					cause = e;
				}
			}
		}
		if (image == null) {
			if (cause != null) {
				throw new ResourceLoadingException("Could not load the image [" + path + "] no ResourceLoaders have found it.", cause);
			} else {
				throw new ResourceLoadingException("Could not load the image [" + path + "] no ResourceLoaders have found it.");
			}
		}
		return image;
	}
}