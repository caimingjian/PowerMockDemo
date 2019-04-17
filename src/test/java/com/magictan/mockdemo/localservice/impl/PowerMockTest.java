package com.magictan.mockdemo.localservice.impl;

import com.magictan.mockdemo.model.Node;
import com.magictan.mockdemo.remoteservice.impl.RemoteServiceImpl;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.CORBA.Any;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @Author: cmj
 * @Description: powerMock 使用案例
 * @Date: 2019/4/17
 */
@RunWith(PowerMockRunner.class) //让测试运行于PowerMock环境
public class PowerMockTest {

    @InjectMocks
    //需要进入执行的方法
    LocalServiceImpl localService;

    @Mock
    //不希望执行的方法
    RemoteServiceImpl remoteService;

    /**
     * mockService的调用，返回指定结果
     */
    @Test
    @PrepareForTest(RemoteServiceImpl.class)//mock的类在单测的开始用PrepareForTest说明，不然会报错
    public void mockServiceResult(){
        //when... return指定返回对象的值
        when(remoteService.getRemoteNode(anyInt())).thenReturn(new Node(1,"test"));
        //这一步模拟真实调用
        Node remoteNode = localService.getRemoteNode(anyInt());
        //返回结果的断言
        Assert.assertEquals(remoteNode.getName(),"test");
        Assert.assertEquals(remoteNode.getNum(),1);

    }

    /**
     * 静态方法的mock
     */
    
}
