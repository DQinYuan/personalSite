#!/bin/bash

apt-get install -y expect

auto_ssh() {
    expect -c "set timeout -1;
        spawn ssh $1;
        expect {
            *(yes/no)* {send -- yes\r;exp_continue;}
            eof        {exit 0;}
        }";
}

auto_ssh 0
auto_ssh 1
auto_ssh 2

hadoop namenode -format

start-all.sh
