package com.work.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GItRepoDemo implements PageProcessor  {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://www\\.zhihu\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://www\\.zhihu\\.com/question/(\\w+)").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        page.putField("follow", page.getHtml().xpath("//div[@class='vcard-stats border-top border-bottom border-gray-light mb-3 py-3']/a[1]/strong/text()").toString());
        page.putField("test", page.getHtml().xpath("//h3[@id='zh-question-answer-num']/text()"));
        page.putField("count", page.getHtml().xpath("//span[@class='count']/text()").all());
        if ((page.getResultItems().get("test") == null) || (page.getResultItems().get("count") == null)){
            //skip this page
            page.setSkip(true);
        }
        
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        Spider.create(new GItRepoDemo()).addUrl("https://github.com/code4craft").thread(5).run();
        Spider.create(new GItRepoDemo()).addUrl("https://www.zhihu.com/question/43134055").thread(5).run();
    }
}
