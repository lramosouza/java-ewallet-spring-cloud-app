package com.lsouza.wallet.templates;

import com.lsouza.wallet.consts.TestConstants;
import com.lsouza.wallet.model.Wallet;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class WalletTemplate implements TemplateLoader{

	@Override
	public void load() {
		Fixture.of(Wallet.class).addTemplate(TestConstants.WALLET, new Rule(){{
			
		}});	
	}

}
