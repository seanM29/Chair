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
package com.seanshend.view.chairview;

import android.support.annotation.NonNull;


public class ChairData {
    // 用户关心数据

    private int angle;        // 数值



    // 非用户关心数据
    private int color = 0;      // 颜色

    public ChairData(@NonNull int angle) {

        this.angle = angle;
    }




    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

}
