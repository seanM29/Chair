/*
 * Copyright 2016 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2016-10-02 00:36:00
 *
 */

package com.seanshend.view.pieview;

import android.support.annotation.NonNull;

public class PieData {
    // 用户关心数据
    private String name;        // 名字
    private int value;        // 数值



    // 非用户关心数据
    private int color = 0;      // 颜色

    public PieData(@NonNull String name, @NonNull int value) {
        if(name.equals("Total")){
            if(value>=90){
                this.name="极好";
            }
            else if(value<=60){
                this.name="危险";
            }
            else {
                this.name="一般";
            }
        }
        else {
            this.name = name;
        }
        this.value = value;
        setColor();
    }

    public int getColor() {
        return color;
    }

    private void setColor(){
        if(value>=90){
            color=0xFF4C9FFF;
        }
        else if(value<90&&value>=60){
            color=0xFFFDD967;
        }
        else{
            color=0xFFFF5C5D;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        setColor();
    }

}
