#!/bin/bash

docker run -d --name node0 -h node0 -v ~/opt:/opt --privileged ubuntu:hadoop-base /root/run.sh
docker run -d --name node1 -h node1 -v ~/opt:/opt --privileged ubuntu:hadoop-base /root/run.sh
docker run -d --name node2 -h node2 -v ~/opt:/opt --privileged ubuntu:hadoop-base /root/run.sh
