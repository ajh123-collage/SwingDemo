package uk.minersonline.SwingDemo.resource;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ResourceManager {
	private static final FileSystemResourceLoader FILE_SYSTEM_RESOURCE_LOADER = new FileSystemResourceLoader();
	private static final ClassLoaderResourceLoader CLASS_LOADER_RESOURCE_LOADER = new ClassLoaderResourceLoader();

	private static final ResourceLocator<BufferedImage> BUFFERED_IMAGE_RESOURCE_LOCATOR = new ResourceLocator<>(List.of(
		FILE_SYSTEM_RESOURCE_LOADER::loadBufferedImage,
		CLASS_LOADER_RESOURCE_LOADER::loadBufferedImage
	));

	private static final ResourceLocator<ImageIcon> IMAGE_ICON_RESOURCE_LOCATOR = new ResourceLocator<>(List.of(
		FILE_SYSTEM_RESOURCE_LOADER::loadImageIcon,
		CLASS_LOADER_RESOURCE_LOADER::loadImageIcon
	));

	private static final ResourceLocator<Clip> CLIP_RESOURCE_LOCATOR = new ResourceLocator<>(List.of(
			FILE_SYSTEM_RESOURCE_LOADER::loadClip,
			CLASS_LOADER_RESOURCE_LOADER::loadClip
	));

	public static BufferedImage loadBufferedImage(ResourceIdentifier path) throws ResourceLoadingException {
		return BUFFERED_IMAGE_RESOURCE_LOCATOR.loadResource(path);
	}

	public static ImageIcon loadImageIcon(ResourceIdentifier path) throws ResourceLoadingException {
		return IMAGE_ICON_RESOURCE_LOCATOR.loadResource(path);
	}

	public static Clip loadClip(ResourceIdentifier path) throws ResourceLoadingException {
		return CLIP_RESOURCE_LOCATOR.loadResource(path);
	}
}
