package com.migu.schedule;

import com.migu.schedule.constants.ReturnCodeKeys;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import java.util.ArrayList;
import java.util.List;
import com.migu.schedule.constants.ReturnCodeKeys;
import org.junit.Assert;

public class stest {
    public static void main(String[] args){
//        List<Integer> nodeIds= new ArrayList<Integer>();
//        int nodeId=1;
//        nodeIds.add(nodeId);
//        System.out.println(nodeIds);
//        if(nodeIds.contains(nodeId))
//            System.out.println(ReturnCodeKeys.E003);
//        nodeIds.clear();
//        System.out.println(nodeIds);
        Schedule schedule = new Schedule();
        int actual =schedule.init();
        System.out.println(actual);
        Assert.assertEquals(ReturnCodeKeys.E001, actual);

        actual = schedule.registerNode(1);
        System.out.println(actual);
        Assert.assertEquals(ReturnCodeKeys.E003, actual);

        actual = schedule.registerNode(-1);
        Assert.assertEquals(ReturnCodeKeys.E004, actual);

        actual = schedule.registerNode(1);
        actual = schedule.registerNode(1);
        Assert.assertEquals(ReturnCodeKeys.E005, actual);

        actual = schedule.init();
        actual = schedule.registerNode(1);
        actual = schedule.unregisterNode(1);
        Assert.assertEquals(ReturnCodeKeys.E006, actual);

        actual = schedule.init();
        actual = schedule.registerNode(1);
        actual = schedule.unregisterNode(2);
        Assert.assertEquals(ReturnCodeKeys.E007, actual);

        actual = schedule.registerNode(1);
        actual = schedule.addTask(1, 10);
        Assert.assertEquals(ReturnCodeKeys.E008, actual);

        actual = schedule.registerNode(1);
        actual = schedule.addTask(0, 10);
        Assert.assertEquals(ReturnCodeKeys.E009, actual);

        actual = schedule.registerNode(1);
        actual = schedule.addTask(1, 10);
        actual = schedule.addTask(1, 10);
        Assert.assertEquals(ReturnCodeKeys.E010, actual);

        actual = schedule.registerNode(1);
        actual = schedule.addTask(1, 10);
        actual = schedule.deleteTask(1);
        Assert.assertEquals(ReturnCodeKeys.E011, actual);

        actual = schedule.registerNode(1);
        actual = schedule.addTask(1, 10);

        int nactual = schedule.deleteTask(2);
        System.out.println(nactual);
        Assert.assertEquals(ReturnCodeKeys.E012, nactual);





    }
}
