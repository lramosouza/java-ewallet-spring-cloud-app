package com.lsouza.wallet.templates;

import com.lsouza.wallet.consts.TestConstants;
import com.lsouza.wallet.dto.WalletResponseDTO;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class WalletDTOTemplate implements TemplateLoader{

	@Override
	public void load() {
		Fixture.of(WalletResponseDTO.class).addTemplate(TestConstants.WALLET_DTO, new Rule(){{
		
		}});	
	}

}
