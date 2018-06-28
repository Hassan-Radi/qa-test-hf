package com.hellofresh.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectOption {

	private WebElement element;

	public SelectOption(WebElement element) {
		this.element = element;
	}

	/**
	 * Selects an option from a Combo-Box given a specific criteria.
	 * 
	 * @param selectionCriteria
	 *            The type of select to use.
	 * @param criteriaValue
	 *            The criteria value to use
	 */
	public void selectBy(SelectionCriteria selectionCriteria, Object criteriaValue) {
		Select select = new Select(element);

		switch (selectionCriteria) {
		case SELECT_BY_INDEX:
			select.selectByIndex((Integer) criteriaValue);
			break;
		case SELECT_BY_VALUE:
			select.selectByValue((String) criteriaValue);
			break;
		case SELECT_BY_VISIBLE_TEXT:
			select.selectByVisibleText((String) criteriaValue);
			break;
		}
	}
}