/**
 * Copyright (C) 2011 ArtiVisi Intermedia <info@artivisi.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.artivisi.kasbon.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.artivisi.kasbon.domain.Karyawan;
import com.artivisi.kasbon.domain.Pengajuan;
import com.artivisi.kasbon.service.KasbonService;

public class KasbonServiceImplTest {

	private static KasbonService kasbonService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("classpath*:com/artivisi/**/applicationContext.xml");
		kasbonService = (KasbonService) ctx.getBean("kasbonService");
	}

	@Test
	public void testSave() {
		Karyawan k = new Karyawan();
		k.setNip("333");
		k.setNama("Endy Muhardin");
		k.setPlafon(new BigDecimal(10000000));
		
		kasbonService.save(k);
		assertNotNull(k.getId());
		
		Karyawan k1 = kasbonService.findById(k.getId());
		assertNotNull(k1);
		assertEquals("333", k1.getNip());
	}
	
	@Test
	public void testCariPinjamanByKaryawan(){
		Karyawan jimmy = kasbonService.findKaryawanByNip("1001");
		assertNotNull(jimmy);
		
		List<Pengajuan> hasil = kasbonService.findPengajuanByKaryawan(jimmy, 0, 10);
		assertNotNull(hasil);
		assertTrue(hasil.size() == 1);
	}

}
