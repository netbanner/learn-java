package com.example.webservice;

import com.example.webservice.util.HttpAPIService;
import com.example.webservice.util.HttpResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWebserviceApplicationTests {

	@Autowired
	HttpAPIService httpAPIService;

	@Test
	public void contextLoads() {
	}


	@Test
	public void testHttpClient(){
		String url = "http://services.iceportal.com/Service.asmx";
		String xmlData = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://services.iceportal.com/service\">\n" +
				"\t\t<soap:Header>\n" +
				"\t\t   <ser:ICEAuthHeaderWithMType>\n" +
				"\t\t\t  <ser:Username>derbysoftdp@iceportal.com</ser:Username>\n" +
				"              <ser:Password>Derby51</ser:Password>\n" +
				"\t\t   </ser:ICEAuthHeaderWithMType>\n" +
				"\t\t</soap:Header>\n" +
				"\t\t<soap:Body>\n" +
				"\t\t   <ser:GetProperties>\n" +
				"\t\t\t  <ser:publicationStatus>1</ser:publicationStatus>\n" +
				"\t\t\t  <ser:modifiedTypeOption>0</ser:modifiedTypeOption>\n" +
				"\t\t\t  <ser:sinceDate>2018-03-12</ser:sinceDate>\n" +
				"\t\t\t  <ser:propertyType>1</ser:propertyType>\n" +
				"\t\t\t  <ser:includeSupplierInfo>1</ser:includeSupplierInfo>\n" +
				"\t\t   </ser:GetProperties>\n" +
				"\t\t</soap:Body>\n" +
				"\t </soap:Envelope>";
		try {
			HttpResult result = httpAPIService.sendXMLPost(url, xmlData);
			System.out.println(result.getBody());
			System.out.println(result.getCode());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
