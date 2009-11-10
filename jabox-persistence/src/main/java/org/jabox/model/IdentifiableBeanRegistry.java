/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
