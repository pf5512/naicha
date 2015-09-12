package com.naicha.app.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * 格式日期
 * @author guxiong
 *
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
	   private SimpleDateFormat dateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");
	   @Override
	   public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
	           throws IOException, JsonProcessingException {
	       String value = dateFormat.format(date);
	       gen.writeString(value);
	   }
	   
	   
	}