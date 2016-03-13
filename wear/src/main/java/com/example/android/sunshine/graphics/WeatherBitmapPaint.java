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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.CanvasWatchFaceService.Engine;

import com.example.android.sunshine.app.R;

import java.lang.ref.WeakReference;

public class WeatherBitmapPaint extends Paint {
    public enum Art {
        CLEAR() {
            @Override
            Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine) {
                if (engine.isInAmbientMode()) {
                    return BitmapFactory.decodeResource(resources, R.drawable.ic_clear);
                } else {
                    return BitmapFactory.decodeResource(resources, R.drawable.art_clear);
                }
            }
        },
        CLOUDS() {
            @Override
            Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine) {
                if (engine.isInAmbientMode()) {
                    return BitmapFactory.decodeResource(resources, R.drawable.ic_cloudy);
                } else {
                    return BitmapFactory.decodeResource(resources, R.drawable.art_clouds);
                }
            }
        },
        FOG() {
            @Override
            Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine) {
                if (engine.isInAmbientMode()) {
                    return BitmapFactory.decodeResource(resources, R.drawable.ic_fog);
                } else {
                    return BitmapFactory.decodeResource(resources, R.drawable.art_fog);
                }
            }
        },
        LIGHT_CLOUDS() {
            @Override
            Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine) {
                if (engine.isInAmbientMode()) {
                    return BitmapFactory.decodeResource(resources, R.drawable.ic_light_clouds);
                } else {
                    return BitmapFactory.decodeResource(resources, R.drawable.art_light_clouds);
                }
            }
        },
        LIGHT_RAIN() {
            @Override
            Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine) {
                if (engine.isInAmbientMode()) {
                    return BitmapFactory.decodeResource(resources, R.drawable.ic_light_rain);
                } else {
                    return BitmapFactory.decodeResource(resources, R.drawable.art_light_rain);
                }
            }
        },
        RAIN() {
            @Override
            Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine) {
                if (engine.isInAmbientMode()) {
                    return BitmapFactory.decodeResource(resources, R.drawable.ic_rain);
                } else {
                    return BitmapFactory.decodeResource(resources, R.drawable.art_rain);
                }
            }
        },
        SNOW() {
            @Override
            Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine) {
                if (engine.isInAmbientMode()) {
                    return BitmapFactory.decodeResource(resources, R.drawable.ic_snow);
                } else {
                    return BitmapFactory.decodeResource(resources, R.drawable.art_snow);
                }
            }
        },
        STORM() {
            @Override
            Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine) {
                if (engine.isInAmbientMode()) {
                    return BitmapFactory.decodeResource(resources, R.drawable.ic_storm);
                } else {
                    return BitmapFactory.decodeResource(resources, R.drawable.art_storm);
                }
            }
        };

        static Bitmap decode(String weather, Resources resources, Engine engine) {
            return Art.valueOf(weather.toUpperCase()).decode(resources, engine);
        }

        abstract Bitmap decode(Resources resources, CanvasWatchFaceService.Engine engine);
    }

    private WeakReference<Context> mContext;
    private WeakReference<Engine> mEngine;
    private float mXOffset;
    private float mYOffset;

    public WeatherBitmapPaint(Context context, Engine engine) {
        super();
        mContext = new WeakReference<>(context);
        mEngine = new WeakReference<>(engine);
        mXOffset = context.getResources().getDimension(R.dimen.icon_x_offset);
        mYOffset = context.getResources().getDimension(R.dimen.icon_y_offset);
    }

    public void onDrawWeatherBitmap(Canvas canvas, Integer weatherId) {
        if (null == weatherId) return;
        Art art = getWeatherAssetCondition(weatherId);
        Bitmap bitmap = art.decode(mContext.get().getResources(), mEngine.get());
        setAntiAlias(!mEngine.get().isInAmbientMode());
        canvas.drawBitmap(bitmap, mXOffset, mYOffset, this);
    }

    private static Art getWeatherAssetCondition(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return Art.STORM;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return Art.LIGHT_RAIN;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return Art.RAIN;
        } else if (weatherId == 511) {
            return Art.SNOW;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return Art.RAIN;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return Art.SNOW;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return Art.FOG;
        } else if (weatherId == 761 || weatherId == 781) {
            return Art.STORM;
        } else if (weatherId == 800) {
            return Art.CLEAR;
        } else if (weatherId == 801) {
            return Art.LIGHT_CLOUDS;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return Art.CLOUDS;
        }
        return null;
    }
}
