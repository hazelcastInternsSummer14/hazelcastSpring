package com.hazelcastspring;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by Mustafa Orkun Acar <mustafaorkunacar@gmail.com> on 09.07.2014.
 */
public class SpringClient
{
    public static void main(String[] args)
    {
        Config config = new Config();
        config.getGroupConfig().setName("name");
        config.getGroupConfig().setPassword("pwd");
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("127.0.0.1:5701");

        Hazelcast.newHazelcastInstance(config);
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        TestBean testBean = (TestBean) context.getBean("springTestBean");

        System.out.println("#######  CLIENT BEGIN #######");

        for(int i = 0; i < 5; i++)
            System.out.println(testBean.getResult());

        System.out.println("#######  CLIENT END #######");

        Hazelcast.shutdownAll();
        HazelcastClient.shutdownAll();

    }
}
