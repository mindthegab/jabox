package org.jabox.webapp.modifiers;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.Model;

public class TooltipModifier extends AttributeModifier {

	private static final String TITLE = "title";

	public TooltipModifier(String tooltip) {
		super(TITLE, true, new Model<String>(tooltip));
	}

	public TooltipModifier(Model<String> tooltipModel) {
		super(TITLE, true, tooltipModel);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2577746456955106108L;

}
