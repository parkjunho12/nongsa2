package com.example.nongsa2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

public class Garden_BoardListAdapter extends BaseAdapter {
    private Context context;
    private List<Garden_board_Board> boardList;
    private Fragment parent;

    public Garden_BoardListAdapter(Context context, List<Garden_board_Board> boardList, Fragment parent) {
        this.context = context;
        this.boardList = boardList;
        this.parent = parent;
    }

    @Override
    public int getCount() {
        return boardList.size();
    }

    @Override
    public Object getItem(int i) {
        return boardList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.garden, null);

        TextView TITLE = (TextView) v.findViewById(R.id.TITLE);
        TextView index =(TextView) v.findViewById(R.id.counts);

        TextView USERNM = (TextView) v.findViewById(R.id.USERNM);
        TextView REGDT = (TextView) v.findViewById(R.id.REGDT);
        index.setText(String.valueOf(i+1));
        TITLE.setText(Garden_array.getFARM_NM(i)+"\n ("+Garden_array.getADDRESS1(i)+")");
        USERNM.setText(Garden_array.getID(i));
        REGDT.setText(Garden_array.getREGIST(i));
        return v;
    }
}
