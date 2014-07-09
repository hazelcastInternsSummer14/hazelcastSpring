package com.hazelcastspring;

import com.hazelcast.core.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Mustafa Orkun Acar <mustafaorkunacar@gmail.com> on 07.07.2014.
 */

public class HazelcastDataTypes
{
    static Random rand = new Random();
    static ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    static TestBean testBean = (TestBean) context.getBean("springTestBean");

    public static void main(String[] args)
    {
        for(int i = 0; i < 5; i++)
            System.out.println(testBean.getResult());

        ExecuteMap();
        ExecuteMultiMap();
        ExecuteQueue();
        ExecuteTopic();
        ExecuteSet();
        ExecuteList();
        ExecuteExecuterService();
        ExecuteIdGenerator();
    }

    public static void ExecuteMap()
    {
        int k = rand.nextInt(100);
        int v = rand.nextInt(100);
        IMap map = (IMap) context.getBean("map");
        map.put(k, v);
        System.out.println(map.get(k));
    }

    public static void ExecuteMultiMap()
    {
        int k = rand.nextInt(100);
        int v = rand.nextInt(100);
        MultiMap<Integer, Integer> multimap = (MultiMap) context.getBean("multiMap");
        multimap.put(k, v);
        System.out.println(multimap.get(k));
    }

    public static void ExecuteQueue()
    {
        int k = rand.nextInt(100);
        Queue queue = (Queue) context.getBean("queue");
        queue.add(k);
        System.out.println(queue.poll());
    }

    public static void ExecuteTopic()
    {
        /*
        ITopic topic = (ITopic) context.getBean("topic");
        topic.addMessageListener()
        */
    }

    public static void ExecuteSet()
    {
        int k = rand.nextInt(100);
        ISet set = (ISet) context.getBean("set");
        set.add(k);
        System.out.println(set.iterator().next());
    }

    public static void ExecuteList()
    {
        int k = rand.nextInt(100);
        List<Integer> list = (List<Integer>) context.getBean("list");
        list.add(k);
        System.out.println(list.iterator().next());
    }

    public static void ExecuteExecuterService()
    {
        ExecutorService executorService = (ExecutorService) context.getBean("executorService");
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("ExecuterService Run");
            }
        });
        executorService.shutdown();
    }

    public static void ExecuteIdGenerator()
    {

    }
}
