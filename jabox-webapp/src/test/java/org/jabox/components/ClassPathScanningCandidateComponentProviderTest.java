/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package org.jabox.components;

import java.lang.reflect.Constructor;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class ClassPathScanningCandidateComponentProviderTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testAllWicketPanels() throws Exception {
		WicketTester wicketTester = new WicketTester();

//		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
//				true);
//		provider.addIncludeFilter(new AssignableTypeFilter(Panel.class));
//
//		Set<BeanDefinition> components = provider
//				.findCandidateComponents("org/jabox");
//		for (BeanDefinition component : components) {
//			Class clazz = Class.forName(component.getBeanClassName());
//			if (hasDefaultConstructor(clazz)) {
//				testWicketPanel(wicketTester, clazz);
//			}
//		}
	}

	@SuppressWarnings("unchecked")
	private void testWicketPanel(final WicketTester wicketTester,
			final Class clazz) {
		wicketTester.startPanel(clazz);
		wicketTester.assertNoErrorMessage();
		wicketTester.assertNoInfoMessage();
	}

	@SuppressWarnings("unchecked")
	private boolean hasDefaultConstructor(final Class clazz) {
		for (Constructor constructor : clazz.getConstructors()) {
			if (constructor.getParameterTypes().length == 1
					&& constructor.getParameterTypes()[0].getSimpleName()
							.equals("String")) {
				return true;
			}
		}

		return false;
	}
}
