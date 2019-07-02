package com.example.nongsa2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;

import java.util.List;

public class BoardListAdapter extends BaseAdapter  {
    private Context context;
    private List<Board> boardList;
    private Fragment parent;

    public BoardListAdapter(Context context, List<Board> boardList,Fragment parent)
    {
        this.context = context;
        this.boardList =boardList;
        this.parent=parent;
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
        View v = View.inflate(context,R.layout.board,null);

        TextView title = (TextView) v.findViewById(R.id.title);
        TextView date = (TextView) v.findViewById(R.id.date);
        TextView content = (TextView) v.findViewById(R.id.content);

        title.setText(boardList.get(i).getTitle());
        date.setText(boardList.get(i).getDate());
        content.setText(boardList.get(i).getContent());

        ImageView imageView =(ImageView)v.findViewById(R.id.imageview);

        if(boardList.get(i).getTitle().substring(0,2).equals("농지"))
        {
            imageView.setImageResource(R.drawable.bat);
        }

        Button addbutton =(Button)v.findViewById(R.id.addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent = new Intent(parent.getContext().getApplicationContext(),complexInfo.class);

                intent.putExtra("SIDO_NM",boardList.get(i).getSIDO_NM());
                intent.putExtra("ID",boardList.get(i).getID());
                intent.putExtra("SIGUN_NM",boardList.get(i).getSIGUN_NM());
                intent.putExtra("ADDR",boardList.get(i).getADDR());
                intent.putExtra("DEAL_AMOUNT",boardList.get(i).getDEAL_AMOUNT());
                intent.putExtra("DEAL_BIGO",boardList.get(i).getDEAL_BIGO());
                intent.putExtra("BUILDING_AREA",boardList.get(i).getBUILDING_AREA());
                intent.putExtra("AREA_ETC",boardList.get(i).getAREA_ETC());
                intent.putExtra("BUILD_YEAR",boardList.get(i).getBUILD_YEAR());
                intent.putExtra("VACANT_YEAR",boardList.get(i).getVACANT_YEAR());
                intent.putExtra("STRUCT_TYPE",boardList.get(i).getSTRUCT_TYPE());
                intent.putExtra("OWNER_NM",boardList.get(i).getOWNER_NM());
                intent.putExtra("OWNER_CONTACT",boardList.get(i).getOWNER_CONTACT());
                intent.putExtra("INSPECTOR",boardList.get(i).getINSPECTOR());
                intent.putExtra("LOT_AREA",boardList.get(i).getLOT_AREA());
                intent.putExtra("BIGO",boardList.get(i).getBIGO());
                intent.putExtra("FILE_PATH1",boardList.get(i).getFILE_PATH1());
                intent.putExtra("FILE_PATH2",boardList.get(i).getFILE_PATH2());
                intent.putExtra("FILE_PATH3",boardList.get(i).getFILE_PATH3());
                intent.putExtra("DETAIL_URL",boardList.get(i).getDETAIL_URL());
                intent.putExtra("DEAL_NEGO_YN",boardList.get(i).getDEAL_NEGO_YN());
                intent.putExtra("GUBUN",boardList.get(i).getGUBUN());
                intent.putExtra("DEAL_TYPE",boardList.get(i).getDEAL_TYPE());
                intent.putExtra("REG_DT",boardList.get(i).getREG_DT());
                intent.putExtra("Latitude",boardList.get(i).getLatitude());
                intent.putExtra("Longtitude",boardList.get(i).getLongtitude());


            parent.startActivity(intent);
    }
});
        return v;
    }



}
