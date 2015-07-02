package com.sihu.utils;

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
public class JsonDateSerializerYYYYMMDDHHMMSS extends JsonSerializer<Date> {
	   private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   @Override
	   public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
	           throws IOException, JsonProcessingException {
	       String value = dateFormat.format(date);
	       gen.writeString(value);
	   }
	   
	   
	}