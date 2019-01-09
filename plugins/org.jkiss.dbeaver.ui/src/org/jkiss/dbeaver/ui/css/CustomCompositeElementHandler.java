package org.jkiss.dbeaver.ui.css;

import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.CSSSWTConstants;
import org.eclipse.e4.ui.css.swt.dom.CompositeElement;
import org.eclipse.e4.ui.css.swt.helpers.SWTElementHelpers;
import org.eclipse.e4.ui.css.swt.properties.css2.CSSPropertyBackgroundSWTHandler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.w3c.dom.css.CSSValue;


/**
 * Needed to override theme styles.
 * For now it's used only for coloring widgets regarding the connection type color.
 */
public class CustomCompositeElementHandler extends CSSPropertyBackgroundSWTHandler {

    @Override
    public void applyCSSPropertyBackgroundColor(Object element, CSSValue value, String pseudo, CSSEngine engine)
            throws Exception {
        Widget widget = SWTElementHelpers.getWidget(element);
        if (widget == null) {
            return;
        }

        Color newColor = CustomSelectedTabFillHandler.getCurrentConnectionColor();
        if (DBStyles.COLORED_BY_CONNECTION_TYPE.equals(widget.getData(CSSSWTConstants.CSS_CLASS_NAME_KEY)) && newColor != null) {
            applyCustomBackground(element, newColor);
        } else {
            super.applyCSSPropertyBackgroundColor(element, value, pseudo, engine);
        }
    }

    protected void applyCustomBackground(Object element, Color newColor) {
        Composite nativeWidget = (Composite)((CompositeElement)element).getNativeWidget();
        nativeWidget.setBackground(newColor);
    }


}