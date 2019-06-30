package com.example.nongsa2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BoardListAdapter extends BaseAdapter {
    private Context context;
    private List<Board> boardList;

    public BoardListAdapter(Context context, List<Board> boardList)
    {
        this.context = context;
        this.boardList =boardList;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.board,null);

        TextView title = (TextView) v.findViewById(R.id.title);
        TextView date = (TextView) v.findViewById(R.id.date);
        TextView content = (TextView) v.findViewById(R.id.content);

        title.setText(boardList.get(i).getTitle());
        date.setText(boardList.get(i).getDate());
        content.setText(boardList.get(i).getContent());


        return v;
    }
}
