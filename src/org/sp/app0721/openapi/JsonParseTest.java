package org.sp.app0721.openapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//JSON이란? 자바스크립트 객체 표기법
//xml과 함께 이기종간 데이터를 교환시 사용되는 데이터 포맷(그냥 문자열이다)
//자바는 json이해 못함 따라서 문자열에 불과한 json포맷을 자바언어가 이해하도록 
//처리 즉 해석하는 프로그래밍을 배워보자, 파싱법을 배워보자
public class JsonParseTest {

	public static void main(String[] args) {
		
		//외부의 라이브러리 maven repository
		
		String str="";
		
		str+="{";
		str+="\"name\":	\"철수\", ";
		str+="\"age\": 28, ";
		str+="\"children\" :[";
		str+="{";
		str+="\"type\":\"고양이\",";
		str+="\"age\" :2, ";
		str+="\"name\":\"나비\",";
		str+="\"color\":\"검정\"";
		str+="},";
		str+="{";
		str+="\"type\":\"강아지\",";
		str+="\"age\" :5, ";
		str+="\"name\":\"뽀미\",";
		str+="\"color\":\"흰색\"";
		str+="}";
		str+="]";
		str+="}";
		
		//System.out.println(str);
		
		JSONParser jsonParser=new JSONParser();
		try {
			JSONObject obj=(JSONObject)jsonParser.parse(str);
			//System.out.println(obj.get("name"));
			
			JSONArray array=(JSONArray)obj.get("children");
			System.out.println(array.size());
			for(int i=0;i<array.size();i++) {
				JSONObject pet=(JSONObject)array.get(i);
				System.out.println("종류"+pet.get("type"));
				System.out.println("나이"+pet.get("age"));
				System.out.println("이름"+pet.get("name"));
				System.out.println("색상"+pet.get("color"));
			}
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
}
