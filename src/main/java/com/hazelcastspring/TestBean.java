package com.hazelcastspring;

import org.springframework.cache.annotation.Cacheable;

/**
 * Created by Mustafa Orkun Acar <mustafaorkunacar@gmail.com> on 07.07.2014.
 */

public class TestBean
{
    private String result;
    @Cacheable("cacheManagerForClient")
    public String getResult() { System.out.println("Cacheable Run"); return result; }
    public void setResult(String Result) { result = Result; }
}