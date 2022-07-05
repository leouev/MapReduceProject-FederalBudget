#!/bin/bash

export HADOOP_HOME=/opt/mapr/hadoop/hadoop-2.7.0
export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native/Linux-amd64-64
export CLASSPATH=$HADOOP_HOME/share/hadoop/mapreduce/*:$HADOOP_HOME/share/hadoop/mapreduce/lib/*:$HADOOP_HOME/share/hadoop/common/lib/*:$HADOOP_HOME/share/hadoop/common/*:$HADOOP_HOME/share/hadoop/tools/lib/*:$HADOOP_HOME/share/hadoop/yarn/*:$HADOOP_HOME/share/hadoop/yarn/lib/*
export HADOOP_CLASSPATH=$CLASSPATH

USER=`whoami`

rm -rf /user/$USER/Lab3/RECEIPTS_LAB/OUT
hadoop jar Receipts.jar Receipts.ReceiptsDriver /user/$USER/Lab3/RECEIPTS_LAB/DATA/receipts.txt /user/$USER/Lab3/RECEIPTS_LAB/OUT 
