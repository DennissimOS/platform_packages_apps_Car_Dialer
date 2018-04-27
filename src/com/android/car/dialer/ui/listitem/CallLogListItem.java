/*
 * Copyright (C) 2018 The Android Open Source Project
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
package com.android.car.dialer.ui.listitem;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

import androidx.car.widget.TextListItem;

import com.android.car.dialer.telecom.UiCallManager;
import com.android.car.dialer.ui.CallHistoryListItemProvider;
import com.android.car.dialer.ui.CallLogListingTask;

/**
 * List item which is created by {@link CallHistoryListItemProvider} binds a call list item to a
 * list view item.
 */
public class CallLogListItem extends TextListItem {
    private final CallLogListingTask.CallLogItem mCallLogItem;
    private final Context mContext;

    public CallLogListItem(Context context, CallLogListingTask.CallLogItem callLog) {
        super(context);
        mCallLogItem = callLog;
        mContext = context;
    }

    @Override
    public void onBind(ViewHolder viewHolder) {
        super.onBind(viewHolder);
        setPrimaryActionIcon(
                new BitmapDrawable(mContext.getResources(), mCallLogItem.mIcon), true);
        setTitle(mCallLogItem.mTitle);
        setBody(mCallLogItem.mText);

        viewHolder.itemView.setOnClickListener((v) -> {
            UiCallManager.get().safePlaceCall(mCallLogItem.mNumber, false);
        });
    }
}
