package com.meta.srmeta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meta.srmeta.entities.Sale;
import com.meta.srmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {
	@Autowired
	private SaleRepository saleRepository;
	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	public void sendSms(Long id) {
		Sale sale = saleRepository.getById(id);
		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		String mensagem = "Vendedor: "+sale.getSellerName() + " foi destaque em " + date + " com um total de: " + String.format("%.2f", sale.getAmount());

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, mensagem).create();

		System.out.println(message.getSid());
	}
}