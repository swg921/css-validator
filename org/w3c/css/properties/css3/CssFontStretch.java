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

import java.util.HashMap;

/**
 * @spec http://www.w3.org/TR/2011/WD-css3-fonts-20111004/#font-stretch-prop
 */
public class CssFontStretch extends org.w3c.css.properties.css.CssFontStretch {

	static final String[] _allowed_values = {"normal", "ultra-condensed",
			"extra-condensed", "condensed", "semi-condensed", "semi-expanded",
			"expanded", "extra-expanded", "ultra-expanded"};
	static final HashMap<String, CssIdent> allowed_values;

	static {
		allowed_values = new HashMap<String, CssIdent>();
		for (String s : _allowed_values) {
			allowed_values.put(s, CssIdent.getIdent(s));
		}
	}

	/**
	 * Create a new CssFontStretch
	 */
	public CssFontStretch() {
		value = initial;
	}

	/**
	 * Creates a new CssFontStretch
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontStretch(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent ident = (CssIdent) val;
			if (inherit.equals(ident)) {
				value = inherit;
			} else {
				value = allowed_values.get(val.toString());
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

	public CssFontStretch(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}


}

