package org.terasology.gestalt.di.instance;

import org.terasology.context.AbstractBeanDefinition;
import org.terasology.context.BeanDefinition;
import org.terasology.gestalt.di.BeanContext;
import org.terasology.gestalt.di.BeanEnvironment;
import org.terasology.gestalt.di.BeanIdentifier;
import org.terasology.gestalt.di.DefaultBeanResolution;
import org.terasology.gestalt.di.Lifetime;

import java.util.Optional;

/**
 * Implementation from class type. use environment to work out fields and properties to inject.
 */
public class ClassProvider<T> extends BeanProvider<T> {
    private final Class<T> target;

    public ClassProvider(BeanEnvironment environment, Lifetime lifetime, Class<T> target) {
        super(environment, lifetime);
        this.target = target;
    }

    @Override
    public Optional<T> get(BeanIdentifier identifier, BeanContext current, BeanContext scopedTo) {
        Optional<BeanDefinition<?>> definition = environment.getDefinition(target);
        if (definition.isPresent() && definition.get() instanceof AbstractBeanDefinition) {
            BeanContext cntx = lifetime == Lifetime.Singleton ? current : scopedTo;
            return (Optional<T>) (definition.get()).build(new DefaultBeanResolution(cntx, environment));
        }
        return Optional.empty();
    }

    @Override
    public void close() throws Exception {
        // Noting to close
    }
}
