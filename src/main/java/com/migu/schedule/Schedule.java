package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/*
*类名和方法不能修改
 */
public class Schedule {
    //声明注册节点列表
    public List<Integer> nodeIds=null;
    //声明当前调度任务
    Map<Integer,Integer> currentTasks=null;
    //声明挂起任务
    Map<Integer,Integer> tasksMap=null;
    //声明统计服务消耗
    Map<Integer,Integer> currentConsump=null;

    //初始化节点列表
    public int init() {

        if(nodeIds==null) {
            nodeIds = new ArrayList<Integer>();
        }else{
            nodeIds.clear();
        }
        if(currentTasks==null) {
            currentTasks = new HashMap<Integer,Integer>();
        }else{
            currentTasks.clear();
        }
        if(tasksMap==null) {
            tasksMap = new HashMap<Integer,Integer>();
        }else{
            tasksMap.clear();
        }
        //若注册节点列与当前任务列均为空则初始化成功，否则失败
        if(nodeIds.size()==0&&currentTasks.size()==0&&tasksMap.size()==0)
            return ReturnCodeKeys.E001;
        return ReturnCodeKeys.E000;
    }


    //注册节点
    public int registerNode(int nodeId) {

        if(nodeId<=0)
            return ReturnCodeKeys.E004;

        if(nodeIds!=null&&nodeIds.size()>0&&nodeIds.contains(nodeId))
            return ReturnCodeKeys.E005;
        if(nodeIds==null)
            nodeIds = new ArrayList<Integer>();

        nodeIds.add(nodeId);
        if(nodeIds.contains(nodeId))
            return ReturnCodeKeys.E003;
        return ReturnCodeKeys.E000;
    }

    //删除节点
    public int unregisterNode(int nodeId) {
        if(nodeId<=0)
            return ReturnCodeKeys.E004;
        if(nodeIds!=null&&nodeIds.size()>0&&nodeIds.contains(nodeId)) {
            int i = 0;
            while (i < nodeIds.size()) {
                if (nodeIds.get(i) == nodeId) {
                    nodeIds.remove(i);
                    return ReturnCodeKeys.E006;
                }
            }
        }
        return ReturnCodeKeys.E007;
    }


    //添加待调度任务
    public int addTask(int taskId, int consumption) {
        if(taskId<=0)
            return ReturnCodeKeys.E009;
        if(tasksMap!=null&&tasksMap.size()>0&&tasksMap.containsKey(taskId)){
            return ReturnCodeKeys.E010;
        }
        if(tasksMap==null) {
            tasksMap = new HashMap<Integer,Integer>();
        }
        tasksMap.put(taskId,consumption);
        return ReturnCodeKeys.E008;
    }


    //删除tasksMap中挂起任务
    public int deleteTask(int taskId) {
        if(taskId<=0)
            return ReturnCodeKeys.E009;
        if(tasksMap!=null&&tasksMap.containsKey(taskId)){
            tasksMap.remove(taskId);
            return ReturnCodeKeys.E011;
        }
        if(currentTasks!=null&&currentTasks.containsKey(taskId)){
            currentTasks.remove(taskId);
            return ReturnCodeKeys.E011;
        }
        return ReturnCodeKeys.E012;
    }


    public int scheduleTask(int threshold) {
        if(threshold<=0)
            return ReturnCodeKeys.E002;
        if(currentConsump==null){
            currentConsump=new HashMap<Integer,Integer>();
        }
        //顺序分配节点

        if(tasksMap!=null&&tasksMap.size()>0&&nodeIds!=null&&nodeIds.size()>0){
            int nodelen=nodeIds.size();
            int tasksMaplen=tasksMap.size();
            //初始化task指定的节点
            int i=0;
            int taskToNode=nodeIds.get(i);
            for(Map.Entry<Integer,Integer>curnode:tasksMap.entrySet()){
                if(i>=nodelen)
                    i=0;
                int nodeid=nodeIds.get(i++);
                if(!currentConsump.containsKey(curnode.getKey())){
                    currentConsump.put(nodeid,0);
                }
                int consum=currentConsump.get(nodeid);
                currentTasks.put(curnode.getKey(),nodeid);
                currentConsump.put(nodeid,curnode.getValue()+consum);

            }
            return ReturnCodeKeys.E013;

        }
        return ReturnCodeKeys.E014;

    }


    //查询任务状态
    public int queryTaskStatus(List<TaskInfo> tasks) {
        if(tasks==null)
            return ReturnCodeKeys.E016;
        tasks.clear();
        //taskid排序
        int[]sortid=new int[currentTasks.size()];
        int i=0;
        for(int tid:currentTasks.keySet()){
            sortid[i++]=tid;
        }

        for(int j=0;j<sortid.length;j++){
            for(int k=j+1;k<sortid.length;k++){
                if(sortid[j]>sortid[k]){
                    int temp=sortid[j];
                    sortid[j]=sortid[k];
                    sortid[k]=temp;
                }
            }
        }

        for(int itid=0;itid<sortid.length;itid++){
            int taskid=sortid[itid];
            int nodeid=currentTasks.get(taskid);
            TaskInfo task=new TaskInfo();
            task.setTaskId(taskid);
            task.setNodeId(nodeid);
            tasks.add(task);
        }
        return ReturnCodeKeys.E015;
    }

}
