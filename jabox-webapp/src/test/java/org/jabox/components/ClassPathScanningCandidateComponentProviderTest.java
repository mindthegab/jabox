package org.jabox.components;

import java.lang.reflect.Constructor;
import java.util.Set;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.WicketTester;
import org.jabox.webapp.application.WicketApplication;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class ClassPathScanningCandidateComponentProviderTest {
	@Test
	public void testAllWicketPanels() throws Exception {
		WicketTester wicketTester = new WicketTester();

		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
				true);
		provider.addIncludeFilter(new AssignableTypeFilter(Panel.class));

		Set<BeanDefinition> components = provider
				.findCandidateComponents("org/jabox");
		for (BeanDefinition component : components) {
			Class clazz = Class.forName(component.getBeanClassName());
			if (hasDefaultConstructor(clazz)) {
				testWicketPanel(wicketTester, clazz);
			}
		}
	}

	private void testWicketPanel(WicketTester wicketTester, Class clazz) {
		wicketTester.startPanel(clazz);
		wicketTester.assertNoErrorMessage();
		wicketTester.assertNoInfoMessage();
	}

	private boolean hasDefaultConstructor(Class clazz) {
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
