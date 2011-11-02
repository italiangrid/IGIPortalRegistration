package portal.registration.utils;

import java.beans.PropertyEditorSupport;

/**
 * Property editor for Long type properties.
 * 
 * @author asarin
 */
public class LongNumberEditor extends PropertyEditorSupport {
	@Override
	public String getAsText() {
		String returnVal = "";
		if (getValue() instanceof Long) {
			returnVal = String.valueOf((Long) getValue());
		}
		if (getValue() != null && getValue() instanceof String[]) {
			returnVal = ((String[]) getValue())[0];
		}
		return returnVal;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(Long.valueOf(text));
	}
}
