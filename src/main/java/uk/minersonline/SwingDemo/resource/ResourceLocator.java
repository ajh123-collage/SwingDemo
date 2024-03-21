package uk.minersonline.SwingDemo.resource;

import java.util.List;

class ResourceLocator<T> {
    private final List<ResourceProducer<T>> producers;

    public ResourceLocator(List<ResourceProducer<T>> producers) {
        this.producers = producers;
    }

    public T loadResource(ResourceIdentifier path) throws ResourceLoadingException {
        T resource = null;
        boolean nextLoader = true;
        ResourceLoadingException cause = null;
        for (ResourceProducer<T> producer : producers) {
            if (nextLoader) {
                try {
                    resource = producer.apply(path);
                    nextLoader = false;
                } catch (ResourceLoadingException e) {
                    cause = e;
                }
            }
        }
        if (resource == null) {
            if (cause != null) {
                throw new ResourceLoadingException("Could not load the resource [" + path + "] no ResourceLoaders have found it.", cause);
            } else {
                throw new ResourceLoadingException("Could not load the resource [" + path + "] no ResourceLoaders have found it.");
            }
        }
        return resource;
    }

    @FunctionalInterface
    public interface ResourceProducer<T> {
        T apply(ResourceIdentifier path) throws ResourceLoadingException;
    }
}
