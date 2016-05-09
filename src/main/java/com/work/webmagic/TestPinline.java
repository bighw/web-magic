package com.work.webmagic;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class TestPinline implements Pipeline  {

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<String> movieList =resultItems.get("movieList");
		if(movieList != null){
			for(String movieData : movieList){
				Document doc= Jsoup.parse(movieData);
				String movieName = doc.select(".c1").get(0).child(0).text();
				String message = doc.select(".c1").get(0).child(2).text();
				String piaofang = doc.select(".c1").get(0).child(3).text();
//				System.out.println(doc.select(".c1").get(0).child(0).text());
				System.out.println(movieName+message+piaofang);
			}
		}
		
	}

}
