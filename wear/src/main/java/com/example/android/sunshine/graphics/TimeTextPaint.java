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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.android.sunshine.app.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTextPaint extends Paint {
    private float mXOffset;
    private float mYOffset;

    public TimeTextPaint(Context context) {
        super();
        setAntiAlias(true);
        setColor(ContextCompat.getColor(context, R.color.digital_text));
        setTextSize(context.getResources().getDimension(R.dimen.time_text_size));
        mXOffset = context.getResources().getDimension(R.dimen.time_x_offset);
        mYOffset = context.getResources().getDimension(R.dimen.time_y_offset);
    }

    public void onDrawText(Canvas canvas, Date time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        canvas.drawText(simpleDateFormat.format(time), mXOffset, mYOffset, this);
    }

    public void onDrawText(Canvas canvas, String time) {
        canvas.drawText(time, mXOffset, mYOffset, this);
    }

    public void onDrawText(Canvas canvas, long milliseconds) {
        onDrawText(canvas, new Date(milliseconds));
    }
}
