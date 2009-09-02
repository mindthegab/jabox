package org.jabox.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class IdentifiableBeanRegistry<T extends Identifiable<ID>, ID extends Serializable>
        implements
            ApplicationContextAware,
            InitializingBean
{
    private final Class<T> beanType;

    private ApplicationContext context;
    private Map<ID, T> entries;
    private List<T> beans;
    private List<ID> uuids;

    public IdentifiableBeanRegistry(Class<T> beanType)
    {
        this.beanType = beanType;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        this.context = context;
    }

    public T getEntry(Serializable id)
    {
        T entry = entries.get(id);
        if (entry == null)
        {
            throw new IllegalStateException();
        }
        return entry;
    }

    public List<T> getEntries()
    {
        return beans;
    }

    public List<ID> getIds()
    {
        return uuids;
    }

    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception
    {
        // initialize the registry
        entries = new HashMap<ID, T>();

        final Map<String, ? extends T> matches = BeanFactoryUtils.beansOfTypeIncludingAncestors(
            context, beanType);
        for (T entry : matches.values())
        {
            entries.put(entry.getId(), entry);
        }

        entries = Collections.unmodifiableMap(entries);

        // intialize indexes
        beans = new ArrayList<T>(entries.values());
        beans = Collections.unmodifiableList(beans);
        uuids = new ArrayList<ID>(entries.keySet());
        uuids = Collections.unmodifiableList(uuids);
    }
}
