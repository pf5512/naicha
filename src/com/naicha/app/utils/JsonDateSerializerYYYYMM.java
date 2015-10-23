package com.naicha.app.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * @author yangxujia
 * @date 2015年10月7日下午3:30:14
 */
public class JsonDateSerializerYYYYMM extends JsonSerializer<Date> {
	   private SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM");
	   @Override
	   public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
	           throws IOException, JsonProcessingException {
	       String value = dateFormat.format(date);
	       gen.writeString(value);
	   }
	}