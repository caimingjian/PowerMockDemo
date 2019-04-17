package com.magictan.mockdemo.localservice.impl;

import com.magictan.mockdemo.exception.MockException;
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

import static org.junit.Assert.assertEquals;
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

    @Test
    @PrepareForTest(RemoteServiceImpl.class)
    public void test() throws MockException {
        when(remoteService.getRemoteNode("name")).thenThrow(new MockException("message", "name"));
        try {
            Node name = localService.getRemoteNode("name");
        }catch (Exception e){
            assertEquals("exception", e.getMessage()); //验证是否返回指定异常内容
            assertEquals("message", e.getMessage()); //验证是否返回指定异常内容
        }
    }

}
