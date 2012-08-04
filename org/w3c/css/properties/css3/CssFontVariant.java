// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * //spec http://www.w3.org/TR/2011/WD-css3-fonts-20111004/#font-variant-prop
 * // TODO UNFINISHED
 */
public class CssFontVariant extends org.w3c.css.properties.css.CssFontVariant {

	public static final CssIdent normal = CssIdent.getIdent("normal");
	public static final CssIdent smallCaps = CssIdent.getIdent("small-caps");

	/**
	 * Creates a new CssFontVariant
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontVariant(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		// TODO FIXME this is a shorthand!
		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent ident = (CssIdent) val;
			if (inherit.equals(ident)) {
				value = inherit;
			} else {
				value = org.w3c.css.properties.css21.CssFontVariant.getAllowedFontVariant(ident);
				if (value == null) {
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				}
			}
		} else {
			throw new InvalidParamException("value",
					val.toString(),
					getPropertyName(), ac);
		}
		expression.next();
	}

	public CssFontVariant(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	public CssFontVariant() {
		value = initial;
	}
}

