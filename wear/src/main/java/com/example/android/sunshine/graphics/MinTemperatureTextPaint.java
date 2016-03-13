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
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;

import com.example.android.sunshine.app.R;

public class MinTemperatureTextPaint extends Paint {
    public static final char DEGREE = (char) 0x00b0;

    private float mXOffset;
    private float mYOffset;

    public MinTemperatureTextPaint(Context context) {
        super();
        setAntiAlias(true);
        setColor(ContextCompat.getColor(context, R.color.digital_subtext));
        setTextSize(context.getResources().getDimension(R.dimen.temperature_text_size));
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
        mXOffset = context.getResources().getDimension(R.dimen.min_temperature_x_offset);
        mYOffset = context.getResources().getDimension(R.dimen.max_temperature_y_offset);
    }

    public void onDrawText(Canvas canvas, String temperature) {
        if (null == temperature) return;
        canvas.drawText(temperature + DEGREE, mXOffset, mYOffset, this);
    }

    public void onDrawText(Canvas canvas, Float temperature) {
        if (null == temperature) return;
        onDrawText(canvas, String.format("%.0f", temperature));
    }

    public void onDrawText(Canvas canvas, Integer temperature) {
        if (null == temperature) return;
        onDrawText(canvas, Integer.toString(temperature));
    }
}
