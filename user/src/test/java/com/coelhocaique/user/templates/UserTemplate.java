package com.lsouza.user.templates;

import com.lsouza.user.consts.TestConstants;
import com.lsouza.user.model.User;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UserTemplate implements TemplateLoader{

	@Override
	public void load() {
		Fixture.of(User.class).addTemplate(TestConstants.USER, new Rule(){{
			add("id", "13214hbkd");
			add("username", "testUser");
			add("key", "MTMyMTRoYmtkOnRlc3RVc2Vy");
		}});	
	}

}
