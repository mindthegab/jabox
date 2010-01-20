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
package org.jabox.webapp.menubuttons;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.form.ImageButton;
import org.jabox.apis.ConnectorConfig;

/**
 * Returns an Icon that represents the {@link ConnectorConfig} that is passed.
 * <p/>
 * The Icon should be located at the same path with the {@link ConnectorConfig}
 * with the name "icon.png".
 * <p/>
 * If the Icon does not exist, a default icon is returned.
 * 
 * @author Administrator
 * 
 */
public final class IconButton extends ImageButton {

	private static final String ICON_PNG = "icon.png";

	private static final long serialVersionUID = 1L;

	private static final ResourceReference PLUGIN = new ResourceReference(
			IconButton.class, "plugin.png");

	public IconButton(final String id, final ConnectorConfig item) {
		super(id);
		ResourceReference rr = new ResourceReference(item.getClass(), ICON_PNG);

		if (existImage(rr)) {
			setImageResourceReference(rr);
		} else {
			setImageResourceReference(PLUGIN);
		}
	}

	private boolean existImage(ResourceReference rr) {
		return rr.getScope().getResource(ICON_PNG) != null;
	}
}
