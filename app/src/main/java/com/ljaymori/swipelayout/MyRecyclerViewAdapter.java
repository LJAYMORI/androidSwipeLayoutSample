package com.ljaymori.swipelayout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> items = new ArrayList<String>();
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, ArrayList<String> list) {
        items = list;
        mContext = context;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setViewHolder(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SwipeLayout swipeLayout;
        private RecyclerView recyclerView;
        private ItemRecyclerViewAdapter mAdapter;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView;

            textView = (TextView) swipeLayout.findViewById(R.id.textView);

            recyclerView = (RecyclerView) swipeLayout.findViewById(R.id.recyclerView_in_item);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            mAdapter = new ItemRecyclerViewAdapter(mContext, initContent());

            recyclerView.setAdapter(mAdapter);

            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.bottom_wrapper));
            swipeLayout.addSwipeListener(swipeListener);

            swipeLayout.findViewById(R.id.recyclerView_in_item).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });

            swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(MainActivity.class.getName(), "click on surface");
                }
            });
            swipeLayout.getSurfaceView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d(MainActivity.class.getName(), "longClick on surface");
                    return true;
                }
            });

        }

        private ArrayList<String> initContent() {
            ArrayList<String> list = new ArrayList<String>();
            for(int i=0; i<10; i++) {
                list.add("Content_" + i);
            }
            return list;
        }

        public void setViewHolder(String str) {
            textView.setText(str);
        }
    }


    public interface OnListViewRevealListener {
        void onReveal();
        void onVanish();
    }
    OnListViewRevealListener revealListener;
    public void setOnListViewRevealListener(OnListViewRevealListener listener) {
        revealListener = listener;
    }

    private SwipeLayout.SwipeListener swipeListener =  new SwipeLayout.SwipeListener() {

        @Override
        public void onStartOpen(SwipeLayout swipeLayout) {

        }

        @Override
        public void onOpen(SwipeLayout swipeLayout) {
            // TODO up down scroll event ListView에게 주기
            if(revealListener != null) {
                revealListener.onReveal();
            }
        }

        @Override
        public void onStartClose(SwipeLayout swipeLayout) {

        }

        @Override
        public void onClose(SwipeLayout swipeLayout) {
            // TODO up down scroll event RecyclerView에게 주기
            if(revealListener != null) {
                revealListener.onVanish();
            }
        }

        @Override
        public void onUpdate(SwipeLayout swipeLayout, int i, int i1) {

        }

        @Override
        public void onHandRelease(SwipeLayout swipeLayout, float v, float v1) {

        }
    };
}
