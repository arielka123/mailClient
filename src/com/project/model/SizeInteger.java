package com.project.model;

public class SizeInteger implements Comparable<SizeInteger>{
    private int size;

    public SizeInteger(int size) {
        this.size = size;
    }

    @Override
    public String toString(){
        if(size<=0){
            return "0";
        }
        if(size<1024){
            return size + "B";
        }
        if(size<1048576){
            return size/1024 + "kB";
        }
        if(size<1073741824){
            return size/1048576 + "MB";
        }else return "...";
    }

    @Override
    public int compareTo(SizeInteger o) {
        if(size > o.size){return 1;}
        else if (size < o.size){return -1;}
        else return 0;
    }
}
