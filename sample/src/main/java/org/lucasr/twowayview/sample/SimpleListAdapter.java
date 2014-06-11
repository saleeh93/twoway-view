/*
 * Copyright (C) 2012 Lucas Rocha
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

package org.lucasr.twowayview.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.lucasr.twowayview.TWSpannableGridView;

public class SimpleListAdapter extends BaseAdapter {
	private final Context mContext;

	public SimpleListAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
	    return 100;
	}

	@Override
	public Integer getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    ViewHolder holder = null;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.title);

			convertView.setTag(holder);
		} else {
		    holder = (ViewHolder) convertView.getTag();
		}

        holder.title.setText(String.valueOf(position));

        TWSpannableGridView.LayoutParams lp =
                (TWSpannableGridView.LayoutParams) convertView.getLayoutParams();

        final int rowSpan = (position == 0 || position == 3 ? 2 : 1);
        final int colSpan = (position == 0 ? 2 : (position == 3 ? 3 : 1));

        if (lp.rowSpan != rowSpan || lp.colSpan != colSpan) {
            lp.rowSpan = rowSpan;
            lp.colSpan = colSpan;
            convertView.setLayoutParams(lp);
        }

//        final int size;
//        if (position % 3 == 0) {
//            size = 300;
//        } else if (position % 5 == 0) {
//            size = 520;
//        } else if (position % 7 == 0) {
//            size = 700;
//        } else {
//            size = 160;
//        }
//
//        ViewGroup.LayoutParams lp = convertView.getLayoutParams();
//        if (lp.width != size) {
//            lp.width = size;
//            convertView.setLayoutParams(lp);
//        }

		return convertView;
	}

	class ViewHolder {
	    public TextView title;
	}
}