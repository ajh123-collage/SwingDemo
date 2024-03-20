package uk.minersonline.SwingDemo.resource;

import java.nio.file.Path;

public class ResourceIdentifier {
	private final String namespace;
	private final String path;
	public final static String DEFAULT_NAMESPACE = "swing_demo";

	public ResourceIdentifier(String namespace, String path) {
		this.namespace = namespace;
		this.path = path;
		isValid();
	}

	public ResourceIdentifier(String path) {
		this(DEFAULT_NAMESPACE, path);
	}

	private void isValid() {
		if (isValidNamespace(this.namespace) && isValidPath(this.path)) {
			return;
		}
		if (!isValidNamespace(this.namespace)) {
			throw new RuntimeException(new ResourceIdentifierException(ResourceIdentifierException.Error.NAMESPACE_ERROR));
		}
		if (!isValidPath(this.path)) {
			throw new RuntimeException(new ResourceIdentifierException(ResourceIdentifierException.Error.PATH_ERROR));
		}
	}

	public Path toPath(String prefix) {
		return Path.of(namespace, prefix, path);
	}

	private static boolean isValidNamespace(String namespace) {
		return namespace.matches("^[a-z0-9._-]+$");
	}

	private static boolean isValidPath(String path) {
		return path.matches("^[a-z0-9/._-]+$");
	}

	@Override
	public String toString() {
		return namespace + ":" + path;
	}
}
