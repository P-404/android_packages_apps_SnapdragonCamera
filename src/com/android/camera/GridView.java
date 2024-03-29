/*
 * Copyright (C) 2017-2020 Paranoid Android
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import org.codeaurora.snapcam.R;

/**
 * GridView is a view which directly overlays the preview and draws
 * evenly spaced grid lines.
 */
public class GridView extends View {

    private RectF mDrawBounds;
    private Paint mPaint = new Paint();

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.grid_line_width));
        mPaint.setColor(getResources().getColor(R.color.grid_line));
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawBounds != null) {
            float thirdWidth = mDrawBounds.width() / 3;
            float thirdHeight = mDrawBounds.height() / 3;
            for (int i = 1; i < 3; i++) {
                // Draw the vertical lines.
                final float x = thirdWidth * i;
                canvas.drawLine(mDrawBounds.left + x, mDrawBounds.top,
                        mDrawBounds.left + x, mDrawBounds.bottom, mPaint);
                // Draw the horizontal lines.
                final float y = thirdHeight * i;
                canvas.drawLine(mDrawBounds.left, mDrawBounds.top + y,
                        mDrawBounds.right, mDrawBounds.top + y, mPaint);
            }
        }
    }

    protected void setBounds(RectF rectF) {
        mDrawBounds = rectF;
        invalidate();
    }
}
