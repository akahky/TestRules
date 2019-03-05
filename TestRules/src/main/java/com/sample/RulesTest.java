package com.sample;

import java.util.Iterator;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class RulesTest {
	public static final void main(String[] args) {
		try {
			// Load the KIE base:
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieBase kieBase = kContainer.getKieBase("rules");
			
			KieSession kSession = kieBase.newKieSession();

			// Set up the fact model:
			Person p = new Person();
			p.setWage(120);
			p.setFirstName("Tom");
			p.setLastName("Summers");
			p.setHourlyRate(10);

			// Insert the person into the session:
			kSession.insert(p);

			// Fire all rules:
			kSession.fireAllRules();
			kSession.dispose();
			Iterator iterator = kSession.getFactHandles().iterator();
			while (iterator.hasNext()) {
				Object object = kSession.getObject((FactHandle)iterator.next());
				System.out.println("Object = " + object);
			}
		}

		catch (Throwable t) {
			t.printStackTrace();
		}
	}
}