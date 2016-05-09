package com.work.webmagic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class MaoYanProcess implements PageProcessor{
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100).addCookie("iuuid", "F894FE0364F19BC74417EECD9217D9AAEB1D4D6B711E79B5986762264C15B873")
			.addCookie("pro20151223", "true").addCookie("isWebp", "1").addCookie("__mta", "218365862.1462501569505.1462503306072.1462503312246.11")
			.addCookie("__utma", "17099173.5879228.1451300579.1462268698.1462501570.9").addCookie("__utmc", "17099173")
			.addCookie("__utmz", "17099173.1462501570.9.7.utmcsr=mmaoyan|utmccn=(not%20set)|utmcmd=(not%20set)	")
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
	
	@Override
	public void process(Page page) {
//		page.addTargetRequests(page.getHtml().links().all());
		page.putField("movieList", page.getHtml().xpath("//div[@id='ticket_tbody']/ul[@class='canTouch']").all());
		String date = page.getUrl().regex("http://piaofang\\.maoyan\\.com/\\?date=(\\S+)").toString();
		System.out.println("-----------"+date);
		if(date != null && StringUtils.isNotBlank(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date targetDate = sdf.parse(date);
//				Date now = new Date();
//				String nowDate = sdf.format(now);
				Calendar calendar = Calendar.getInstance();
//				if(date.compareTo(nowDate) < 0){
//					calendar.setTime(targetDate);
//					calendar.set(Calendar.DAY_OF_YEAR, 1);
//					page.addTargetRequest("http://piaofang.maoyan.com/?date="+sdf.format(calendar.getTime()));
//				}
				calendar.setTime(targetDate);
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
				page.addTargetRequest("http://piaofang.maoyan.com/?date="+sdf.format(calendar.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			page.addTargetRequest("http://piaofang.maoyan.com/?date=");
		}
//		List<String> movieList = page.getResultItems().get("movieList");
//		if(movieList != null){
//			for(String movieData : movieList){
//				Document doc= Jsoup.parse(movieData);
//				String movieName = doc.select(".c1").get(0).child(0).text();
//				String message = doc.select(".c1").get(0).child(2).text();
//				String piaofang = doc.select(".c1").get(0).child(2).text();
//				System.out.println(doc.select(".c1").get(0).child(0).text());
//			}
//		}
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return this.site;
	}
	public static void main(String[] args) {
		Spider.create(new MaoYanProcess()).addUrl("http://piaofang.maoyan.com/?date=2011-08-19").addPipeline(new TestPinline()).thread(5).run();
	}
}
