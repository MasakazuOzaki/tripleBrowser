package com.lifeistech.masakazuozaki.triplebrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifeistech.masakazuozaki.triplebrowser.entity.Bookmark;

import java.util.List;


/**
 * Created by MasakazuOzaki on 16/04/05.
 */
public class BookmarkAdapter extends ArrayAdapter<Bookmark> {
    List<Bookmark> mBookmarks;
    LayoutInflater layoutInflater;

    public BookmarkAdapter(Context context, int resouceId, List<Bookmark> bookmarks) {
        super(context, resouceId, bookmarks);

        mBookmarks = bookmarks;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mBookmarks.size();
    }

    @Override
    public Bookmark getItem(int position) {
        return mBookmarks.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bookmark_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder); //ここでtagづけしとかないと落ちる
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Bookmark item = getItem(position);
        if (item != null) {
            //set data
            viewHolder.titleTextView.setText(item.title);
            viewHolder.urlTextView.setText(item.url);
        }
        //set tag
        viewHolder.titleTextView.setTag(position);
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.motion);
        convertView.startAnimation(anim);
        return convertView;
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView urlTextView;


        public ViewHolder(View view) {
            titleTextView = (TextView) view.findViewById(R.id.bookmark_list_title);
            urlTextView = (TextView) view.findViewById(R.id.bookmark_list_url);
        }
    }
}
