package com.example.demo.models;

public record PageModel(Integer size, Integer index, Integer total) {

    public int getPages(){
        int ps = total / size;
        int off = total % size;
        if (off > 0){
            return ps + 1;
        } else {
            return ps;
        }
    }

}
