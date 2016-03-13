/*
 * Copyright (c) 2016 JP Ventura
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.android.sunshine.app.R;

public class SeparatorPaint extends Paint {
    private float mLeft;
    private float mTop;
    private float mBottom;
    private float mRight;

    public SeparatorPaint(Context context) {
        Resources resources = context.getResources();
        mLeft = resources.getDimension(R.dimen.separator_x_offset);
        mTop = resources.getDimension(R.dimen.separator_y_offset);
        mBottom = mTop + resources.getDimension(R.dimen.separator_height);
        mRight = mLeft + resources.getDimension(R.dimen.separator_width);
        setColor(ContextCompat.getColor(context, R.color.separator));
    }

    public void onDrawSeparator(Canvas canvas) {
        canvas.drawRect(mLeft, mTop, mRight, mBottom, this);
    }
}
