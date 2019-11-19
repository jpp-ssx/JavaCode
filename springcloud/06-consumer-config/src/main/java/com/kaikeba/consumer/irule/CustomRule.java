package com.kaikeba.consumer.irule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 自定义负载均衡策略：从所有可用的provider中排除掉指定端口号的provider,剩余的provider进项随机选择
 */
public class CustomRule implements IRule {
    private ILoadBalancer iLoadBalancer;
    //要排除的提供者端口号集合
    private List<Integer> excludePorts;

    public CustomRule() {
    }

    public CustomRule(List<Integer> excludePorts) {
        this.excludePorts = excludePorts;
    }

    @Override
    public Server choose(Object o) {
        //获取所有可用的提供者
        List<Server> servers = iLoadBalancer.getReachableServers();
        //可用的提供者
        List<Server> avaliableServers = this.getAvaliableServers(servers);
        //使用随机策略返回负载均衡算法
        return this.getAvaliableRandomServers(avaliableServers);
    }

    private Server getAvaliableRandomServers(List<Server> avaliableServers) {
        //获取一个[0,avaliableServers.size())的随机整数
        int index = new Random().nextInt(avaliableServers.size());
        return avaliableServers.get(index);
    }

    private List<Server> getAvaliableServers(List<Server> servers) {
        if (excludePorts == null || excludePorts.size() <= 0) {
            return servers;
        }
        //定义一个排除了指定端口的提供者集合
        List<Server> aservers = new ArrayList<>();
        for (Server server : servers) {
            boolean flag = true;
            int port = server.getPort();
            for (Integer excludePort : excludePorts) {
                if (port == excludePort) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                aservers.add(server);
            }

        }
        return aservers;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return null;
    }
}
