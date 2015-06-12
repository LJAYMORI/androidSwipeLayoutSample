package com.ljaymori.swipelayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

        private ListView listView;
        private SwipeLayout swipeLayout;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView;

            textView = (TextView) swipeLayout.findViewById(R.id.textView);

            listView = (ListView) swipeLayout.findViewById(R.id.listView);
            ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, initContent());
            listView.setAdapter(adapter);

            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
//            View starBottView = sample1.findViewById(R.id.starbott);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.bottom_wrapper));
//            sample1.addDrag(SwipeLayout.DragEdge.Right, sample1.findViewById(R.id.bottom_wrapper_2));
//            sample1.addDrag(SwipeLayout.DragEdge.Top, starBottView);
//            sample1.addDrag(SwipeLayout.DragEdge.Bottom, starBottView);
//            sample1.addRevealListener(R.id.delete, new SwipeLayout.OnRevealListener() {
//                @Override
//                public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {
//
//                }
//            });
            swipeLayout.addRevealListener(R.id.bottom_wrapper, new SwipeLayout.OnRevealListener(){
                @Override
                public void onReveal(View view, SwipeLayout.DragEdge dragEdge, float v, int i) {
                    Log.i("SwipeLayout", "Drag!!");
//                    Log.i("SwipeLayout view", "type - " + view.ty);
                    Log.i("SwipeLayout dragEdge", dragEdge.toString());
                    Log.i("SwipeLayout float", v+"");
                    Log.i("SwipeLayout i", i+"");

                    if(v == 1.0) {
                        if(revealListener != null) {
                            revealListener.onReveal();
                        }
                    } else if (v == 0.0) {
                        if(vanishListener != null) {
                            vanishListener.onVanish();
                        }
                    }
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
//            sample1.findViewById(R.id.star2).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(MyActivity.this, "Star", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            sample1.findViewById(R.id.trash2).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(MyActivity.this, "Trash Bin", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            sample1.findViewById(R.id.magnifier2).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(MyActivity.this, "Magnifier", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            sample1.addRevealListener(R.id.starbott, new SwipeLayout.OnRevealListener() {
//                @Override
//                public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {
//                    View star = child.findViewById(R.id.star);
//                    float d = child.getHeight() / 2 - star.getHeight() / 2;
//                    ViewHelper.setTranslationY(star, d * fraction);
//                    ViewHelper.setScaleX(star, fraction + 0.6f);
//                    ViewHelper.setScaleY(star, fraction + 0.6f);
//                }
//            });



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
    }
    OnListViewRevealListener revealListener;
    public void setOnListViewRevealListener(OnListViewRevealListener listener) {
        revealListener = listener;
    }

    public interface OnListViewVanishListener {
        void onVanish();
    }
    OnListViewVanishListener vanishListener;
    public void setOnListViewVanishListener(OnListViewVanishListener listener) {
        vanishListener = listener;
    }

}
